package com.android.androidexercise.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.androidexercise.MainActivity

import com.android.androidexercise.R
import com.android.androidexercise.base.BaseFragment
import com.android.androidexercise.base.BasePresenter
import com.android.androidexercise.data.response.NewsListResponse
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : BaseFragment(), NewsView {

    private val adapter = NewsAdapter()
    private var presenter = NewsPresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        presenter.bind(this)
        presenter.getNewsList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun getPresenter(): BasePresenter<*>? {
        return presenter
    }

    override fun onNewsSucceed(response: NewsListResponse) {
        adapter.setList(response.rows)
        if (context is MainActivity) {
            response.title?.let {
                (context as MainActivity).updateTitle(it)
            }
        }
        if (layout_refresh.isRefreshing) // if is loading animation still showing, dismiss it
        {
            layout_refresh.isRefreshing = false
        }
    }

    private fun initView() {
        rv_news.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_news.adapter = adapter
        rv_news.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        layout_refresh.setOnRefreshListener {
            presenter.getNewsList()
        }
    }

    companion object {
        fun getInstance(): NewsFragment {
            return NewsFragment()
        }
    }
}
