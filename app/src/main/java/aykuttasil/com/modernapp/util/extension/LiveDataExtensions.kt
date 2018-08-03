package aykuttasil.com.modernapp.util.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.Observer
import io.reactivex.Flowable

fun <T> LiveData<T>.reObserve(owner: LifecycleOwner, observer: Observer<T>) {
    removeObserver(observer)
    observe(owner, observer)
}

fun <T> LiveData<T>.toFlowable(owner: LifecycleOwner): Flowable<T> {
    return Flowable.fromPublisher(LiveDataReactiveStreams.toPublisher(owner, this))
}