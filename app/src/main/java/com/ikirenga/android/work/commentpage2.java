package com.ikirenga.android.work;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class commentpage2 extends Activity {
	  SQLiteDatabase db;
	 
	  EditText et1,et2;
	  int id;
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.commentpage);
	   //initialize all view objects
	  
	   et1=(EditText)findViewById(R.id.email);
	   et2=(EditText)findViewById(R.id.editText2);
	   //create database if not already exist
	   db= openOrCreateDatabase("Mydb", MODE_PRIVATE, null);
	   //create new table if not already exist
	   db.execSQL("create table if not exists mytable2(id INTEGER PRIMARY KEY, name varchar, sur_name varchar, DATETIME DEFAULT CURRENT_TIMESTAMP)");
	   }
	   //This method will call on when we click on insert button
	   public void insert(View v)
	   {
		   
		   
		   
	    String name=et1.getText().toString();
	    String sur_name=et2.getText().toString();

	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy            HH:mm:ss");
	    String strDate = sdf.format(new Date());
	    
  	
	    if(name.equals("")||sur_name.equals(""))
      {
              Toast.makeText(getApplicationContext(), "Plz fill blank field", Toast.LENGTH_LONG).show();
           
      }
	    else{
	
	    et1.setText("");
	    et2.setText("");
	    //insert data into able
	    db.execSQL("insert into mytable2 values(null,'"+name+"','"+sur_name+"','"+strDate+"')");
	   //display Toast
	   Toast.makeText(this, "thank you.", Toast.LENGTH_LONG).show();
	   
	
	   
	   
	   
	    }
	   //This method will call when we click on display button
	    
	    
	    
	   }
	   
	   
	}