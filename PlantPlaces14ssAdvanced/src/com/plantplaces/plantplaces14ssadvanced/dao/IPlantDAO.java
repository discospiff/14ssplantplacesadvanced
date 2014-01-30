package com.plantplaces.plantplaces14ssadvanced.dao;

import java.util.List;

import com.plantplaces.plantplaces14ssadvanced.dto.Plant;

/**
 * Persistence methods for a Plant.
 * @author jonesb
 *
 */
public interface IPlantDAO {

	/**
	 * Save the plant passed in to this method.
	 * @param plant the plant object we wish to save.
	 * @throws Exception if anything goes wrong with the persistence layer.
	 */
	public void save(Plant plant) throws Exception;
	
	/**
	 * Fetch plants based on the selected criteria.
	 * @param searchPlant the search criteria for which to search a plant.
	 * @return a list of plants that match the search criteria
	 * @throws Exception if anything goes wrong in the persistence layer.
	 */
	public List<Plant> fetch(Plant searchPlant) throws Exception;
	
	/**
	 * Fetch the plant htat has this unique ID.
	 * @param id the unique identifier for this plant.
	 * @return the matching plant.
	 * @throws Exception if anything goes wrong in the persistence layer.
	 */
	public Plant fetchPlantById(int id) throws Exception;
	
}
