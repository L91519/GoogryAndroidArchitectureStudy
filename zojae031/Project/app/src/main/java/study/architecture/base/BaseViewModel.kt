package study.architecture.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel {
    protected val compositeDisposable = CompositeDisposable()

    open fun onPause() {
        compositeDisposable.clear()
    }

    abstract fun onResume()
}