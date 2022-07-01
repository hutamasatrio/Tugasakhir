package com.example.tugasakhirnew.common

import android.os.Handler
import android.os.Looper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.StringReader
import java.util.concurrent.Executors


object ExecuteAsync {

    private val executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    fun runInBackground(action: Actions) {
        executor.execute {
            action.doInBacground()
        }
    }

    fun <T>runInUiThread(data: T, onSuccess: OnSuccess<T>) {
        handler.post {
            onSuccess.onSuccess(data)
        }
    }

    interface Actions {
        fun doInBacground()
    }

    interface OnSuccess<T> {
        fun onSuccess(response: T)
    }
}