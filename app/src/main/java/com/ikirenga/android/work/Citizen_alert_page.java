package com.ikirenga.android.work;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexis on 6/16/2016.
 */
public class Citizen_alert_page extends Activity {
    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;

    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;

    SQLiteDatabase db;
    private String location;
    private  ProgressDialog dialog;

    private String public_institution;
    private String description;
    private String reason;
    private Spinner citizen_reason;
    private Spinner citizen_institution;

    private EditText citizen_location;
    private EditText citizen_description;
    private Button submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.citizen_alert_page);


        db = openOrCreateDatabase("ndanoza_noza", MODE_PRIVATE, null);
        db.execSQL("create table if not exists citizenalert( id INTEGER PRIMARY KEY, location TEXT,reason TEXT,public_institution TEXT,description TEXT, udpateStatus TEXT)");


        citizen_reason = (Spinner) findViewById(R.id.reason);
        citizen_institution = (Spinner) findViewById(R.id.institution);
        citizen_location = (EditText) findViewById(R.id.location);
        citizen_description = (EditText) findViewById(R.id.idea);
        submit_button = (Button) findViewById(R.id.alert);


        addListenerOnSpinnerItemSelection();
        addItemsOnInstitution();

        // TODO Auto-generated method stub
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                location = citizen_location.getText().toString();
                if (location.equals("")) {
                    Toast.makeText(getApplicationContext(), "Plz fill blank field", Toast.LENGTH_LONG).show();

                }
                description = citizen_description.getText().toString();
                if (description.equals("")) {
                    Toast.makeText(getApplicationContext(), "Plz fill blank field", Toast.LENGTH_LONG).show();

                } else if (citizen_institution.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "Please select instution",Toast.LENGTH_LONG).show();
                    return;
                } else if (citizen_reason.getSelectedItemPosition() == 0) {

                    Toast.makeText(getApplicationContext(), "Please select yr Reason", Toast.LENGTH_LONG).show();
                    return;
                } else {


                Push();

                }

            }});}

    private void Push() {
        location = citizen_location.getText().toString().trim().toLowerCase();;
        description = citizen_description.getText().toString().trim().toLowerCase();;
        public_institution = citizen_institution.getSelectedItem().toString().trim().toLowerCase();;
        reason = citizen_reason.getSelectedItem().toString().trim().toLowerCase();;

        dialog = ProgressDialog.show(Citizen_alert_page.this, "",
                "please wait sending alert...", true);
        dialog.setCancelable(true);
        new Thread(new Runnable() {
            public void run() {
                try{
                    httpclient=new DefaultHttpClient();
                    httppost= new HttpPost("https://82e2e4c2.ngrok.io/ikirenga/citizenalert.php"); // make sure the url is correct.
                    //add your data
                    nameValuePairs = new ArrayList<NameValuePair>(2);
                    // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar,
                    nameValuePairs.add(new BasicNameValuePair("location",location)); // $Edittext_value = $_POST['Edittext_value'];
                    nameValuePairs.add(new BasicNameValuePair("description",description));
                    nameValuePairs.add(new BasicNameValuePair("public_institution",public_institution)); // $Edittext_value = $_POST['Edittext_value'];
                    nameValuePairs.add(new BasicNameValuePair("reason",reason));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    //Execute HTTP Post Request
                    response=httpclient.execute(httppost);
                    // edited by James from coderzheaven.. from here....
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    final String response = httpclient.execute(httppost, responseHandler);
                    System.out.println("Response : " + response);

                    runOnUiThread(new Runnable() {
                        public void run() {

                            dialog.dismiss();
                        }
                    });

                    if(response==response){
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(Citizen_alert_page.this,"Alert sent. thanks !", Toast.LENGTH_SHORT).show();
                            }
                        });

                       // startActivity(new Intent(CitizenPage.this, MainActivity.class));
                    }else{
                        showAlert();
                    }

                }catch(Exception e){
                    dialog.dismiss();
                    System.out.println("Exception : " + e.getMessage());
                }


                }
        }).start();
        }

    public void showAlert(){
        Citizen_alert_page.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Citizen_alert_page.this);
                builder.setTitle("report error .");
                builder.setMessage("system error !.")
                        .setCancelable(false)
                        .setPositiveButton("OK" , new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });


    }

    private void addListenerOnSpinnerItemSelection(){


        List<String> list= new ArrayList<String>();
        list.add("----");
        list.add("Poor service");
        list.add("Corruption");
        list.add("Inquiry");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citizen_reason.setAdapter(dataAdapter);


    }

    private void addItemsOnInstitution() {

        List<String> list = new ArrayList<String>();
        list.add("----");
        list.add("Districts");
        list.add("Ministries");
        list.add("Security ");
        list.add("Education");
        list.add("Health");
        list.add("Lawyers");
        list.add("Gov agencies");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citizen_institution.setAdapter(dataAdapter);

    }
    }
