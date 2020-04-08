package com.android.androidexercise.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.androidexercise.R
import com.android.androidexercise.data.response.News
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.util.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private val newsArrayList = ArrayList<News>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_news_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsArrayList[position])
    }

    override fun getItemCount(): Int {
        return newsArrayList.size
    }

    //add data into list
    fun setList(newsList: List<News>?) {
        newsArrayList.clear() // removing existing list
        if (newsList != null && newsList.isNotEmpty()) {
            newsArrayList.addAll(newsList)
            removeNullNew()
            notifyDataSetChanged()
        }
    }

    //remove null item
    private fun removeNullNew() {
        val newIterator = newsArrayList.iterator()
        for (i in newIterator) {
            if (i.isNull()) {
                newIterator.remove()
            }
        }
    }

    //viewholder for news
    inner class NewsViewHolder(itemView: View) : ViewHolder(itemView) {
        private var txtTitle: TextView = itemView.findViewById(R.id.txt_title)
        private val txtDescription: TextView = itemView.findViewById(R.id.txt_description)
        private val imgNews: ImageView = itemView.findViewById(R.id.img_news)

        fun bind(news: News) {
            txtTitle.text = news.title
            Glide.with(itemView.context).load(news.imageHref)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imgNews)
            txtDescription.text = news.getDescriptionWithDefaultText()

        }

    }

}