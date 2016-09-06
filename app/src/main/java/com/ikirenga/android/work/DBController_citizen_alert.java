package com.ikirenga.android.work;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
public class DBController_citizen_alert extends SQLiteOpenHelper {

    
	public DBController_citizen_alert(Context applicationcontext) {
        super(applicationcontext,"ikirenga", null, 2);

    }
	//Creates Table
	@Override
	public void onCreate(SQLiteDatabase database) {



		String query;


      query= "create table if not exists citizenalert( id INTEGER PRIMARY KEY, location TEXT,reason TEXT,public_institution TEXT,description TEXT, udpateStatus TEXT)";
        database.execSQL(query);
	}
	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
		String query;
		query = "DROP TABLE IF EXISTS citizenalert";
		database.execSQL(query);
        onCreate(database);
	}
	/**
	 * Inserts User into SQLite DB
	 * @param queryValues
	 */
	public void insertUser(HashMap<String, String> queryValues) {

        SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("location", queryValues.get("location"));
		values.put("reason", queryValues.get("reason"));
        values.put("public_institution", queryValues.get("public_institution"));
        values.put("description", queryValues.get("description"));
        values.put("udpateStatus", "no");
		database.insert("citizenalert", null, values);
		database.close();
	}
	
	/**
	 * Get list of Users from SQLite DB as Array List
	 * @return
	 */
	public ArrayList<HashMap<String, String>> getAllUsers() {
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM citizenalert";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	map.put("Id", cursor.getString(0));
	        	map.put("location", cursor.getString(1));
	        	map.put("reason", cursor.getString(2));
                map.put("public_institution", cursor.getString(3));
                map.put("description", cursor.getString(4));

                wordList.add(map);
	        } while (cursor.moveToNext());
	    }
	    database.close();
	    return wordList;
	}
	
	/**
	 * Compose JSON out of SQLite records
	 * @return
	 */
	public String composeJSONfromSQLite(){
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM citizenalert where udpateStatus = '"+"no"+"'";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	HashMap<String, String> map = new HashMap<String, String>();

                map.put("Id", cursor.getString(0));
                map.put("location", cursor.getString(1));
                map.put("reason", cursor.getString(2));
                map.put("public_institution", cursor.getString(3));
                map.put("description", cursor.getString(4));

	        	wordList.add(map);
	        } while (cursor.moveToNext());
	    }
	    database.close();
		Gson gson = new GsonBuilder().create();
		//Use GSON to serialize Array List to JSON
		return gson.toJson(wordList);
	}
	
	
	
	/**
	 * Get SQLite records that are yet to be Synced
	 * @return
	 */
	public int dbSyncCount(){
		int count = 0;
		String selectQuery = "SELECT  * FROM citizenalert where udpateStatus = '"+"no"+"'";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    count = cursor.getCount();
	    database.close();
		return count;
	}
	
	/**
	 * Update Sync status against each User ID
	 * @param id
	 * @param status
	 */
	public void updateSyncStatus(String id, String status){
		SQLiteDatabase database = this.getWritableDatabase();	 
		String updateQuery = "Update citizenalert set udpateStatus = '"+ status +"' where Id="+"'"+ id +"'";
		Log.d("query",updateQuery);		
		database.execSQL(updateQuery);
		database.close();
	}
}
