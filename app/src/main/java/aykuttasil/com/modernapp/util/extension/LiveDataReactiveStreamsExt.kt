package aykuttasil.com.modernapp.util.extension

/**
 * Created by aykutasil on 27.12.2017.
 */
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import org.reactivestreams.Publisher

fun <T> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this)

fun <T> LiveData<T>.toPublisher(lifecycleOwner: LifecycleOwner)
        = LiveDataReactiveStreams.toPublisher(lifecycleOwner, this)