package com.dean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TransportationFragment extends Fragment {
	private static final String TAG = "TransportationFragment";
	private ExpandableListView expandableListView;
	private ExpandableListAdapter adapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_transportation, container, false);
		expandableListView = (ExpandableListView) rootView.findViewById(R.id.listview);
		Quay[] quays = getQuays();
		List<Quay> quaysList = new ArrayList<Quay>(Arrays.asList(quays));

		adapter = new ExpandableListAdapter(getActivity(), expandableListView, quaysList);

		expandableListView.setAdapter(adapter);
		return rootView;
	}
	public Quay[] getQuays() {
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		return (gson).fromJson(loadJSONFromAsset(), Quay[].class);
	}

	public String loadJSONFromAsset() {
		String json = null;
		try {
			InputStream is = getActivity().getAssets().open("data.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;
	}

}