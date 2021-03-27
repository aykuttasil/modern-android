package com.aykuttasil.modernapp.util.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat

/**
 * Extension method to display Width for Context.
 */
fun Context.displayWidth(): Int = getDisplaySize().x

/**
 * Retrieve a decoded bitmap from resources, or null if the image could not be decoded.
 */
fun Context.decodeBitmap(resId: Int): Bitmap? = BitmapFactory.decodeResource(resources, resId)

@RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isNetworkStatusAvailable(): Boolean {
  val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
  connectivityManager?.let { connectivity ->
    connectivity.activeNetworkInfo?.let {
      if (it.isConnected) return true
    }
  }
  return false
}

/**
 * Method used to easily retrieve [WindowManager] from [Context].
 */
fun Context.getWindowManager() = getSystemService(Context.WINDOW_SERVICE) as WindowManager

/**
 * Method used to easily retrieve display size from [Context].
 */
fun Context.getDisplaySize() = Point().apply {
  getWindowManager().defaultDisplay.getSize(this)
}

/**
 * Extension method to provide quicker access to the [LayoutInflater] from [Context].
 */
fun Context.getLayoutInflater() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

/**
 * Extension method to provide simpler access to {@link ContextCompat#getDrawableCompat(int)}.
 */
fun Context.getDrawableCompat(drawableResId: Int): Drawable? = ContextCompat
  .getDrawable(this, drawableResId)


/**
 * Extension method to provide simpler access to {@link ContextCompat#getColor(int)}.
 */
fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)



