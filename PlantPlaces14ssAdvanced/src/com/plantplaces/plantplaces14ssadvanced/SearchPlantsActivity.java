package com.plantplaces.plantplaces14ssadvanced;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class SearchPlantsActivity extends Activity {

    public static final String PLANT_SEARCH_TEXT = "PLANT_SEARCH_TEXT";
	private TextView edtStatus;
	private AutoCompleteTextView actPlantName;


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_plants);

        actPlantName = (AutoCompleteTextView) findViewById(R.id.actPlantName);
        
        
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
    
}
