package com.ikirenga.android.work;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class programsofgov extends Activity implements RatingBar.OnRatingBarChangeListener {

	
	RatingBar r1,r2; // declare RatingBar object
	TextView t1,tx2;// declare TextView Object
	
	SQLiteDatabase db;
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.programsofgov);
		
		
		db= openOrCreateDatabase("Mydb", MODE_PRIVATE, null);
		//create new table if not already exist
		   db.execSQL("create table if not exists ubudehe(likes)");
		   db.execSQL("create table if not exists ubudehe2(likes)");
		
		
		
		
		r1=(RatingBar)findViewById(R.id.r1);// create RatingBar object
		r2=(RatingBar)findViewById(R.id.r2);// create RatingBar object
		
		t1=(TextView)findViewById(R.id.t1);// create TextView object
		tx2=(TextView)findViewById(R.id.tx2);// create TextView object
		
		r1.setOnRatingBarChangeListener(this);// select listener to be HelloAndroid (this) class
		r2.setOnRatingBarChangeListener(this);
		
		
		
		Button comment1 =(Button)findViewById(R.id.button1);
		
		
		Button comment2 =(Button)findViewById(R.id.button2);
		Button comment3 =(Button)findViewById(R.id.button3);
		Button comment4 =(Button)findViewById(R.id.button4);
	
		
		
comment1.setOnClickListener(new OnClickListener() {
            
            @Override
     		public void onClick(View v) {
     		
       			 Intent intent = new Intent(getApplicationContext(),commentpage.class);
       	    	    startActivity(intent);

     		} });
comment2.setOnClickListener(new OnClickListener() {
    
    @Override
		public void onClick(View v) {
		
			 Intent intent = new Intent(getApplicationContext(),commentpage2.class);
	    	    startActivity(intent);

		} });


comment3.setOnClickListener(new OnClickListener() {
    
    @Override
		public void onClick(View v) {
		
			 Intent intent = new Intent(getApplicationContext(),commentpage3.class);
	    	    startActivity(intent);

		} });


comment4.setOnClickListener(new OnClickListener() {
    
    @Override
		public void onClick(View v) {
		
			 Intent intent = new Intent(getApplicationContext(),commentpage4.class);
	    	    startActivity(intent);

		} });

 
	}	
	
	
	
	
	// implement abstract method onRatingChanged
	public void onRatingChanged(RatingBar ratingBar,float rating, boolean fromUser){
		t1.setText(""+this.r1.getRating()); // display rating as number in TextView, use "this.rating" to not confuse with "float rating"
		
		tx2.setText(""+this.r2.getRating());
		
		int rating1 = (int) rating;
		String message = null;
		switch(rating1) {
		case 1: message = "Sorry you're really upset with us"; break;
		case 2: message = "Sorry you're not happy"; break;
		case 3: message = "It's not good enough"; break;
		case 4: message = "Thanks, we're glad you liked it."; break;
		case 5: message = "Awesome - thanks!"; break;
		}
		Toast.makeText(programsofgov.this, 
			message,
			Toast.LENGTH_LONG).show();
		
		
}
	
	
	
	 public void ok1(View v)
	   {
		  
 	    
 	    float rates = r1.getRating();
 	    
 	   if(r1.getRating()==0){
           Toast.makeText(getApplicationContext(),"Atleast on star must be selected",Toast.LENGTH_LONG).show();
         return;
       }
	    else{
	
 	   
 	   
 	    //insert data into able
 	    db.execSQL("insert into ubudehe values('"+rates+"')");
 	   //display Toast}
 	 
 	   Toast.makeText(this, "thank you.", Toast.LENGTH_LONG).show();
	    }
 	
		 
	   }
	
	
	 public void ok2(View v)
	   {
		  
	    
	    float rates = r2.getRating();
	    
	   if(r2.getRating()==0){
         Toast.makeText(getApplicationContext(),"Atleast on star must be selected",Toast.LENGTH_LONG).show();
       return;
     }
	    else{
	
	   
	   
	    //insert data into able
	    db.execSQL("insert into ubudehe2 values('"+rates+"')");
	   //display Toast}
	 
	   Toast.makeText(this, "thank you.", Toast.LENGTH_LONG).show();
	    }
	
		 
	   }
	 public boolean onCreateOptionsMenu(Menu menu) {
		 
			MenuInflater inflater = getMenuInflater();


			return true;
			 
			}

}