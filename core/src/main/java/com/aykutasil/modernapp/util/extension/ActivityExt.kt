package com.aykutasil.modernapp.util.extension

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aykutasil.modernapp.BuildConfig

/**
 * Return whether Keyboard is currently visible on screen or not.
 *
 * @return true if keyboard is visible.
 */
fun Activity.isKeyboardVisible(): Boolean {
  val r = Rect()

  //r will be populated with the coordinates of your view that area still visible.
  window.decorView.getWindowVisibleDisplayFrame(r)

  //get screen height and calculate the difference with the usable area from the r
  val height = getDisplaySize().y
  val diff = height - r.bottom

  // If the difference is not 0 we assume that the keyboard is currently visible.
  return diff != 0
}

/**
 * Extension method to provide hide keyboard for [Activity].
 */
fun Activity.hideSoftKeyboard() {
  if (currentFocus != null) {
    val inputMethodManager = getSystemService(
      Context
        .INPUT_METHOD_SERVICE
    ) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
  }
}

fun Activity.hideKeyboard() {
  val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
}

fun AppCompatActivity.setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {
  setSupportActionBar(findViewById(toolbarId))
  supportActionBar?.run {
    action()
  }
}

inline fun AppCompatActivity.debug(block: () -> Unit) {
  if (BuildConfig.DEBUG) {
    block()
  }
}

fun AppCompatActivity.replaceFragment(containerId: Int, fragment: Fragment) {
  supportFragmentManager.transaction { replace(containerId, fragment) }
}

fun AppCompatActivity.addFragment(containerId: Int, fragment: Fragment) {
  supportFragmentManager.transaction { add(containerId, fragment) }
}


