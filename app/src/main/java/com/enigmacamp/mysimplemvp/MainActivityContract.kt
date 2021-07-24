package com.enigmacamp.mysimplemvp

interface MainActivityContract {
    interface AppView {
        fun attachPresenter()
        fun hideProgressBar()
        fun showProgressBar()
        fun showCustomerInfo(customer: Customer)
    }

    interface AppPresenter {
        fun attachView(view: AppView)

        fun detachView()
        fun start()
    }
}