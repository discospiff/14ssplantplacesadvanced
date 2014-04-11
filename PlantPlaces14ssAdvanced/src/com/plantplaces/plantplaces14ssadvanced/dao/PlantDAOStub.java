package com.plantplaces.plantplaces14ssadvanced.dao;

import java.util.ArrayList;
import java.util.List;

import com.plantplaces.plantplaces14ssadvanced.dto.Plant;
import com.plantplaces.plantplaces14ssadvanced.dto.Specimen;

public class PlantDAOStub implements IPlantDAO {

	@Override
	public void save(Plant plant) throws Exception {
		// TODO Auto-generated method stub
		if (plant.getGenus() == null || plant.getSpecies() == null) {
			throw new Exception ("Plant data are incomplete");
		} else {
			// we have enough plant data to persist.  Nothing to do.
		}

	}

	@Override
	public List<Plant> fetch(Plant searchPlant) throws Exception {
		ArrayList<Plant> allPlants = new ArrayList<Plant>();
		
		if (searchPlant.getCommon().equalsIgnoreCase("Redbud")) {
			Plant p = new Plant();
			p.setGenus("Cercis");
			p.setSpecies("canadensis");
			p.setCommon("Redbud");
			
			allPlants.add(p);
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
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public List<Specimen> fetchSpecimensByLocation(double latitude,
			double longitude) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
