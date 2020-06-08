package com.aykuttasil.common.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * A value holder that automatically clears the reference if the Fragment's view is destroyed.
 *
 * @param <T>
</T> */

class AutoClearedValue<T>(fragment: Fragment, private var value: T?) {

  init {
    val fragmentManager = fragment.parentFragmentManager
    fragmentManager.registerFragmentLifecycleCallbacks(
      object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
          if (f === fragment) {
            this@AutoClearedValue.value = null
            fragmentManager.unregisterFragmentLifecycleCallbacks(this)
          }
        }
      },
      false
    )
  }

  fun get(): T? {
    return value
  }
}
