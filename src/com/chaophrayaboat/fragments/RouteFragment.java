package com.chaophrayaboat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;

import com.chaophrayaboat.R;
import com.chaophrayaboat.activities.RouteResultActivity;
import com.chaophrayaboat.adapters.RouteSpinnerAdapter;
import com.chaophrayaboat.models.Quay;
import com.chaophrayaboat.models.QuayManager;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RouteFragment extends Fragment implements OnClickListener {
	private static final String TAG = "RouteFragment";
	private View rootView;
	private Spinner startSpinner;
	private Spinner destinationSpinner;
	private Button searchButton;
	private RouteSpinnerAdapter startAdapter;
	private RouteSpinnerAdapter destinationAdapter;

	private Quay start;
	private Quay destination;

	public static final String EXTRA_START = "start";
	public static final String EXTRA_DESTINATION = "destination";

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_route, container, false);
		setupStartSpinner();
		setupDestinationSpinner();
		setupSearchButton();
		return rootView;
	}

	private void setupStartSpinner() {
		startSpinner = (Spinner) rootView.findViewById(R.id.start_spinner);
		startAdapter = new RouteSpinnerAdapter(getActivity(), QuayManager.getQuaysList(getActivity()), "เริ่มต้น");
		startSpinner.setAdapter(startAdapter);
		startSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO: don't forget the header "เริ่มต้น"
				if (position > 0) {
					start = startAdapter.getItem(position - 1);
				} else {
					start = null;
				}
				changeSearchButtonState();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		// TODO: don't forget the header "เริ่มต้น"
		// start = startAdapter.getItem(1);
	}
	private void setupDestinationSpinner() {
		destinationSpinner = (Spinner) rootView.findViewById(R.id.destination_spinner);
		destinationAdapter = new RouteSpinnerAdapter(getActivity(), QuayManager.getQuaysList(getActivity()), "ปลายทาง");
		destinationSpinner.setAdapter(destinationAdapter);
		destinationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// / TODO: don't forget the "ปลายทาง"
				if (position > 0) {
					destination = destinationAdapter.getItem(position - 1);
				} else {
					destination = null;
				}
				changeSearchButtonState();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		// / TODO: don't forget the "ปลายทาง"
		// destination = destinationAdapter.getItem(1);
	}

	private void setupSearchButton() {
		searchButton = (Button) rootView.findViewById(R.id.search_button);
		searchButton.setOnClickListener(this);
		changeSearchButtonState();
	}

	@Override
	public void onResume() {
		super.onResume();
		if (startSpinner != null && destinationSpinner != null) {
			startSpinner.setSelection(0);
			destinationSpinner.setSelection(0);
		}
	}

	private void changeSearchButtonState() {
		this.searchButton.setEnabled((start != null && destination != null) && (!start.id.equals(destination.id)));
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(getActivity(), RouteResultActivity.class);
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		String startStr = gson.toJson(start);
		String destinationStr = gson.toJson(destination);
		intent.putExtra(EXTRA_START, startStr);
		intent.putExtra(EXTRA_DESTINATION, destinationStr);
		startActivity(intent);
	}
}
