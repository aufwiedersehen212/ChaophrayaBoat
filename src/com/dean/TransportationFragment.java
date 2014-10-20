package com.dean;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class TransportationFragment extends Fragment {
	private static final String TAG = "TransportationFragment";
	private ExpandableListView expandableListView;
	private ExpandableListAdapter adapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_transportation, container, false);
		expandableListView = (ExpandableListView) rootView.findViewById(R.id.listview);
		setupList();
		return rootView;
	}

	private void setupList() {
		List<Quay> quaysList = QuayManager.getQuaysList(getActivity());
		adapter = new ExpandableListAdapter(getActivity(), expandableListView, quaysList);
		expandableListView.setAdapter(adapter);
	}
}