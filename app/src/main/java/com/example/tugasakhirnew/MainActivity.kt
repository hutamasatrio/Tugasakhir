package com.example.tugasakhirnew

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.tugasakhirnew.common.Constant.Companion.NFC_PARAM
import com.example.tugasakhirnew.model.UserProfile
import com.example.tugasakhirnew.network.ApiInterface
import com.example.tugasakhirnew.network.RetrofitClient
import com.qifan.readnfcmessage.parser.NdefMessageParser
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var mNfcAdapter: NfcAdapter? = null
    private var mPendingIntent: PendingIntent? = null
    private lateinit var mTvView: TextView

    private val retrofitClient = RetrofitClient.getNewtwork().create(ApiInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (checkNFCEnable()) {
            mPendingIntent = PendingIntent.getActivity(
                this, 0,
                Intent(this, this.javaClass)
                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
            )
        } else {
            mTvView.text = getString(R.string.tv_noNfc)
        }

        getData()

        btnUrgent.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
//
        }

    }
    private fun getData() {

        val profileUser = retrofitClient.getData()
        profileUser.enqueue(object : Callback<UserProfile?> {
            override fun onResponse(call: Call<UserProfile?>, response: Response<UserProfile?>) {

                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()
                Log.i("data_raw", response.toString())
                for (myData in responseBody.data) {
                    myStringBuilder.append(myData.name)
                    myStringBuilder.append("/n")
                }
//                Log.i("data_response", myStringBuilder.toString())
//
////                txtId3.text = myStringBuilder.toString()
//                tv.postDelayed({
//                    txtId2.text = myStringBuilder
//                }, 1000)

            }

            override fun onFailure(call: Call<UserProfile?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "data gagal", Toast.LENGTH_SHORT).show()
                Log.d("main activity", "failed to load data"+t.message)
            }
        })


    }

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
                val stringname = parserNDEFMessage(messages)
                if (stringname.toString() == "satrio"){
                    Toast.makeText(this,stringname.toString(), Toast.LENGTH_LONG).show()

                    val idNFC = Intent(this@MainActivity, HomeActivity::class.java)
                    idNFC.putExtra(NFC_PARAM, stringname.toString())
                    startActivity(idNFC)

                    mTvView.text = stringname.toString()


//                    startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                }
                else{
                    Toast.makeText(this,"user tidak diketahui, silahkan hubungi admin", Toast.LENGTH_LONG).show()

                }
            }
        }
    }

    private fun parserNDEFMessage(messages: List<NdefMessage>) {
        val builder = StringBuilder()
        val records = NdefMessageParser.parse(messages[0])
        val size = records.size
        val name = "satrio\n"

        for (i in 0 until size) {
            val record = records.get(i)
            val str = record.str()
            builder.append(str).append("\n")
        }
        mTvView.text = builder.toString()
        if (mTvView.text.toString()== builder.toString()){
            Toast.makeText(this,builder.toString(), Toast.LENGTH_LONG).show()

            val idNFC = Intent(this@MainActivity, HomeActivity::class.java)
            idNFC.putExtra(NFC_PARAM, builder.toString())
            startActivity(idNFC)
            mTvView.text = builder.toString()

        }else{
            Toast.makeText(this, "gagal", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initView() {

        mTvView = findViewById<View>(R.id.resultTextView) as TextView
    }

    private fun checkNFCEnable(): Boolean {
        return if (mNfcAdapter == null) {
            mTvView.text = getString(R.string.tv_noNfc)
            false
        } else {
            mNfcAdapter!!.isEnabled
        }
    }
}


