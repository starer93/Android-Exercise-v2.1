package com.android.androidexercise.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * base activity class for all activities
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {

    private val disposables = CompositeDisposable()

    protected abstract fun getLayoutResId(): Int
    protected abstract fun getPresenter(): BasePresenter<*>?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayoutResId().takeIf { it > 0 }?.run {
            setContentView(this)
        }
    }

    //remove everything
    override fun onDestroy() {
        getPresenter()?.destroy()
        disposables.dispose()
        super.onDestroy()
    }

    override fun showError(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG)
            .show() //simple error pop up, can customize it if require
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}