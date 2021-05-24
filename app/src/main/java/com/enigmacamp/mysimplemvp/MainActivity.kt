package com.enigmacamp.mysimplemvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.enigmacamp.mysimplemvp.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity(), View {
    private val TAG = "MainActivity"
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainPresenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainPresenter = MainActivityPresenter(this)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.apply {
            updateButton.setOnClickListener {
                mainPresenter.updateInfo(Customer("Donny"))
            }
        }
    }

    override fun hideProgressBar() {
        Log.d(TAG, "hideProgressBar: ")
        mainBinding.updateButton.isEnabled = true
    }

    override fun showProgressBar() {
        Log.d(TAG, "showProgressBar: ")
        mainBinding.updateButton.isEnabled = false
    }
}