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
        mainActivityView = null
    }

    override fun start() {
        updateInfo(getCustomerFromRepository())
    }

    private fun getCustomerFromRepository() = Customer("Donny")

    private fun updateInfo(customer: Customer) {

        mainActivityView?.apply {
            showProgressBar()
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    hideProgressBar()
                    showCustomerInfo(customer)
                }, 2000
            )
        }
    }

    companion object {
        private val TAG = "MainActivityPresenter"
    }
}