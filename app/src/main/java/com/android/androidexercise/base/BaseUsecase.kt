package com.android.androidexercise.base

import com.android.androidexercise.data.ErrorModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * base implementation for api call
 */
abstract class BaseUsecase<T> : BaseUsecaseContract<T> {

    @Volatile
    final override var isCompleted = false

    @Volatile
    final override var isFailed = false
    private var disposable: Disposable? = null
    private var mvpView: BaseView? = null
    private var retryNumber: Long = 3 //trying 3 times

    protected abstract fun createObservable(): Single<T>

    //canceling api call
    override fun cancel() {
        if (disposable != null && !disposable!!.isDisposed)
            disposable!!.dispose()
        isCompleted = false
        isFailed = false
        disposable = null
    }

    // api is in progress
    override var isInProgress =
        !isCompleted && !isFailed && disposable != null && !disposable!!.isDisposed

    /**
     * onSuccess: implementation for successful api call
     * onError: implementation for unsuccessful api call
     * onFinish: implementation when api call is complete regardless success or not
     */
    override fun observe(
        onSuccess: (T) -> Unit,
        onError: (errorModel: ErrorModel) -> Unit,
        onFinish: () -> Unit
    ): BaseUsecase<T> {
        isCompleted = false
        isFailed = false
        createObservable()
            .retry(retryNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally(onFinish)
            .subscribe(object : SingleObserver<T> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onSuccess(t: T) {
                    onSuccess(t)
                    isCompleted = true
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    isCompleted = false
                    isFailed = true
                    onError(
                        ErrorModel(Exception(e))
                    )
                }
            })
        return this
    }

    /**
     * adding api call the the CompositeDisposable, cancel calls when necessary.
     */
    fun <V : BaseView, T : BasePresenter<V>> register(presenter: T): Disposable? {
        mvpView = presenter.getMvpView()
        disposable?.run {
            presenter.addToDisposables(this)
        }
        return this.disposable
    }

    fun toDisposable() = disposable
}