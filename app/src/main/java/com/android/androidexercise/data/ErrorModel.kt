package com.android.androidexercise.data

/**
 * this should be custom error wrapper, however, currently, only Exception is provided a
 */
data class ErrorModel(
    val e: Exception?
//    val errorCode: Int,
//    val errorMessage: String
)