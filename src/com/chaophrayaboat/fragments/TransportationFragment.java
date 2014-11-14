package com.chaophrayaboat.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.chaophrayaboat.R;
import com.chaophrayaboat.adapters.ExpandableListAdapter;
import com.chaophrayaboat.models.Quay;
import com.chaophrayaboat.models.QuayManager;

public class TransportationFragment extends Fragment {
	@SuppressWarnings("unused")
	private static final String TAG = "TransportationFragment";
	private ExpandableListView expandableListView;
	private ExpandableListAdapter adapter;

	public void onResume() {
		super.onResume();
		if (expandableListView != null) {
			expandableListView.setSelection(0);
			int count = adapter.getGroupCount();
			for (int i = 0; i < count; i++)
				expandableListView.collapseGroup(i);
		}
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_transportation, container, false);
		expandableListView = (ExpandableListView) rootView.findViewById(R.id.listview);
		setupList();
		setHasOptionsMenu(true);
		return rootView;
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_search, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	private void setupList() {
		List<Quay> quaysList = QuayManager.getQuaysList(getActivity());
		adapter = new ExpandableListAdapter(getActivity(), expandableListView, quaysList);
		expandableListView.setAdapter(adapter);
	}
}