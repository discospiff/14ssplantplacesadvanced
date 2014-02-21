package com.plantplaces.plantplaces14ssadvanced.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
		// create our return variable.
		List<Plant> allPlants = new ArrayList<Plant>();
		
		// get the search term.
		String searchTerm = searchPlant.getCommon();

		// assemble the SQL statement.
		String select = "SELECT * FROM " + PLANTS + " WHERE " + GENUS + " like '%" + searchTerm + "%' OR " + SPECIES + " like '%" + searchTerm + "%' OR " + CULTIVAR + " like '%" + searchTerm + "%' OR " + COMMON + " like '%" + searchTerm + "%' ";

		// run the query.
		Cursor cursor = getReadableDatabase().rawQuery(select, null);

		// see if I have results.
		if (cursor.getCount() > 0) {
			// move the cursor to the first result.
			cursor.moveToFirst();

			// a while loop will iterate over results.
			while(!cursor.isAfterLast()) {

				// populate this plant from the cursor.
				Plant plant = populatePlantFromCursor(cursor);
				
				// assign this plant to the collection that we will return.
				allPlants.add(plant);
				
				// move to the next record
				cursor.moveToNext();
			}
		}
		// return our results.
		return allPlants;    
	}

	@Override
	public Plant fetchPlantById(int id) throws Exception {
		// create a return variable.
		Plant plant = null;
		
		// create a select statement.
		String select = "SELECT * FROM " + PLANTS + " WHERE " + GUID + " = " + id;
		
		// run the query.
		Cursor cursor = getReadableDatabase().rawQuery(select, null);
		
		// we should have only one result, since we are searching by GUID.
		if (cursor.getCount() == 1) {
			// move the cursor to the first line.
			cursor.moveToFirst();
			
			plant = populatePlantFromCursor(cursor);
		}
		// close cursor, free up memory.
		cursor.close();
		
		// return the variable.
		return plant;
	}

	private Plant populatePlantFromCursor(Cursor cursor) {
		Plant plant;
		// I need to construct a new Plant object before I can populate it.
		plant = new Plant();
		// populate the plant.
		plant.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
		plant.setGuid(cursor.getInt(cursor.getColumnIndex(GUID)));
		plant.setGenus(cursor.getString(cursor.getColumnIndex(GENUS)));
		plant.setSpecies(cursor.getString(cursor.getColumnIndex(SPECIES)));
		plant.setCultivar(cursor.getString(cursor.getColumnIndex(CULTIVAR)));
		plant.setCommon(cursor.getString(cursor.getColumnIndex(COMMON)));
		return plant;
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
