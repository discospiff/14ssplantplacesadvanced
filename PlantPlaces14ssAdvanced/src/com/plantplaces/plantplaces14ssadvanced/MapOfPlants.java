package com.plantplaces.plantplaces14ssadvanced;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class MapOfPlants extends FragmentActivity implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

	private GoogleMap map;
	private LocationRequest request = LocationRequest.create().setInterval(5000).setFastestInterval(16).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	private LocationClient locationClient;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		// wire up with our map fragment
		setContentView(R.layout.mapv2fragment);

		setupMapIfRequired();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		setUpLocationClientIfRequired();
		locationClient.connect();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		if (locationClient != null) {
			// unsubscribe
			locationClient.disconnect();
		}
	}
	
	private void setUpLocationClientIfRequired() {
		if (locationClient == null) {
			locationClient = new LocationClient(this, this, this);
		}
		
	}

	private void setupMapIfRequired() {
		if (map == null) {
			// get access to the map from the fragment.
			MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
			map = mapFragment.getMap();

			// if we got a map at this point, set my location avaiable.
			if (map != null) {
				map.setMyLocationEnabled(true);
				
			}
			
		}

	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		// marry together our Location Request and our Location Listener which will receive updates.
		locationClient.requestLocationUpdates(request, this);
		
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLocationChanged(Location location) {
		Toast.makeText(this, "Location: " + location.getLatitude() + " Longitude: " + location.getLongitude(), Toast.LENGTH_LONG).show();
		
	}

}
