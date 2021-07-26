package com.enigmacamp.mysimplemvp

import android.os.Handler
import android.os.Looper
import android.util.Log

class MainActivityPresenter : MainActivityContract.AppPresenter {
    var mainActivityView: MainActivityContract.AppView? = null

    override fun attachView(view: MainActivityContract.AppView) {
        mainActivityView = view
    }

    override fun detachView() {
        Log.d(TAG, "onDestroy: ")
        mainActivityView = null
    }

    override fun start() {
        updateInfo()
    }

    private fun getCustomerFromRepository() = Customer("Donny")

    private fun updateInfo() {
        var customer: Customer? = null
        mainActivityView?.apply {
            showProgressBar()
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    customer = getCustomerFromRepository()
                    hideProgressBar()
                    showCustomerInfo(customer ?: Customer(""))
                }, 2000
            )

        }
    }

    companion object {
        private val TAG = "MainActivityPresenter"
    }
}