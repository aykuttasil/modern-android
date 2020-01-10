package com.aykutasil.network.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

object InternetConnection {


  private fun preAndroidMInternetCheck(
      connectivityManager: ConnectivityManager): Boolean {
    val activeNetwork = connectivityManager.activeNetworkInfo
    if (activeNetwork != null) {
      return (activeNetwork.type == ConnectivityManager.TYPE_WIFI ||
          activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
    }
    return false
  }

  @RequiresApi(Build.VERSION_CODES.M)
  private fun postAndroidMInternetCheck(
      connectivityManager: ConnectivityManager): Boolean {
    val network = connectivityManager.activeNetwork
    val connection =
        connectivityManager.getNetworkCapabilities(network)

    return connection != null && (
        connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
  }

  fun isConnectionOn(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as
            ConnectivityManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      postAndroidMInternetCheck(connectivityManager)
    } else {
      preAndroidMInternetCheck(connectivityManager)
    }
  }

  fun isInternetAvailable(): Boolean {
    return try {
      val timeoutMs = 1500
      val sock = Socket()
      val sockaddr = InetSocketAddress("8.8.8.8", 53)

      sock.connect(sockaddr, timeoutMs)
      sock.close()

      true
    } catch (e: IOException) {
      false
    }
  }

}