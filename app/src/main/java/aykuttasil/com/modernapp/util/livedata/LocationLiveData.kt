package aykuttasil.com.modernapp.util.livedata

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import android.content.Context
import android.location.Location
import android.util.Log
import aykuttasil.com.modernapp.data.DataManager
import aykuttasil.com.modernapp.data.local.entity.LocationEntity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*
import javax.inject.Inject


/**
 * Created by aykutasil on 27.12.2017.
 */
class LocationLiveData @Inject constructor(var context: Context, var dataManager: DataManager) : LiveData<Location>(), AnkoLogger {

    private var fusedLocationProvider = FusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        info("LocationLiveData -> onActive")
        fusedLocationProvider.locationAvailability.addOnSuccessListener {
            if (it.isLocationAvailable) {
                fusedLocationProvider.requestLocationUpdates(createLocationRequest(), locationCallback, null)

                /*
                fusedLocationProvider.lastLocation.addOnSuccessListener {
                    value = it
                }
                */
            }
        }
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult?.locations?.forEach {
                Log.i("aaa", it.toString())
                // value = it

                val locationEntity = LocationEntity(locLat = it.latitude, locLong = it.longitude, locTime = Date())
                dataManager.addLocation(locationEntity)
            }
        }
    }

    private fun createLocationRequest(): LocationRequest {
        return LocationRequest().apply {
            interval = 6000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    override fun onInactive() {
        super.onInactive()
        info("LocationLiveData -> onInactive")
        fusedLocationProvider.removeLocationUpdates(locationCallback)
    }
}