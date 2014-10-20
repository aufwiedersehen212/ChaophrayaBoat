package com.dean;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private FragmentTabHost mTabHost;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_tabs, container,
					false);
			mTabHost = (FragmentTabHost) rootView
					.findViewById(android.R.id.tabhost);
			mTabHost.setup(getActivity(), getChildFragmentManager(),
					android.R.id.tabcontent);

			mTabHost.addTab(
					mTabHost.newTabSpec("route").setIndicator("Route"),
					RouteFragment.class, null);

			mTabHost.addTab(
					mTabHost.newTabSpec("map").setIndicator("Map"),
					MapFragment.class, null);
			
			mTabHost.addTab(
					mTabHost.newTabSpec("transportation").setIndicator("Transportation"),
					TransportationFragment.class, null);
			
			mTabHost.addTab(
					mTabHost.newTabSpec("place").setIndicator("Place"),
					PlaceFragment.class, null);

			return rootView;
		}
	}
}
