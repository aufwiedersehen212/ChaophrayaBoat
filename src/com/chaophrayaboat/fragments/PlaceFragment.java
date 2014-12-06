package com.chaophrayaboat.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.chaophrayaboat.R;
import com.chaophrayaboat.adapters.ExpandableListAdapter;
import com.chaophrayaboat.models.Quay;
import com.chaophrayaboat.models.QuayManager;

public class PlaceFragment extends Fragment {

	private AutoCompleteTextView autoCompleteTextView;
	private ExpandableListView expandableListView;
	private ExpandableListAdapter adapter;
	private ArrayAdapter<String> autoCompleteAdapter;

	public void onResume() {
		super.onResume();
		if (expandableListView != null) {
			expandableListView.setSelection(0);
			int count = adapter.getGroupCount();
			for (int i = 0; i < count; i++)
				expandableListView.collapseGroup(i);
		}
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_place, container,
				false);
		expandableListView = (ExpandableListView) rootView
				.findViewById(R.id.listview);
		autoCompleteTextView = (AutoCompleteTextView) rootView
				.findViewById(R.id.autoCompleteTextView);

		setupList(null);
		setAutoComplete();
		setHasOptionsMenu(true);
		return rootView;
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_search, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	private void setupList(String name) {
		List<Quay> quaysList = QuayManager.getQuaysList(getActivity());
		if (name != null) {
			Quay quay = QuayManager.getQuayWithName(getActivity(), name);
			quaysList = new ArrayList<Quay>();
			quaysList.add(quay);
		}

		adapter = new ExpandableListAdapter(getActivity(), expandableListView,
				quaysList, ExpandableListAdapter.Mode.PLACE);
		expandableListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();

	}

	private void setAutoComplete() {
		String[] quaysNames = QuayManager.getQuayNames(getActivity());

		autoCompleteAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, quaysNames);
		autoCompleteTextView.setAdapter(autoCompleteAdapter);
		autoCompleteTextView.setThreshold(1);
		autoCompleteTextView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1,
					int index, long id) {
				String place = autoCompleteAdapter.getItem(index);
				setupList(place);
			}

		});
		autoCompleteTextView
				.setOnEditorActionListener(new OnEditorActionListener() {
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {

						if (v.getText().toString().isEmpty()) {
							setupList(null);
						}					

						return true;
					}
				});
	}
}
