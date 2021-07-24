package com.enigmacamp.mysimplemvp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.enigmacamp.mysimplemvp.MainActivityContract.AppView
import com.enigmacamp.mysimplemvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AppView {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainPresenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        attachPresenter()
        mainBinding.apply {
            updateButton.setOnClickListener {
                mainPresenter.start()
            }
        }
    }

    override fun attachPresenter() {
        mainPresenter = MainActivityPresenter()
        mainPresenter.attachView(this)
    }

    override fun hideProgressBar() {
        Log.d(TAG, "hideProgressBar: ")
        mainBinding.updateButton.isEnabled = true
    }

    override fun showProgressBar() {
        Log.d(TAG, "showProgressBar: ")
        mainBinding.updateButton.isEnabled = false
    }

    override fun showCustomerInfo(customer: Customer) {
        Log.d(TAG, "Customer: ${customer}")
    }

    override fun onDestroy() {
        mainPresenter.detachView()
        super.onDestroy()
    }

    companion object {
        private val TAG = "MainActivity"
    }
}