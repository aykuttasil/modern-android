package com.aykutasil.modernapp.util.extension

import android.content.Context
import android.graphics.Point
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Extension method to set width for View.
 */
fun View.setWidth(value: Int) {
  val lp = layoutParams
  lp?.let {
    lp.width = value
    layoutParams = lp
  }
}


/**
 * Method used to easily retrieve display size from [View].
 */
fun View.getDisplaySize() = context.getDisplaySize()


/**
 * Extension method to provide show keyboard for View.
 */
fun View.gone() {
  if (visibility != View.GONE) {
    visibility = View.GONE
  }
}

/**
 * Extension method to provide show keyboard for View.
 */
fun View.visible() {
  if (visibility != View.VISIBLE) {
    visibility = View.VISIBLE
  }
}

/**
 * Extension method to return the view location on screen as a [Point].
 */
fun View.locationOnScreen(): Point {
  val location = IntArray(2)
  getLocationOnScreen(location)
  return Point(location[0], location[1])
}

/**
 * Extension method to return the view location in window as a [Point].
 */
fun View.locationInWindow(): Point {
  val location = IntArray(2)
  getLocationInWindow(location)
  return Point(location[0], location[1])
}

/**
 * Extension method to provide quicker access to the [LayoutInflater] from a [View].
 */
fun View.getLayoutInflater() = context.getLayoutInflater()

/**
 * Extension method to provide hide keyboard for [View].
 */
fun View.hideKeyboard() {
  val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  imm.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * Extension method to provide simpler access to {@link View#getResources()#getString(int)}.
 */
fun View.getString(stringResId: Int): String = resources.getString(stringResId)

/**
 * Extension method to provide show keyboard for View.
 */
fun View.showKeyboard() {
  val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  this.requestFocus()
  imm.showSoftInput(this, 0)
}



