package com.chaophrayaboat.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.chaophrayaboat.R;
import com.chaophrayaboat.fragments.RouteFragment;
import com.chaophrayaboat.models.Quay;
import com.chaophrayaboat.models.QuayManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;

public class RouteResultActivity extends ActionBarActivity {
	private static final String TAG = "RouteResultActivity";

	private GoogleMap mMap;
	private Quay mStart;
	private Quay mDestination;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route_result);
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				// The Map is verified. It is now safe to manipulate the map.
				Intent intent = getIntent();
				String startStr = intent.getStringExtra(RouteFragment.EXTRA_START);
				mStart = (new Gson()).fromJson(startStr, Quay.class);
				String destinationStr = intent.getStringExtra(RouteFragment.EXTRA_DESTINATION);
				mDestination = (new Gson()).fromJson(destinationStr, Quay.class);
				mMap.addMarker(new MarkerOptions().position(mStart.getLatLng()).title(mStart.nameEn)
						.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
				Log.i(TAG, "Start " + mStart.getLatLng());
				mMap.addMarker(new MarkerOptions().position(mDestination.getLatLng()).title(mDestination.nameEn));
				Log.i(TAG, "Start " + mDestination.getLatLng());
				CameraPosition cameraPosition = new CameraPosition.Builder().target(mStart.getLatLng()).zoom(14)
						.bearing(90).build();
				mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
				mMap.addPolyline(new PolylineOptions()
						.add(QuayManager.getPolylinePoints(getApplicationContext(), mStart.id, mDestination.id))
						.width(2).color(Color.RED));
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.route_result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
