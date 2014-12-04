package com.chaophrayaboat.models;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class QuayManager {
	private static final String TAG = "QuayManager";
	private static List<Quay> quays;

	public static List<Quay> getQuaysList(Context context) {
		if (quays == null) {
			quays = new ArrayList<Quay>(Arrays.asList(getQuays(context)));
		}
		return quays;
	}

	public static List<String> getQuayNamesWithId(Context context, String spinnerText) {
		List<String> names = new ArrayList<String>();
		if (spinnerText != null) {
			names.add(spinnerText);
		}
		List<Quay> qys = getQuaysList(context);
		for (Quay q : qys) {
			names.add(String.format("%s - %s", q.id, q.nameEn));
		}
		return names;
	}
	public static Quay[] getQuays(Context context) {
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		return (gson).fromJson(loadJSONFromAsset(context), Quay[].class);
	}

	public static LatLng[] getPolylinePoints(Context context, String startId, String endId) {
		List<LatLng> lls = new ArrayList<LatLng>();
		List<Quay> qys = getQuaysList(context);
		int start = 0;
		int end = 0;
		for (int i = 0; i < qys.size(); i++) {
			Quay q = qys.get(i);
			if (q.id.equals(startId)) {
				Log.i(TAG + " START", i + "");
				start = i;
			}
			if (q.id.equals(endId)) {
				Log.i(TAG + " END", i + "");
				end = i;
			}
		}
		if (start < end) {
			for (int i = start; i <= end; i++) {
				lls.add(qys.get(i).getLatLng());
			}
		} else {
			for (int i = end; i <= start; i++) {
				lls.add(qys.get(i).getLatLng());
			}
		}
		return lls.toArray(new LatLng[0]);
	}

	public static Quay[] getQuayPoints(Context context, String startId, String endId) {
		List<Quay> qys = getQuaysList(context);
		List<Quay> qyls = new ArrayList<Quay>();
		
		int start = 0;
		int end = 0;
		for (int i = 0; i < qys.size(); i++) {
			Quay q = qys.get(i);
			if (q.id.equals(startId)) {
				Log.i(TAG + " START", i + "");
				start = i;
			}
			if (q.id.equals(endId)) {
				Log.i(TAG + " END", i + "");
				end = i;
			}
		}
		if (start < end) {
			for (int i = start; i <= end; i++) {
				qyls.add(qys.get(i));
			}
		} else {
			for (int i = end; i <= start; i++) {
				qyls.add(qys.get(i));
			}
		}
		return qyls.toArray(new Quay[0]);
	}
	
	public static String loadJSONFromAsset(Context context) {
		String json = null;
		try {
			InputStream is = context.getAssets().open("data.json");
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
