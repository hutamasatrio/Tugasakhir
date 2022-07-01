package com.example.tugasakhirnew

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.tugasakhirnew.common.Constant.Companion.NFC_PARAM
import com.example.tugasakhirnew.common.printMessage
import com.example.tugasakhirnew.model.UserProfile
import com.example.tugasakhirnew.network.RetrofitClient
import com.qifan.readnfcmessage.parser.NdefMessageParser
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var mNfcAdapter: NfcAdapter? = null
    private var mPendingIntent: PendingIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (checkNFCEnable()) {
            mPendingIntent = PendingIntent.getActivity(
                this, 0,
                Intent(this, this.javaClass)
                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
            )
        }
        else{
            printMessage("nyalakan fitu NFC")

        }

//        getData()

//        btnUrgent.setOnClickListener {
//            startActivity(Intent(this, HomeActivity::class.java))
//        }

    }
//    private fun getData() {
//        RetrofitClient.getApi().getData()
//            .enqueue(object : Callback<UserProfile?> {
//                override fun onResponse(
//                    call: Call<UserProfile?>,
//                    response: Response<UserProfile?>
//                ) {
//
//                    val responseBody = response.body()!!
//
//                    val myStringBuilder = StringBuilder()
//                    Log.i("data_raw", response.toString())
//                    for (myData in responseBody.data) {
//                        myStringBuilder.append(myData.name)
//                        myStringBuilder.append("/n")
//                    }
//
//                }
//
//                override fun onFailure(call: Call<UserProfile?>, t: Throwable) {
//                    Toast.makeText(this@MainActivity, "data gagal", Toast.LENGTH_SHORT).show()
//                    Log.d("main activity", "failed to load data" + t.message)
//                }
//            })
//
//
//    }

    override fun onResume() {
        super.onResume()
        mNfcAdapter?.let {
            it.enableForegroundDispatch(this, mPendingIntent, null, null)
            Log.i("nfc", "on enable")
        }
    }


    override fun onPause() {
        super.onPause()
        mNfcAdapter?.disableForegroundDispatch(this)
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMessages ->
                val messages: List<NdefMessage> = rawMessages.map { it as NdefMessage }
                // Process the messages array.
                //       parserNDEFMessage(messages)
                parserNDEFMessage(messages)

                /*val stringname = parserNDEFMessage(messages)
                if (stringname.toString() == "satrio"){
                    Toast.makeText(this,stringname.toString(), Toast.LENGTH_LONG).show()

                    val idNFC = Intent(this@MainActivity, HomeActivity::class.java)
                    idNFC.putExtra(NFC_PARAM, stringname.toString())
                    startActivity(idNFC)
                }
                else{
                    resultTextView.text= stringname.toString()
                  print("user tidak diketahui, silahkan hubungi admin")
                }*/
            }
        }
    }

    private fun parserNDEFMessage(messages: List<NdefMessage>) {
//        val builder = StringBuilder()
        val records = NdefMessageParser.parse(messages[0])
        val str = records.get(records.size - 1).str()
        validateUser(str)
    }

    private fun validateUser(userId: String) {
        RetrofitClient.getApi().getUserbyId(userId)
            .enqueue(object : Callback<UserProfile> {
                override fun onResponse(call: Call<UserProfile?>, response: Response<UserProfile?>) {
                    if (response.body() == null)  {
                        printMessage("login failed")
                        resultTextView.text= userId
                        return
                    }

                    response.body()?.let {
                        if (it.status && userId == it.data[it.data.size - 1].userId) {
                            printMessage("login success")
                            onValidData(userId)
                        } else {
                            printMessage("login failed")
                            resultTextView.text= userId
                        }
                    }
                }


                override fun onFailure(call: Call<UserProfile?>, t: Throwable) {
                    resultTextView.text= userId
                    printMessage("user unrigistered!")
                }
            })
    }

    private fun onValidData(userId: String) {
            startActivity(
                Intent(this@MainActivity, HomeActivity::class.java)
                    .putExtra(NFC_PARAM, userId)
            )
    }

    private fun checkNFCEnable(): Boolean {
        return if (mNfcAdapter == null) {
            false
        } else {
            mNfcAdapter!!.isEnabled
        }
    }
}


