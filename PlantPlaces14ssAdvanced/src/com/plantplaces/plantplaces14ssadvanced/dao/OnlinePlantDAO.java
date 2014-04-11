package com.plantplaces.plantplaces14ssadvanced.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.plantplaces.plantplaces14ssadvanced.dto.Plant;
import com.plantplaces.plantplaces14ssadvanced.dto.Specimen;

public class OnlinePlantDAO implements IPlantDAO {
	
	private NetworkDAO networkDAO;

	public OnlinePlantDAO() {
		networkDAO = new NetworkDAO();
		
	}

	@Override
	public void save(Plant plant) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Plant> fetch(Plant searchPlant) throws Exception {
		// the base URL for a JSON parse.  
		// TODO: Refactor this to make the domain name configurable.
		String queryURL = "http://www.plantplaces.com/perl/mobile/viewplantsjson.pl?Common=";
		
		// get the search term that the user entered.
		String searchTerm = searchPlant.getCommon();
		
		// assemble the entire query URL.
		final String uri = queryURL + searchTerm;
		
		// make the network call.
		String result = networkDAO.request(uri);
	
		// declare a collection of plants.
		List<Plant> allPlants = new ArrayList<Plant>();

		// parse the data as JSON data; store the data in a series of Plant objects and return them.
		
		// TODO: parse the return from a String to a collection of Plants.
		JSONObject root = new JSONObject(result);
		
		// get the collection (array) of plants from this object.
		JSONArray plantsArray = root.getJSONArray("plants");
		
		// iterate over the collection of plants, and create Plant objects.
		for (int i = 0; i < plantsArray.length(); i++) {
			// make a new Plant object that will hold our plant data.
			Plant plant = new Plant();
			
			// fetch each item from the JSON collection, and store it in our plant.
			JSONObject jsonPlant = plantsArray.getJSONObject(i);
			
			// move data from jsonPlant to plant.
			int id = jsonPlant.getInt("id");
			plant.setGuid(id);
			
			String genus = jsonPlant.getString("genus");
			plant.setGenus(genus);
			
			String species = jsonPlant.getString("species");
			plant.setSpecies(species);
			
			String cultivar = jsonPlant.getString("cultivar");
			plant.setCultivar(cultivar);
			
			String common = jsonPlant.getString("common");
			plant.setCommon(common);
			
			// add our plant to the ArrayList of all plants.
			allPlants.add(plant);
		}
				
		// TODO Auto-generated method stub
		return allPlants;
	}

	@Override
	public Plant fetchPlantById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Specimen> fetchSpecimensByLocation(double latitude,
			double longitude) throws Exception {

		// declare our return type.
		ArrayList<Specimen> allSpecimens = new ArrayList<Specimen>();
		
		String uri = "http://www.plantplaces.com/perl/mobile/viewspecimens.pl?Lat=" + latitude + "&Lng="+ longitude +"&Range=0.01&Source=location";
		
		// make the network call.
		String result = networkDAO.request(uri);
		
		// data coming back in this format:
		// $specimen_id\t$name\t$address\t$address_2\t$city\t$state\t$zip\t$age\t$location\t$planted_by\t$lat\t$lng\t$notes\t$result_plant_id\r\n
		
		String[] allSpecimenData = result.split("\r\n");
		
		// iterate over the raw specimen data, and create specimen objects.
		for (int i = 0; i < allSpecimenData.length; i++) {
			// split the line into constituent parts.
			String[] specimenFields = allSpecimenData[i].split("\t");
			if (specimenFields.length > 13) {
				// create a new specimen object.
				Specimen specimen = new Specimen();

				// populate the specimen object from the fields.
				specimen.setSpecimenId(Integer.parseInt(specimenFields[0]));
				specimen.setAddress(specimenFields[1]);
				specimen.setLatitude(Double.parseDouble(specimenFields[10]));
				specimen.setLongitude(Double.parseDouble(specimenFields[11]));
				specimen.setDescription(specimenFields[12]);
				specimen.setPlantId(Integer.parseInt(specimenFields[13]));

				// add the specimen to our results.
				allSpecimens.add(specimen);
			}
		}
		
		// return the list of specimens.
		return allSpecimens;
		
	}

}
