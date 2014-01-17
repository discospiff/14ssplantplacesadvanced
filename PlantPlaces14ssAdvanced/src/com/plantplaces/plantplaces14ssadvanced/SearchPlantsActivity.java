package com.plantplaces.plantplaces14ssadvanced;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.plantplaces.plantplaces14ssadvanced.dto.Plant;

public class SearchPlantsActivity extends Activity {

    private TextView edtStatus;


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_plants);
        // create a new plant.
        Plant plant = new Plant();
        
        plant.setGenus("Cercis");
        plant.setSpecies("canadensis");
        plant.setCommon("Redbud");
        
        // get a reference to the status text
        edtStatus = (TextView) findViewById(R.id.edtStatus);
        
        String plantStatus = plant.toString();
        
        // put this status in the edt.
        edtStatus.setText(plantStatus);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_plants, menu);
        return true;
    }
    
}
