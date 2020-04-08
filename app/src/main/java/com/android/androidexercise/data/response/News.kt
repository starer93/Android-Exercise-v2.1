package com.android.androidexercise.data.response

class News {
    var title: String? = null
    var description: String? = null
    var imageHref: String? = null

    fun getDescriptionWithDefaultText(): String {
        return if (description == null) {
            "no description"
        } else {
            description!!
        }
    }

    fun isNull(): Boolean {
        return title.isNullOrBlank() && description.isNullOrBlank() && imageHref.isNullOrBlank()
    }
}