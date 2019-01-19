package com.aykutasil.modernapp.util;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * A value holder that automatically clears the reference if the Fragment's view is destroyed.
 *
 * @param <T>
 */
public class AutoClearedValue<T> {
  private T value;

  public AutoClearedValue(final Fragment fragment, T value) {
    final FragmentManager fragmentManager = fragment.getFragmentManager();
    assert fragmentManager != null;
    fragmentManager.registerFragmentLifecycleCallbacks(
        new FragmentManager.FragmentLifecycleCallbacks() {
          @Override
          public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
            if (f == fragment) {
              AutoClearedValue.this.value = null;
              fragmentManager.unregisterFragmentLifecycleCallbacks(this);
            }
          }
        },
        false);
    this.value = value;
  }

  public T get() {
    return value;
  }
}
