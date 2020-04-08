package com.android.androidexercise.base

import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Base Fragment
 */
abstract class BaseFragment : Fragment(), BaseView {

    protected abstract fun getPresenter(): BasePresenter<*>?

    override fun onDetach() {
        super.onDetach()
        getPresenter()?.destroy()
    }

    override fun showError(errorMsg: String) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
    }


}