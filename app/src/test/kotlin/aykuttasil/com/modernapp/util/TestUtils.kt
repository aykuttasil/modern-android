package aykuttasil.com.modernapp.util

/*
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

    val stringMap = mapOf(
        Pair<String, Provider<AndroidInjector.Factory<*>>>(
            T::class.java.simpleName,
            Provider { factory })
    )

    return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(map, stringMap)
}
*/