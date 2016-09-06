

package com.ikirenga.android.work;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {



    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mListTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        mListTitles = getResources().getStringArray(R.array.list_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mListTitles));

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }


    private void selectItem(int position) {

        switch (position) {
            case 0:

                // update the main content by replacing fragments
                Fragment fragment = new AboutFragment();
                Bundle args = new Bundle();

                fragment.setArguments(args);

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                // update selected item and title, then close the drawer
                mDrawerList.setItemChecked(position, true);
                setTitle(mListTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);

                break;case 1:

                // update the main content by replacing fragments
                Fragment institution = new InstutitionsFragment();
                Bundle instut = new Bundle();

                institution.setArguments(instut);

                FragmentManager fragmentManager_of_instutition = getFragmentManager();
                fragmentManager_of_instutition.beginTransaction().replace(R.id.content_frame, institution).commit();

                // update selected item and title, then close the drawer
                mDrawerList.setItemChecked(position, true);
                setTitle(mListTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);

                break;

            case 2:

                // update the main content by replacing fragments
                Fragment fragment1 = new RegisterFragment();
                Bundle args1 = new Bundle();

                fragment1.setArguments(args1);

                FragmentManager fragmentManager1 = getFragmentManager();
                fragmentManager1.beginTransaction().replace(R.id.content_frame, fragment1).commit();

                // update selected item and title, then close the drawer
                mDrawerList.setItemChecked(position, true);
                setTitle(mListTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);

                break;

            case 3:

                // update the main content by replacing fragments
                Fragment fragment2 = new SigninFragment();
                Bundle args2 = new Bundle();

                fragment2.setArguments(args2);

                FragmentManager fragmentManager2 = getFragmentManager();

                fragmentManager2.beginTransaction().replace(R.id.content_frame, fragment2).commit();

                // update selected item and title, then close the drawer
                mDrawerList.setItemChecked(position, true);
                setTitle(mListTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);


                break;

            case 4:

                // update the main content by replacing fragments
                Fragment fragment3 = new HelpFragment();
                Bundle args3 = new Bundle();

                fragment3.setArguments(args3);

                FragmentManager fragmentManager3 = getFragmentManager();
                fragmentManager3.beginTransaction().replace(R.id.content_frame, fragment3).commit();

                // update selected item and title, then close the drawer
                mDrawerList.setItemChecked(position, true);
                setTitle(mListTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);

                break;
            default:
        }

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class AboutFragment extends Fragment {


        public AboutFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.about_fragment, container, false);


            return rootView;
        }
    }



    public static class InstutitionsFragment extends Fragment implements AdapterView.OnItemClickListener {
        String[] institutions = new String[]
                {
                        "Districts",
                        "Ministries",
                        "Security ",
                        "Education",
                        "Health",
                        "Lawyers",
                        "Gov agencies",
                };

        // Array of integers points to images stored in /res/drawable-ldpi/
        int[] amafoto = new int[]{
                R.drawable.services,
                R.drawable.hotels,
                R.drawable.hotelsbars,
                R.drawable.travel,
                R.drawable.banking,
                R.drawable.lawyers,
                R.drawable.proffessional,
        };

        public InstutitionsFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.institutions_fragment, container, false);


            // Each row in the list stores amazinayabyo and amafoto


            // Each row in the list stores amazinayabyo and amafoto

            List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

            for(int i= 0;i < 7; i++){
                HashMap<String, String> hm = new HashMap<String,String>();
                hm.put("textView",  institutions[i]);
                hm.put("imageView1", Integer.toString(amafoto[i]) );
                aList.add(hm);
            }


            // Keys used in Hashmap
            String[] from = { "imageView1","textView" };

            // Ids of views in listview_layout
            int[] to = { R.id.imageView,R.id.textView};

            // Instantiating an adapter to store each items
            // R.layout.listview_layout defines the layout of each item
            SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.tab2, from, to);
            // Getting a reference to listview of main.xml layout file
            ListView listView = ( ListView )rootView.findViewById(R.id.listView);

            // Setting the adapter to the listView
            listView.setAdapter(adapter);


            // Setting the adapter to the listView

            listView .setOnItemClickListener(this);



            return rootView;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            switch (position) {
                case 0:

                    Intent a =new Intent  (getActivity().getApplicationContext(),page1.class);
                    startActivity(a);
                    break;
                case 1:


                    Intent b =new Intent  (getActivity().getApplicationContext(),page2.class);
                    startActivity(b);
                    break;
                case 2:

                    Intent c =new Intent  (getActivity().getApplicationContext(),page3.class);
                    startActivity(c);
                    break;
                case 3:

                    Intent d =new Intent  (getActivity().getApplicationContext(),page4.class);
                    startActivity(d);
                    break;
                case 4:
                    Intent e=new Intent  (getActivity().getApplicationContext(),page5.class);
                    startActivity(e);
                    break;
                case 5:
                    Intent f =new Intent  (getActivity().getApplicationContext(),page6.class);
                    startActivity(f);
                    break;
                case 6:
                    Intent g =new Intent  (getActivity().getApplicationContext(),page7.class);
                    startActivity(g);

                default:
                    break;
            }
        }
    }
    public static class HelpFragment extends Fragment {


        public HelpFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.help_fragment, container, false);


            return rootView;
        }
    }

    public class RegisterFragment extends Fragment {


        public Button btnRegister;
        public EditText inputUsername;
        public EditText inputPassword;
        public EditText inputPhone;
        private ProgressBar progress;

        InputStream is = null;
        String result = null;
        String line = null;
        int code;


        HttpPost httppost;
        StringBuffer buffer;
        HttpResponse response;

        HttpClient httpclient;
        NetworkInfo nf;
        List<NameValuePair> nameValuePairs;
        ProgressDialog dialog = null;

        public RegisterFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.register_fragment, container, false);


            inputUsername = (EditText) rootView.findViewById(R.id.editText);
            inputPassword = (EditText) rootView.findViewById(R.id.editText2);
            inputPhone = (EditText) rootView.findViewById(R.id.editText3);
            btnRegister = (Button) rootView.findViewById(R.id.button2);

            progress = (ProgressBar) rootView.findViewById(R.id.progress);


            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }


            btnRegister.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    String name = inputUsername.getText().toString();
                    String password = inputPassword.getText().toString();
                    String phone = inputPhone.getText().toString();


                    if (name.isEmpty() && password.isEmpty() && phone.isEmpty()) {
                        Toast.makeText(getActivity(),
                                "Field can not be empty ", Toast.LENGTH_LONG).show();


                    }
                    else {

                        dialog = ProgressDialog.show(getActivity(), "",
                                "please wait registration...", true);
                        dialog.setCancelable(true);
                        new Thread(new Runnable() {
                            public void run() {

                                registerProcess();
                            }


                        }).start();

                    }


                }
            });
            return rootView;
        }


        private void registerProcess() {

            String name = inputUsername.getText().toString();
            String password = inputPassword.getText().toString();
            String phone = inputPhone.getText().toString();


            try {

                httpclient = new DefaultHttpClient();
                httppost = new HttpPost("http://www.ndanoza.com/ikirengaAPI/register2.php"); // make sure the url is correct.
                //add your data
                nameValuePairs = new ArrayList<NameValuePair>(2);
                // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar,


                nameValuePairs.add(new BasicNameValuePair("name", name)); // $Edittext_value = $_POST['Edittext_value'];
                nameValuePairs.add(new BasicNameValuePair("password", password)); // $Edittext_value = $_POST['Edittext_value'];
                nameValuePairs.add(new BasicNameValuePair("phone", phone));


                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //Execute HTTP Post Request
                response = httpclient.execute(httppost);
                // edited by James from coderzheaven.. from here....
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String response = httpclient.execute(httppost, responseHandler);
                System.out.println("Response : " + response);

                runOnUiThread(new Runnable() {
                    public void run() {

                        dialog.dismiss();
                        // to clear form contents

                        inputPhone.setText("");
                        inputPassword.setText("");
                        inputUsername.setText("");
                    }
                });

                if (response == response) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getActivity(), "successfully registered", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getActivity(), MainActivity.class));

                        }
                    });


                } else {
                    showAlert();
                }

            } catch (Exception e) {
                dialog.dismiss();
                System.out.println("Exception : " + e.getMessage());
            }
        }

        public void showAlert() {
            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("registration error Error.");
                    builder.setMessage("the user is already there !.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });

        }
    }

    public class SigninFragment extends Fragment {

        CallbackManager callbackManager;
        private LoginButton loginButton;

        private TextView textView;
        private EditText a, b, c;


        String name;
        String password;

        NetworkInfo nf;

        HttpPost httppost;
        StringBuffer buffer;
        HttpResponse response;

        HttpClient httpclient;
        List<NameValuePair> nameValuePairs;
        ProgressDialog dialog = null;
        Button login;

        public SigninFragment() {
            // Empty constructor required for fragment subclasses
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            // Other app specific specialization
            FacebookSdk.sdkInitialize(getApplicationContext());
            AppEventsLogger.activateApp(getActivity());
            callbackManager = CallbackManager.Factory.create();
            // Initialize the SDK before executing any other operations,
            View rootView = inflater.inflate(R.layout.signin_fragment, container, false);



            a = (EditText)rootView.findViewById(R.id.editText4);
            b = (EditText)rootView.findViewById(R.id.editText5);

            loginButton = (LoginButton)rootView.findViewById(R.id.login_button);
            loginButton.setReadPermissions("email");
            // If using in a fragment
            loginButton.setFragment(this);
            login = (Button)rootView.findViewById(R.id.button3);


            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }




            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Toast.makeText(getApplicationContext(), "Login attempt succes,Welcome.",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getActivity(), Citizen_alert_page.class));
                }

                @Override
                public void onCancel() {
                  Toast.makeText(getApplicationContext(), "Login attempt cancelled.",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(FacebookException e) {
                    Toast.makeText(getApplicationContext(), "Login attempt failed,check your network status.",Toast.LENGTH_LONG).show();
                }
            });


            login.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {


                    String name = a.getText().toString();
                    String password = b.getText().toString();
                    // check if any of the fields are vaccant
                    if (name.equals("") || password.equals("")) {
                        a.setError("field coudn't be empty ");
                        b.setError("field coudn't be empty ");
                        return;
                    }

                   else  {


                        dialog = ProgressDialog.show(getActivity(), "",
                                "wait user authentication...", true);
                        dialog.setCancelable(true);
                        new Thread(new Runnable() {
                            public void run() {


                                login();

                            }

                        }).start();
                          }

                }
            });

            return rootView;
        }


        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

        public   void login() {

            String name = a.getText().toString();
            String password = b.getText().toString();


            try {

                httpclient = new DefaultHttpClient();
                httppost = new HttpPost("https://82e2e4c2.ngrok.io/ikirenga/login2.php"); // make sure the url is correct.
                //add your data
                nameValuePairs = new ArrayList<NameValuePair>(2);
                // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar,
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("password", password));

                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //Execute HTTP Post Request
                response = httpclient.execute(httppost);
                // edited by James from coderzheaven.. from here....
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String response = httpclient.execute(httppost, responseHandler);
                System.out.println("Response : " + response);

                runOnUiThread(new Runnable() {
                    public void run() {

                        dialog.dismiss();

                        a.setText("");
                        b.setText("");
                    }
                });

                if (response.equalsIgnoreCase("User Found")) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Access granted", Toast.LENGTH_SHORT).show();
                        }
                    });

                    startActivity(new Intent(getActivity(), Citizen_alert_page.class));
                } if (response.equalsIgnoreCase("User Found")) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Access granted", Toast.LENGTH_SHORT).show();
                        }
                    });

                    startActivity(new Intent(getActivity(), Citizen_alert_page.class));
                }

                else if (response.equalsIgnoreCase("hi")) {
                    runOnUiThread(new Runnable() {
                        public void run() {

                            startActivity(new Intent(getActivity(), programsofgov.class));
                            Toast.makeText(getApplicationContext(), "wow", Toast.LENGTH_SHORT).show();
                        }
                    });


                } else {
                    showAlert();
                }

            } catch (Exception e) {
                dialog.dismiss();
                System.out.println("Exception : " + e.getMessage());
            }
        }

        public void showAlert() {
            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Login Error.");
                    builder.setMessage("invalid user !.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
    }
}


