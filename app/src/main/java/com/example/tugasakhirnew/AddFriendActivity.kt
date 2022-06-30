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
import com.example.tugasakhirnew.common.Constant
import com.example.tugasakhirnew.common.Constant.Companion.NFC_PARAM
import com.example.tugasakhirnew.model.UserProfile
import com.example.tugasakhirnew.network.ApiInterface
import com.example.tugasakhirnew.network.RetrofitClient
import com.google.gson.JsonObject
import com.qifan.readnfcmessage.parser.NdefMessageParser
import kotlinx.android.synthetic.main.activity_add_friend.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFriendActivity : AppCompatActivity() {

    private var userId: String = ""
    private var mNfcAdapter: NfcAdapter? = null
    private var mPendingIntent: PendingIntent? = null
    private val retrofitClient = RetrofitClient.getNewtwork().create(ApiInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)
        userId = intent.getStringExtra(NFC_PARAM).toString()

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (checkNFCEnable()) {
            mPendingIntent = PendingIntent.getActivity(
                this, 0,
                Intent(this, this.javaClass)
                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
            )
        }
    }


    override fun onResume() {
        super.onResume()
        mNfcAdapter?.enableForegroundDispatch(this, mPendingIntent, null, null)
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
                // post to server
                addFriend(parserNDEFMessage(messages))
            }
        }
    }

    private fun addFriend(stringname: String) {
        retrofitClient.saveToFirendList(stringname, userId)
            .enqueue(object : Callback<JsonObject?> {
                override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                    response.body()?.let {
                        if (it.get("status").asBoolean) {
                            printMessage("success")
                            finish()
                        }
                    }
                }

                override fun onFailure(call: Call<JsonObject?>, t: Throwable) {
                    printMessage("failed")
                }
            })

    }

    private fun printMessage(message: String) {
        Toast.makeText(this@AddFriendActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun parserNDEFMessage(messages: List<NdefMessage>) : String {
        val records = NdefMessageParser.parse(messages[0])
        return records[records.size - 1].str()
    }

    private fun checkNFCEnable(): Boolean {
        return if (mNfcAdapter == null) {
            TVAddFriend.text = getString(R.string.tv_noNfc)
            false
        } else {
            mNfcAdapter!!.isEnabled
        }
    }
}