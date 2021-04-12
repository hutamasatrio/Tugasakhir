package com.example.tugasakhirnew

import android.content.Intent
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {


    private var mNfcAdapter: NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
        toast(NFCUtil.retrieveNFCMessage(this.intent))

        btnUrgent.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))

            //menampilkan atau membaca nfc
            mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
            toast(NFCUtil.retrieveNFCMessage(this.intent))
        }

         fun onResume() {
            super.onResume()
            mNfcAdapter?.let {
                NFCUtil.enableNFCInForeground(it, this, javaClass)
            }
        }

         fun onPause() {
            super.onPause()
            mNfcAdapter?.let {
                NFCUtil.disableNFCInForeground(it, this)
            }
        }

        //write nfc
         fun onNewIntent(intent: Intent?) {
            super.onNewIntent(intent)
            val messageWrittenSuccessfully = NFCUtil.createNFCMessage("satrio", intent)
            toast(messageWrittenSuccessfully.ifElse("Successful Written to Tag", "Something When wrong Try Again"))
        }
    }

    fun <T> Boolean.ifElse(primaryResult: T, secondaryResult: T) = if (this) primaryResult else secondaryResult
        }



