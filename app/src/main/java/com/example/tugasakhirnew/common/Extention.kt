package com.example.tugasakhirnew.common

import android.content.Context
import android.widget.Toast


fun Context.printMessage(message : String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}