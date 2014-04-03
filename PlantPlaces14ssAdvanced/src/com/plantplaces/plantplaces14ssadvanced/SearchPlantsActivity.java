package com.plantplaces.plantplaces14ssadvanced;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.plantplaces.plantplaces14ssadvanced.dao.OfflinePlantDAO;

public class SearchPlantsActivity extends Activity {

    public static final String PLANT_SEARCH_TEXT = "PLANT_SEARCH_TEXT";
	private TextView edtStatus;
	private AutoCompleteTextView actPlantName;


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_plants);

        actPlantName = (AutoCompleteTextView) findViewById(R.id.actPlantName);
        
        // populate the autocomplete with the distinct set of names.
        OfflinePlantDAO plantDAO = new OfflinePlantDAO(this);
        
        // get a list of distinct names.
        ArrayList<String> names = plantDAO.fetchDistinctNames();
        
        // make an ArrayAdapter, which makes this collection UI-friendly.
        ArrayAdapter<String> aaNames = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        // add the collection to the auto complete
        actPlantName.setAdapter(aaNames);
    }

	/**
	 * React to the search button being clicked.
	 * @param v
	 */
	public void onSearchClicked(View v) {
		String plantSearchText = actPlantName.getText().toString();
		
		// Start the PlantResultsActivity
		Intent plantResultsIntent = new Intent(this, PlantResultsActivity.class);
		
		// send the search text to the PlantResultsActivity.
		plantResultsIntent.putExtra(PLANT_SEARCH_TEXT, plantSearchText);
		
				// start the activity.
		startActivity(plantResultsIntent);
		
	}
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_plants, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if (item.getItemId() == R.id.mnuMasterDetail) {
    		// show the master detail screen.
    		Intent mda = new Intent(this, MasterDetailActivity.class);
    		startActivity(mda);
    	} else if (item.getItemId() == R.id.mapOfPlants) {
    		Intent mapOfPlants = new Intent(this, MapOfPlants.class);
    		startActivity(mapOfPlants);
    	}
    	
    	// TODO Auto-generated method stub
    	return super.onOptionsItemSelected(item);
    }
}
