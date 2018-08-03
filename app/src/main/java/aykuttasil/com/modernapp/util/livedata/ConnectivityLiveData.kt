package aykuttasil.com.modernapp.util.livedata

import androidx.lifecycle.LiveData
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * Created by aykutasil on 27.12.2017.
 */

//This LiveData emits NetWork information when network availability status changes and there is an active observer to it
class ConnectivityLiveData constructor(context: Context) : LiveData<Network>() {
    private val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val listener = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            //this part runs on background thread so use postValue
            postValue(network)
        }

        override fun onLost(network: Network) {
            postValue(network)
        }
    }

    init {
        //get connectivity system service
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onActive() {
        //onActive is called when there is an active observer to this LiveData
        //since active LiveData observers are there, add network callback listener to connectivity manager
        connectivityManager.registerDefaultNetworkCallback(listener)
    }

    override fun onInactive() {
        //onActive is called when there is no active observer to this LiveData
        //as no active observers exist, remove netwrok callback from connectivity manager
        connectivityManager.unregisterNetworkCallback(listener)
    }
}