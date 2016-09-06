package com.ikirenga.android.work;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alexis on 8/11/2016.
 */
public class Citizen_info_rgb extends Activity {
    TextView text;
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.citizen_info_rgb);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Receive();
    }

    private void Receive() {
        String data;

        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> databaseData = new ArrayList<HashMap<String,String>>();

        // Keys used in Hashmap
        String[] from = { "citizen_location","citizen_reason","citizen_institution","citizen_description" ,"time" };

        // Ids of views in listview_layout
        int[] to = { R.id.citizen_location,R.id.citizen_reason,R.id.citizen_institution,R.id.citizen_description,R.id.time};

        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), databaseData, R.layout.itemlist, from, to);

        ListView list=(ListView)findViewById(R.id.listView1);
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("https://8864836a.ngrok.io/ikirenga/citizen_info_to_rgb.php");
            HttpResponse response = client.execute(request);
            HttpEntity entity=response.getEntity();
            data= EntityUtils.toString(entity);
            Log.e("STRING", data);
            try {

                JSONArray json=new JSONArray(data);
                for(int i=0;i<json.length(); i++)
                {
                    JSONObject obj=json.getJSONObject(i);
                    String location=obj.getString("location");
                    String reason=obj.getString("reason");
                    String public_institution=obj.getString("public_institution");
                    String description=obj.getString("description");
                    String created_at=obj.getString("created_at");
                    Log.e("STRING", location);

                    HashMap<String, String> hm = new HashMap<String,String>();
                    hm.put("citizen_location",location);
                    hm.put("citizen_reason",reason);
                    hm.put("citizen_institution",public_institution);
                    hm.put("citizen_description",description);
                    hm.put("time",created_at);
                    databaseData.add(hm);

                    list.setAdapter(adapter);

                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (ClientProtocolException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        } catch (IOException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        }


    }

}
