package com.plantplaces.plantplaces14ssadvanced;

import java.util.ArrayList;
import java.util.List;

import android.location.Location;
import android.os.AsyncTask;
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
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.plantplaces.plantplaces14ssadvanced.dao.IPlantDAO;
import com.plantplaces.plantplaces14ssadvanced.dao.OnlinePlantDAO;
import com.plantplaces.plantplaces14ssadvanced.dto.Specimen;

public class MapOfPlants extends FragmentActivity implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener, OnInfoWindowClickListener {

	private GoogleMap map;
	private LocationRequest request = LocationRequest.create().setInterval(5000).setFastestInterval(16).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	private LocationClient locationClient;
	private IPlantDAO plantDAO;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		// wire up with our map fragment
		setContentView(R.layout.mapv2fragment);

		// setup our plant DAO.
		plantDAO = new OnlinePlantDAO();
		
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
		
		// find specimens at this location.
		PlantSearchTask pst = new PlantSearchTask();
		pst.execute(location.getLatitude(), location.getLongitude());
	}

	class PlantSearchTask extends AsyncTask<Double, Integer, List<Specimen>> {
		
		@Override
		protected void onPostExecute(List<Specimen> result) {
			// given a list of specimens, add markers to our map.
			
			// iterate over the collection of specimens.
			for (Specimen specimen : result) {
				// make an LatLng object to represent this position on a map.
				LatLng position = new LatLng(specimen.getLatitude(), specimen.getLongitude());
				map.addMarker(new MarkerOptions().position(position)
						.title("[" + specimen.getPlantId() + "]" + specimen.getDescription())
						.snippet(specimen.getAddress())
						);
			}
		}

		@Override
		protected List<Specimen> doInBackground(Double... params) {
			List<Specimen> allSpecimens = new ArrayList<Specimen>();
			try {
				allSpecimens = plantDAO.fetchSpecimensByLocation(params[0], params[1]);
			} catch (Exception e) {
				Toast.makeText(MapOfPlants.this, "Error getting data", Toast.LENGTH_LONG).show();
			}
			return allSpecimens;
		}
		
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		// TODO Auto-generated method stub
		String title = marker.getTitle();
		int indexOfOpen = title.indexOf("[");
		int indexOfClose = title.indexOf("]");
		// get the plant ID of the marker that was clicked.
		String plantId = title.substring(indexOfOpen+1, indexOfClose);
		
		
	}
	
}
