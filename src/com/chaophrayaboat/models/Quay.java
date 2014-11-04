package com.chaophrayaboat.models;

import java.util.List;

import com.google.android.gms.maps.model.LatLng;

public class Quay {
	public String id;
	public String nameTh;
	public String nameEn;
	public List<String> otherTransportations;
	public List<Flag> flags;
	public List<String> nearbyPlaces;
	public String lat;
	public String lng;

	class Flag {
		public String flag;
		public String fee;
	}

	@Override
	public String toString() {
		return this.getEnglishNameWithId();
	}

	public String getEnglishNameWithId() {
		return String.format("%s - %s", id, nameEn);
	}

	public String getThaiNameWithId() {
		return String.format("%s - %s", id, nameTh);
	}

	public LatLng getLatLng() {
		return new LatLng(Double.parseDouble(this.lat), Double.parseDouble(this.lng));
	}

}
