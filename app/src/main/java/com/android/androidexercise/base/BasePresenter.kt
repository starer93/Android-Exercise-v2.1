package com.android.androidexercise.base

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Base Presenter
 */
abstract class BasePresenter<T : BaseView> {
    private val disposables = CompositeDisposable()
    protected var view: T? = null

    fun addToDisposables(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun getMvpView(): T? = view

    fun bind(view: T) {
        this.view = view
    }

    private fun unbind() {
        this.view = null
    }

    open fun destroy() {
        disposables.clear()
        unbind()
    }

}