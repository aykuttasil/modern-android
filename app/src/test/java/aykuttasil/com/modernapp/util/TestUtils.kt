package aykuttasil.com.modernapp.util

import android.app.Activity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import javax.inject.Provider

inline fun <reified T : Activity> createFakeActivityInjector(crossinline block: T.() -> Unit)
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