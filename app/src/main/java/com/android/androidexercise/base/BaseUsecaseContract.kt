package com.android.androidexercise.base

import com.android.androidexercise.data.ErrorModel

interface BaseUsecaseContract<T> {
    val isCompleted: Boolean
    val isInProgress: Boolean
    val isFailed: Boolean
    fun cancel()
    fun observe(
        onSuccess: (T) -> Unit,
        onError: (errorModel: ErrorModel) -> Unit,
        onFinish: () -> Unit = {}
    ): BaseUsecase<T>
}