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
package com.aykutasil.modernapp.util.livedata

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
