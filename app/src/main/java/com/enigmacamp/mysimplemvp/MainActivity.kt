package com.enigmacamp.mysimplemvp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.mysimplemvp.MainActivityContract.AppView
import com.enigmacamp.mysimplemvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AppView {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainPresenter: MainActivityPresenter
    private var customer: Customer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        attachPresenter()
        Log.d(TAG, "Customer On Create: ${customer}")
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

    override fun showCustomerInfo(newCustomer: Customer) {
        customer = newCustomer
        Intent(this@MainActivity, NextActivity::class.java).also {
            startActivity(it)
        }
        finish()
        Log.d(TAG, "Customer: ${customer}")
    }

    override fun onDestroy() {
        mainPresenter.detachView()
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }

    companion object {
        private val TAG = "MainActivity"
    }
}