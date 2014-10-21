package com.chaophrayaboat.models;

import java.util.List;

public class Quay {
	public String id;
	public String nameTh;
	public String nameEn;
	public List<String> otherTransportations;
	public List<Flag> flags;
	public List<String> nearbyPlaces;

	class Flag {
		public String flag;
		public String fee;
	}

	public String getEnglishNameWithId() {
		return String.format("%s - %s", id, nameEn);
	}

	public String getThaiNameWithId() {
		return String.format("%s - %s", id, nameTh);
	}

}
