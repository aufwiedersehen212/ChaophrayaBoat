package com.dean;

import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TransportationFragment extends Fragment {
	private static final String TAG = "TransportationFragment";

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_transportation, container, false);
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		Boat[] boats = (gson).fromJson(loadJSONFromAsset(), Boat[].class);
		for (Boat b : boats) {
			Log.e(TAG, b.nameEn);
		}
		return rootView;
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