package com.chaophrayaboat.adapters;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chaophrayaboat.models.Quay;

public class RouteSpinnerAdapter extends ArrayAdapter<Quay> {
	private String desiredText;

	public RouteSpinnerAdapter(Context context, List<Quay> objects, String desiredText) {
		super(context, android.R.layout.simple_list_item_2, objects);
		this.desiredText = desiredText;
	}

	@Override
	// don't override if you don't want the default spinner to be a two line
	// view
	public View getView(int position, View convertView, ViewGroup parent) {
		return initView(position, convertView);
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return initView(position, convertView);
	}

	// for the header
	public int getCount() {
		return super.getCount() + 1;
	}

	private View initView(int position, View convertView) {
		if (convertView == null) {
			convertView = View.inflate(getContext(), android.R.layout.simple_list_item_2, null);
		}

		TextView tvText1 = (TextView) convertView.findViewById(android.R.id.text1);
		TextView tvText2 = (TextView) convertView.findViewById(android.R.id.text2);
		if (position == 0) {
			tvText1.setText(desiredText);
			tvText2.setText("");
			return convertView;
		}

		tvText1.setText(getItem(position - 1).getEnglishNameWithId());
		tvText2.setText(getItem(position - 1).getThaiNameWithId());
		return convertView;
	}
}