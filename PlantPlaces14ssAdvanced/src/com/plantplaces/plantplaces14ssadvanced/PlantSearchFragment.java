package com.plantplaces.plantplaces14ssadvanced;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class PlantSearchFragment extends Fragment {

	// declare our callback activity
	private CoordinateSearch coordinateSearch;
	private AutoCompleteTextView actSearchTerm;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
	// 	we are associating a layout with our fragment
		View layout = inflater.inflate(R.layout.plantsearchfragmentlayout, container, false);
		
		// Get access to our button.
		Button btnSearchFragment = (Button) layout.findViewById(R.id.btnSearchFragment);
	
		actSearchTerm = (AutoCompleteTextView) layout.findViewById(R.id.actSearchTerm);
		
//		// assign a button listener.
//		btnSearchFragment.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				int i = 1 + 1;
//			}
//		
//		});
		
		JeremysOnClickListener jocl = new JeremysOnClickListener();
		
		btnSearchFragment.setOnClickListener(jocl);
		
		return layout;
		
	}

	
	
	class JeremysOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// get the search term from the auto complete.
			String searchTerm = actSearchTerm.getText().toString();
			
			// invoke the callback method.
			coordinateSearch.search(searchTerm);
			
		}
		
	}
	
	/**
	 * Get a reference to the activity that will manage this fragment
	 */
	@Override
	public void onAttach(Activity activity) {
		// assign our callback activity
		coordinateSearch = (CoordinateSearch) activity;
		
		
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	
	/**
	 * Callback interface that allows two fragments to coordinate a search, by using an activity
	 * @author jonesbr
	 *
	 */
	interface CoordinateSearch {
		
		/**
		 * This method will be invoked when we click search on one fragment.  
		 * The method and interface should be implemented on the class that owns the fragments
		 * @param searchTerm
		 */
		public void search(String searchTerm);
		
	}
	
	
}
