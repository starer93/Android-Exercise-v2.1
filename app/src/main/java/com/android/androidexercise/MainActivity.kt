package com.android.androidexercise

import android.os.Bundle
import com.android.androidexercise.base.BaseActivity
import com.android.androidexercise.base.BasePresenter
import com.android.androidexercise.ui.news.NewsFragment

class MainActivity : BaseActivity() {

    private val newsFragment = NewsFragment.getInstance()
    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun getPresenter(): BasePresenter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    private fun initFragment() {
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.layout_main, newsFragment)
        transition.commit()
    }

    fun updateTitle(title: String) {
        supportActionBar?.title = title
    }
}
