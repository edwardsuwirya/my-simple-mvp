package com.enigmacamp.mysimplemvp

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private var _customerLiveData = MutableLiveData<Customer>()
    val customerLiveData: LiveData<Customer>
        get() = _customerLiveData

    fun start() {
        updateInfo()
    }

    private fun getCustomerFromRepository() = Customer("Donny")

    private fun updateInfo() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val customer = getCustomerFromRepository()
                _customerLiveData.value = customer
            }, 2000
        )

    }
}