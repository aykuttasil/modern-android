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

import androidx.lifecycle.LiveData
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * Created by aykutasil on 27.12.2017.
 */

// This LiveData emits NetWork information when network availability status changes and there is an active observer to it
class ConnectivityLiveData constructor(context: Context) : LiveData<Network>() {
    private val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val listener = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            // this part runs on background thread so use postValue
            postValue(network)
        }

        override fun onLost(network: Network) {
            postValue(network)
        }
    }

    init {
        // get connectivity system service
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onActive() {
        // onActive is called when there is an active observer to this LiveData
        // since active LiveData observers are there, add network callback listener to connectivity manager
        connectivityManager.registerDefaultNetworkCallback(listener)
    }

    override fun onInactive() {
        // onActive is called when there is no active observer to this LiveData
        // as no active observers exist, remove netwrok callback from connectivity manager
        connectivityManager.unregisterNetworkCallback(listener)
    }
}
