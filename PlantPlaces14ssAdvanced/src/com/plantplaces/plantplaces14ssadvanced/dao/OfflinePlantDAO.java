package com.plantplaces.plantplaces14ssadvanced.dao;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.plantplaces.plantplaces14ssadvanced.dto.Plant;

public class OfflinePlantDAO extends SQLiteOpenHelper implements IPlantDAO {
	
	private static final String COMMON = "COMMON";
	private static final String CULTIVAR = "CULTIVAR";
	private static final String SPECIES = "SPECIES";
	private static final String GENUS = "GENUS";
	private static final String GUID = "GUID";
	private static final String _ID = "_id";
	private static final String PLANTS = "PLANTS";

	public OfflinePlantDAO(Context context) {
		super(context, "plantplaces", null, 1);
	}

	@Override
	public void save(Plant plant) throws Exception {
		// Store our plants in a local SQLite database.
		
		// ContentValues is a collection of key value pairs that we will put into the database, where the key is the database column name.
		ContentValues cv = new ContentValues();
		cv.put(GENUS, plant.getGenus());
		cv.put(SPECIES, plant.getSpecies());
		cv.put(CULTIVAR, plant.getCultivar());
		cv.put(COMMON, plant.getCommon());
		cv.put(GUID, plant.getGuid());
		
		// insert the record into the database, and it will return the local (just generated) primary key.
		long id = getWritableDatabase().insert(PLANTS, GENUS, cv);
		
		// associat this primary key with the plant DTO object.
		plant.setId(id);
		
		
	}

	@Override
	public List<Plant> fetch(Plant searchPlant) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plant fetchPlantById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create the SQLite Database
		db.execSQL("CREATE TABLE " + PLANTS + " ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + GUID + " INTEGER, " + GENUS + " TEXT, " + SPECIES + " TEXT, " + CULTIVAR + " TEXT, " + COMMON + " TEXT); ");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
