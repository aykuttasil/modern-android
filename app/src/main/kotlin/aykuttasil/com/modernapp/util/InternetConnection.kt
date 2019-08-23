package aykuttasil.com.modernapp.util

import android.os.AsyncTask
import androidx.annotation.WorkerThread
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

/**
 * - https://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-times-out?page=1&tab=votes#tab-top
 */
object InternetConnection {

  /**
   * ICMP
   *  + could run on main thread
   *  - does not work on some old devices (Galays S3, etc.), it blocks a while if no internet is available.
   */
  fun isOnline(): Boolean {
    val runtime = Runtime.getRuntime()
    try {
      val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
      val exitValue = ipProcess.waitFor()
      return exitValue == 0
    } catch (e: IOException) {
      e.printStackTrace()
    } catch (e: InterruptedException) {
      e.printStackTrace()
    }

    return false
  }

  /**
   * TCP/HTTP/DNS (depending on the port, 53=DNS, 80=HTTP, etc.)
   * + very fast (either way), works on all devices, very reliable
   * - can't run on the UI thread
   */
  @WorkerThread
  fun isOnlineWorkBackground(): Boolean {
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

/**
 * sample using
InternetCheck(object : InternetCheck.Consumer {
override fun accept(internet: Boolean) {

}
})
 */
class InternetCheck(private val mConsumer: Consumer) : AsyncTask<Void, Void, Boolean>() {

  interface Consumer {
    fun accept(internet: Boolean)
  }

  init {
    execute()
  }

  override fun doInBackground(vararg params: Void?): Boolean {
    try {
      val sock = Socket()
      sock.connect(InetSocketAddress("8.8.8.8", 53), 1500)
      sock.close()
      return true
    } catch (e: IOException) {
      return false
    }
  }

  override fun onPostExecute(internet: Boolean?) {
    mConsumer.accept(internet ?: false)
  }
}

/**
 * sample using
hasInternetConnection().subscribe({ hasInternet ->
}, {
}).addTo(compositeDisposable)
 */
fun hasInternetConnection(): Single<Boolean> {
  return Single.fromCallable {
    try {
      // Connect to Google DNS to check for connection
      val timeoutMs = 1500
      val socket = Socket()
      val socketAddress = InetSocketAddress("8.8.8.8", 53)

      socket.connect(socketAddress, timeoutMs)
      socket.close()

      true
    } catch (e: IOException) {
      false
    }
  }
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
}
