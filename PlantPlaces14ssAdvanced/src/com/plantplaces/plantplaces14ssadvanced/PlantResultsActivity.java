package com.plantplaces.plantplaces14ssadvanced;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.plantplaces.plantplaces14ssadvanced.dao.IPlantDAO;
import com.plantplaces.plantplaces14ssadvanced.dao.PlantDAOStub;
import com.plantplaces.plantplaces14ssadvanced.dto.Plant;

public class PlantResultsActivity extends ListActivity {

	private IPlantDAO plantDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// get access to our DAO.
		plantDAO = new PlantDAOStub();
		
		// find the search term.
		String plantSearchText = this.getIntent().getStringExtra(SearchPlantsActivity.PLANT_SEARCH_TEXT);
		
		// make a new Plant object that will serve as our search criteria.
		Plant searchPlant = new Plant();
		
		// set the search criteria.  Assume it's common name.
		searchPlant.setCommon(plantSearchText);
		
		// pass the search criteria to the PlantDAO.
		try {
			// get a list of plants.
			List<Plant> allPlants = plantDAO.fetch(searchPlant);
		
			// the lines that follow, until the catch block, will execute only if we did not get an exception.
			
			// make the list UI-friendly.
			ArrayAdapter<Plant> aaPlant = new ArrayAdapter<Plant>(this, android.R.layout.simple_list_item_1, allPlants);
			
			// set the list in the list adapter.
			setListAdapter(aaPlant);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "Unable to fetch data.", Toast.LENGTH_LONG).show();
		}
		
	}
	
}
