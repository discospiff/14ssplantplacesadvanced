package com.plantplaces.plantplaces14ssadvanced;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MasterDetailActivity extends FragmentActivity implements PlantSearchFragment.CoordinateSearch {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.plantmasterdetail);
	}

	@Override
	public void search(String searchTerm) {
		// TODO Auto-generated method stub
		int i = 1 + 1;
	}
	
}
