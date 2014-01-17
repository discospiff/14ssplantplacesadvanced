package com.plantplaces.plantplaces14ssadvanced;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SearchPlantsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_plants);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_plants, menu);
        return true;
    }
    
}
