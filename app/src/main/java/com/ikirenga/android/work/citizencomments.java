package com.ikirenga.android.work;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class citizencomments extends Activity  {

	SQLiteDatabase db;
	
	 TextView tv,tv2,tv3,tv4,tv5;
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.citizencomments);
		

		tv=(TextView)findViewById(R.id.textView1);
		tv2=(TextView)findViewById(R.id.t7);
		tv3=(TextView)findViewById(R.id.t4);
		tv4=(TextView)findViewById(R.id.rates);
		tv5=(TextView)findViewById(R.id.rates2);
		
		db= openOrCreateDatabase("Mydb", MODE_PRIVATE, null);
		

//use cursor to keep all data
//cursor can keep data of any data type
Cursor c=db.rawQuery("select name,DATETIME,sur_name from mytable ORDER BY id DESC", null);

tv.setText("");
tv.setText("");

tv.setTextColor(Color.parseColor("#00008B"));

//move cursor to first position
c.moveToFirst();

//fetch all data one by one
do
{
//we can use c.getString(0) here
//or we can get data using column index
String name1=c.getString(c.getColumnIndex("name"));
String DATETIME=c.getString(c.getColumnIndex("DATETIME"));

String surname=c.getString(c.getColumnIndex("sur_name"));


//display on text view
tv.append("Name:    "+name1+"\n");

tv.append(""+DATETIME+"\n");
tv.append("\n");

tv.append(""+surname+"\n");
tv.append("\n");
tv.append("------------------");
tv.append("\n");
tv.append("\n");



//move next position until end of the data
}while(c.moveToNext());

 
 Cursor c2=db.rawQuery("select name,DATETIME,sur_name from mytable2 ORDER BY id DESC", null);
 tv2.setText("");
 tv2.setText("");
 tv2.setTextColor(Color.parseColor("#00008B"));
 c2.moveToFirst();
 //fetch all data one by one
 do
 {
  //we can use c.getString(0) here
  //or we can get data using column index
  String name1=c2.getString(c2.getColumnIndex("name"));
  String DATETIME=c2.getString(c2.getColumnIndex("DATETIME"));
  
  String surname=c2.getString(c2.getColumnIndex("sur_name"));

 
//display on text view
  tv2.append("Name:    "+name1+"\n");
  
  tv2.append(""+DATETIME+"\n");
  tv2.append("\n");
  
  tv2.append(""+surname+"\n");
  tv2.append("\n");
  tv2.append("------------------");
  tv2.append("\n");
  tv2.append("\n");
  
 
  
 
  //move next position until end of the data
 }
 while(c2.moveToNext());
 
 
 
 Cursor c3=db.rawQuery("select name,DATETIME,sur_name from mytable3 ORDER BY id DESC", null);
 tv3.setText("");
 tv3.setText("");
 tv3.setTextColor(Color.parseColor("#00008B"));
 c3.moveToFirst();
 //fetch all data one by one
 do
 {
  //we can use c.getString(0) here
  //or we can get data using column index
  String name1=c3.getString(c3.getColumnIndex("name"));
  String DATETIME=c3.getString(c3.getColumnIndex("DATETIME"));
  
  String surname=c3.getString(c3.getColumnIndex("sur_name"));

 
//display on text view
  tv3.append("Name:    "+name1+"\n");
  
  tv3.append(""+DATETIME+"\n");
  tv3.append("\n");
  
  tv3.append(""+surname+"\n");
  tv3.append("\n");
  tv3.append("------------------");
  tv3.append("\n");
  tv3.append("\n");
  
 
  
 
  //move next position until end of the data
 }
 while(c3.moveToNext());
 
	
	
	
	//cursor can keep data of any data type
	Cursor c4=db.rawQuery("select SUM(likes) from ubudehe ", null);

	tv4.setText("");
	tv4.setTextColor(Color.parseColor("#FFFAF0"));

	

	//move cursor to first position
	c4.moveToFirst();

	//fetch all data one by one
	do
	{
	//we can use c.getString(0) here
	//or we can get data using column index
	float rates =c4.getFloat(0);
	;


	//display on text view

	tv4.append(""+rates+"");
	

	
	//move next position until end of the data
	}while(c4.moveToNext());

	
	//cursor can keep data of any data type
		Cursor c5=db.rawQuery("select SUM(likes) from ubudehe2 ", null);

		tv5.setText("");
		tv5.setTextColor(Color.parseColor("#FFFAF0"));

		

		//move cursor to first position
		c5.moveToFirst();

		//fetch all data one by one
		do
		{
		//we can use c.getString(0) here
		//or we can get data using column index
		float rates =c5.getFloat(0);
		;


		//display on text view

		tv5.append(""+rates+"");
		

		
		//move next position until end of the data
		}while(c5.moveToNext());


	}	
	
	@Override
	 
	public boolean onCreateOptionsMenu(Menu menu) {
	 
	MenuInflater inflater = getMenuInflater();
	 

	 
	return true;
	 
	}


 
	}	
	
	
	
