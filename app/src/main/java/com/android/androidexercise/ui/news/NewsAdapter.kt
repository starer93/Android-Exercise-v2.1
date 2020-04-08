package com.android.androidexercise.ui.news

import android.graphics.drawable.Drawable
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
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
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
            if (news.imageHref == null) // hide image if there is no image
            {
                imgNews.visibility = View.GONE
            } else {
                imgNews.visibility = View.VISIBLE
                Glide.with(itemView.context).load(news.imageHref).diskCacheStrategy(
                    DiskCacheStrategy.ALL
                ).listener(
                    object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            imgNews.visibility = View.GONE  // hide image if loading image failed
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }
                    }

                ).into(imgNews)
            }
            txtDescription.text = news.getDescriptionWithDefaultText()

        }

    }

}