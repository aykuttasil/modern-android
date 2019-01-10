package aykuttasil.com.modernapp.util

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import javax.inject.Provider

inline fun <reified T : Activity> createFakeActivityInjectorX(crossinline block: T.() -> Unit)
    : DispatchingAndroidInjector<Activity> {
    val injector = AndroidInjector<Activity> { instance ->
        if (instance is T) {
            instance.block()
        }
    }
    val factory = AndroidInjector.Factory<Activity> { injector }
    val map = mapOf(
        Pair<Class<out Activity>, Provider<AndroidInjector.Factory<out Activity>>>(
            T::class.java,
            Provider { factory })
    )
    return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(map)
}

inline fun <reified T : Fragment> createFakeFragmentInjectorX(crossinline block: T.() -> Unit)
    : DispatchingAndroidInjector<Fragment> {
    val injector = AndroidInjector<Fragment> { instance ->
        if (instance is T) {
            instance.block()
        }
    }
    val factory = AndroidInjector.Factory<Fragment> { injector }
    val map = mapOf(
        Pair<Class<out Fragment>, Provider<AndroidInjector.Factory<out Fragment>>>(
            T::class.java,
            Provider { factory })
    )
    return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(map)
}