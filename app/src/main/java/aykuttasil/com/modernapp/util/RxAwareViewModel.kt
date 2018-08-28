package aykuttasil.com.modernapp.util

import androidx.lifecycle.AndroidViewModel
import aykuttasil.com.modernapp.App
import io.reactivex.disposables.CompositeDisposable

open class RxAwareViewModel(app: App) : AndroidViewModel(app) {

    var compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}