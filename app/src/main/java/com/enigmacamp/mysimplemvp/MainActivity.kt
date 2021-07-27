package com.enigmacamp.mysimplemvp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.mysimplemvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initViewModel()
        subscribe()
        mainBinding.apply {
            updateButton.setOnClickListener {
                showProgressBar()
                viewModel.start()
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.customerLiveData.observe(this, {
            hideProgressBar()
            showCustomerInfo(it)
        })
    }

    private fun hideProgressBar() {
        Log.d(TAG, "hideProgressBar: ")
        mainBinding.updateButton.isEnabled = true
    }

    private fun showProgressBar() {
        Log.d(TAG, "showProgressBar: ")
        mainBinding.updateButton.isEnabled = false
    }

    private fun showCustomerInfo(newCustomer: Customer) {
//        Intent(this@MainActivity, NextActivity::class.java).also {
//            startActivity(it)
//        }
//        finish()
        Log.d(TAG, "Customer: ${newCustomer}")
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }

    companion object {
        private val TAG = "MainActivity ${BuildConfig.APPLICATION_ID} run on ${BuildConfig.BASE_URL}"
    }
}