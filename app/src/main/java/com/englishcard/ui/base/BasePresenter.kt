package com.englishcard.ui.base

import android.content.Context
import androidx.lifecycle.*

open class BasePresenter<BaseViewInterface> : ViewModel(), LifecycleObserver {

    protected var view: BaseViewInterface? = null

    private var viewLifecycle: Lifecycle? = null

    open fun attachView(view: BaseViewInterface, lifecycle: Lifecycle) {
        this.view = view
        this.viewLifecycle = lifecycle

        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        view = null
        viewLifecycle = null
    }

    open fun loadDatabase(applicationContext: Context) {

    }
}