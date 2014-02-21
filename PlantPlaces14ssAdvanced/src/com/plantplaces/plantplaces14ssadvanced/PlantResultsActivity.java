package com.plantplaces.plantplaces14ssadvanced;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.plantplaces.plantplaces14ssadvanced.dao.IPlantDAO;
import com.plantplaces.plantplaces14ssadvanced.dao.OfflinePlantDAO;
import com.plantplaces.plantplaces14ssadvanced.dao.OnlinePlantDAO;
import com.plantplaces.plantplaces14ssadvanced.dto.Plant;

public class PlantResultsActivity extends ListActivity {

	private IPlantDAO plantDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// find the search term.
		String plantSearchText = this.getIntent().getStringExtra(SearchPlantsActivity.PLANT_SEARCH_TEXT);

		// make a new Plant object that will serve as our search criteria.
		Plant searchPlant = new Plant();

		// set the search criteria.  Assume it's common name.
		searchPlant.setCommon(plantSearchText);

		// create an object of our AsyncTask.
		PlantSearchTask pst = new PlantSearchTask();

		// run the plant search task in a new thread.
		pst.execute(searchPlant);

	}

	class PlantSearchTask extends AsyncTask<Plant, Integer, List<Plant>> {

		/**
		 * onPostExecute is invoked when doInBackground thread completes.
		 * onPostExecute executes on the UI thread, so it can interact with UI elements,
		 * and update UI elements.
		 */
		@Override
		protected void onPostExecute(List<Plant> allPlants) {
			// TODO Auto-generated method stub
			super.onPostExecute(allPlants);

			// the lines that follow, until the catch block, will execute only if we did not get an exception.

			// make the list UI-friendly.
			ArrayAdapter<Plant> aaPlant = new ArrayAdapter<Plant>(PlantResultsActivity.this, android.R.layout.simple_list_item_1, allPlants);

			// set the list in the list adapter.
			setListAdapter(aaPlant);


			// create an object of the thread class that will store plants locally.
			SavePlants savePlants = new SavePlants();
			// place this ArrayList in our separate thread so that we can save it.
			savePlants.setAllPlants((ArrayList<Plant>) ((ArrayList<Plant>) allPlants).clone());

			// make a new class of type Thread that will run our thread.
			Thread thread = new Thread(savePlants, "Local Plant Sync");

			// spawn a new thread.
			thread.start();
		}

		/**
		 * This is the stuff that will run in its own thread.  
		 * It's important to put the networking logic here.
		 * THis thread does not run on the UI thread, and thus, cannot directly access UI components.
		 */
		@Override
		protected List<Plant> doInBackground(Plant... searchPlant) {
			// get access to our DAO, which will perform network operations.
			// plantDAO = new OnlinePlantDAO();
			plantDAO = new OfflinePlantDAO(PlantResultsActivity.this);

			// pass the search criteria to the PlantDAO.
			try {
				// get a list of plants.
				List<Plant> allPlants = plantDAO.fetch(searchPlant[0]);

				return allPlants;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//	Toast.makeText(this, "Unable to fetch data.", Toast.LENGTH_LONG).show();
			}

			return new ArrayList<Plant>();

		}

	}

	/**
	 * A thread that will sync data locally once it has beend downloaded from the server.
	 * @author jonesbr
	 *
	 */
	class SavePlants implements Runnable {

		private List<Plant> allPlants;

		/**
		 * This logic will run in a separate thread.
		 */
		@Override
		public void run() {
			// create an instance of OfflinePlantDAO.
			IPlantDAO offlinePlantDAO = new OfflinePlantDAO(PlantResultsActivity.this);

			// save the collection allPlants in our local SQLite database.

			// iterate over the allPlants collection, and save them, one at a time.
			for (Plant plant : allPlants) {
				try {
					if(offlinePlantDAO.fetchPlantById(plant.getGuid()) == null) {
						offlinePlantDAO.save(plant);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		List<Plant> getAllPlants() {
			return allPlants;
		}

		void setAllPlants(List<Plant> allPlants) {
			this.allPlants = allPlants;
		}

	}

}
