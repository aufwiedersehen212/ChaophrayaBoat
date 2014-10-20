package com.dean;

import uk.co.senab.photoview.PhotoViewAttacher;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MapFragment extends Fragment {

	ImageView mapImageView;
	PhotoViewAttacher mAttacher;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_map, container,
				false);

		mapImageView = (ImageView) rootView.findViewById(R.id.map_image);
		mAttacher = new PhotoViewAttacher(mapImageView);
		mAttacher.update();

		return rootView;
	}

}
