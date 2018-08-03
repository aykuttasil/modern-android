package aykuttasil.com.modernapp.util.livedata

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import android.os.FileObserver
import java.io.File

/**
 * Created by aykutasil on 27.12.2017.
 */
class LiveFile(private val context: Context) : LiveData<List<String>>() {
    private val fileObserver: FileObserver

    init {
        val path = File(context.filesDir, "users.txt").path
        fileObserver = object : FileObserver(path) {
            override fun onEvent(event: Int, path: String?) {
                // The file has changed, so let’s reload the data
                loadData()
            }
        }
        loadData()
    }

    override fun onActive() {
        fileObserver.startWatching()
    }

    override fun onInactive() {
        fileObserver.stopWatching()
    }

    @SuppressLint("StaticFieldLeak")
    private fun loadData() {
        object : AsyncTask<Void, Void, List<String>>() {
            override fun doInBackground(vararg voids: Void): List<String> {
                val file = File(context.filesDir, "downloaded.json")
                return file.readLines()
            }

            override fun onPostExecute(data: List<String>) {
                value = data
            }
        }.execute()
    }
}