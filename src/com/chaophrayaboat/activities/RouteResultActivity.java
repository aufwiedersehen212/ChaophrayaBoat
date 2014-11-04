package com.chaophrayaboat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.chaophrayaboat.R;
import com.chaophrayaboat.fragments.RouteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RouteResultActivity extends ActionBarActivity {

	private static final String TAG = "RouteResultActivity";

	private MapFragment mMapFragment;
	private GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route_result);

		setUpMapIfNeeded();
		Log.i(TAG, mMap.toString());

		// startTextView.setText(intent.getStringExtra(RouteFragment.EXTRA_START));
		// destinationTextView.setText(intent.getStringExtra(RouteFragment.EXTRA_DESTINATION));
	}

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				// The Map is verified. It is now safe to manipulate the map.
				// Construct a CameraPosition focusing on Mountain View and
				// animate the camera to that position.
				LatLng MOUNTAIN_VIEW = new LatLng(37.4, -122.1);
				CameraPosition cameraPosition = new CameraPosition.Builder().target(MOUNTAIN_VIEW).zoom(17) // Sets
																											// the
																											// zoom
						.bearing(90) // Sets the orientation of the camera to
										// east
						.tilt(30) // Sets the tilt of the camera to 30 degrees
						.build(); // Creates a CameraPosition from the builder
				mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
				Intent intent = getIntent();
				Log.i(TAG, intent.getStringExtra(RouteFragment.EXTRA_START));
				Log.i(TAG, intent.getStringExtra(RouteFragment.EXTRA_DESTINATION));

				// mMap.addMarker(new MarkerOptions().position(new LatLng(10,
				// 10)).title("Hello world"));
				mMap.addMarker(new MarkerOptions().position(MOUNTAIN_VIEW).title("Hello world"));
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
