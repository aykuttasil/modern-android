package aykuttasil.com.modernapp.util.livedata;

/*
public abstract class LocationLiveData extends LiveData<Location> implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private GoogleApiClient googleApiClient;

    public LocationLiveData(Context context) {
        googleApiClient =
                new GoogleApiClient.Builder(context, this, this)
                        .addApi(LocationServices.API)
                        .build();
    }

    @Override
    protected void onActive() {
        // Wait for the GoogleApiClient to be connected
        googleApiClient.connect();
    }

    @Override
    protected void onInactive() {
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    googleApiClient, this);
        }
        googleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        // Try to immediately find a location
        Location lastLocation = LocationServices.FusedLocationApi
                .getLastLocation(googleApiClient);
        if (lastLocation != null) {
            setValue(lastLocation);
        }
        // Request updates if there’s someone observing
        if (hasActiveObservers()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    googleApiClient, new LocationRequest(), this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        // Deliver the location changes
        setValue(location);
    }

    @Override
    public void onConnectionSuspended(int cause) {
        // Cry softly, hope it comes back on its own
    }

    @Override
    public void onConnectionFailed(
            @NonNull ConnectionResult connectionResult) {
        // Consider exposing this state as described here:
        // https://d.android.com/topic/libraries/architecture/guide.html#addendum
    }
}
*/