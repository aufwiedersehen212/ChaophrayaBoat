package com.dean;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class RouteResultActivity extends ActionBarActivity {

	private TextView startTextView;
	private TextView destinationTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route_result);

		startTextView = (TextView) findViewById(R.id.start_textview);
		destinationTextView = (TextView) findViewById(R.id.destination_textview);
		Intent intent = getIntent();

		startTextView.setText(intent.getStringExtra(RouteFragment.EXTRA_START));
		destinationTextView.setText(intent
				.getStringExtra(RouteFragment.EXTRA_DESTINATION));
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
