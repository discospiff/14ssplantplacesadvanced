package com.plantplaces.plantplaces14ssadvanced;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class PlantSearchFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
	// 	we are associating a layout with our fragment
		View layout = inflater.inflate(R.layout.plantsearchfragmentlayout, container, false);
		
		// Get access to our button.
		Button btnSearchFragment = (Button) layout.findViewById(R.id.btnSearchFragment);
	
		// assign a button listener.
		btnSearchFragment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i = 1 + 1;
			}
		
		});
		
		return layout;
		
	}

	
}
