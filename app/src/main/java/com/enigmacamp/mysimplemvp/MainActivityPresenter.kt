package com.enigmacamp.mysimplemvp

import android.os.Handler
import android.os.Looper
import android.util.Log

class MainActivityPresenter(val view: View) {
    private val TAG = "MainActivityPresenter"
    fun updateInfo(customer: Customer) {
        view.showProgressBar()
        Handler(Looper.getMainLooper()).postDelayed(
            object : Runnable {
                override fun run() {
                    Log.d(TAG, "updateInfo: ${customer}")
                    view.hideProgressBar()
                }
            }, 2000
        )
    }
}