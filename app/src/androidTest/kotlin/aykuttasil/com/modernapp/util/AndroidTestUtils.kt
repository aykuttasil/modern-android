/**
 * Designed and developed by Aykut Asil (@aykuttasil)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package aykuttasil.com.modernapp.util

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import javax.inject.Provider

inline fun <reified T : Activity> createFakeActivityInjectorX(crossinline block: T.() -> Unit): DispatchingAndroidInjector<Activity> {
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

inline fun <reified T : Fragment> createFakeFragmentInjectorX(crossinline block: T.() -> Unit): DispatchingAndroidInjector<Fragment> {
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
