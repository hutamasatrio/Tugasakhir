package com.example.tugasakhirnew

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.qifan.readnfcmessage.parser.NdefMessageParser
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    private var mNfcAdapter: NfcAdapter? = null
    private var mPendingIntent: PendingIntent? = null
    private lateinit var mTvView: TextView

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

//        btnUrgent.setOnClickListener {
//            startActivity(Intent(this, HomeActivity::class.java))
//
//        }


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
                // Process the messages array.
                //       parserNDEFMessage(messages)
                val stringname = parserNDEFMessage(messages)
                if (stringname.toString() == "satrio"){
                    Toast.makeText(this,stringname.toString(), Toast.LENGTH_LONG).show()

                    val idNFC = Intent(this@MainActivity, HomeActivity::class.java)
                    idNFC.putExtra("idNFC", stringname.toString())
                    startActivity(idNFC)

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
            idNFC.putExtra("idNFC", builder.toString())
            startActivity(idNFC)

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


