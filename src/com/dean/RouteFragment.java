package com.dean;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

public class RouteFragment extends Fragment implements OnClickListener {

	private View rootView;
	private Spinner startSpinner;
	private Spinner destinationSpinner;
	private ImageButton searchButton;

	ArrayAdapter<CharSequence> startAdapter;
	ArrayAdapter<CharSequence> destinationAdapter;

	private String start;
	private String destination;

	public static final String EXTRA_START = "start";
	public static final String EXTRA_DESTINATION = "destination";

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_route, container, false);
		setupStartSpinner();
		setupDestinationSpinner();
		setupSearchButton();
		return rootView;
	}

	private void setupStartSpinner() {
		startSpinner = (Spinner) rootView.findViewById(R.id.start_spinner);
		startAdapter = ArrayAdapter.createFromResource(getActivity(),
				R.array.start_array, android.R.layout.simple_spinner_item);
		startAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		startSpinner.setAdapter(startAdapter);
		startSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				start = (String) startAdapter.getItem(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		start = startAdapter.getItem(0).toString();
	}

	private void setupDestinationSpinner() {
		destinationSpinner = (Spinner) rootView
				.findViewById(R.id.destination_spinner);
		destinationAdapter = ArrayAdapter
				.createFromResource(getActivity(), R.array.destination_array,
						android.R.layout.simple_spinner_item);
		destinationAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		destinationSpinner.setAdapter(destinationAdapter);
		destinationSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						destination = (String) destinationAdapter
								.getItem(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
		destination = destinationAdapter.getItem(0).toString();
	}

	private void setupSearchButton() {
		searchButton = (ImageButton) rootView.findViewById(R.id.search_button);
		searchButton.setOnClickListener(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (startSpinner != null && destinationSpinner != null) {
			startSpinner.setSelection(0);
			destinationSpinner.setSelection(0);
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(getActivity(), RouteResultActivity.class);
		intent.putExtra(EXTRA_START, start);
		intent.putExtra(EXTRA_DESTINATION, destination);
		startActivity(intent);
	}

}
