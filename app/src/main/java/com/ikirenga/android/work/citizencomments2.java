package com.ikirenga.android.work;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class citizencomments2 extends Activity {
	SQLiteDatabase db;
	
	 TextView tv4;
	
	 
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.citizencomments2);
	   
	   
	   
	   tv4=(TextView)findViewById(R.id.t6);
		
		db= openOrCreateDatabase("Mydb", MODE_PRIVATE, null);
		

//use cursor to keep all data
//cursor can keep data of any data type
Cursor c4=db.rawQuery("select name,DATETIME,sur_name from mytable4 ORDER BY id DESC", null);

tv4.setText("");
tv4.setText("");

tv4.setTextColor(Color.parseColor("#00008B"));

//move cursor to first position
c4.moveToFirst();

//fetch all data one by one
do
{
//we can use c.getString(0) here
//or we can get data using column index
String name1=c4.getString(c4.getColumnIndex("name"));
String DATETIME=c4.getString(c4.getColumnIndex("DATETIME"));

String surname=c4.getString(c4.getColumnIndex("sur_name"));


//display on text view
tv4.append("Name:    "+name1+"\n");

tv4.append(""+DATETIME+"\n");
tv4.append("\n");

tv4.append(""+surname+"\n");
tv4.append("\n");
tv4.append("------------------");
tv4.append("\n");
tv4.append("\n");



//move next position until end of the data
}while(c4.moveToNext());
	   
	   
	  }
	  }