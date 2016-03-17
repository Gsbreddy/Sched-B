package com.example.saibharath.cse2017;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sai bharath on 25-02-2016.
 */
public class ChangettFragment extends Fragment {


    private EditText editTextName;
    private TextView tv;
    //String[] ttable;
    String sname,stime;
    Spinner snames,stimes;
    private Button send,receive,clear;


    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    String ok;

    Boolean b1,b2;

    View inflatedview;


    String JSON_SERVER="http://cse2017.96.lt/cse/insert.php";
    String JSON_SERVER1="http://cse2017.96.lt/cse/clear.php";

    public static final String MyPREFERENCES = "MyPrefs" ;

    SharedPreferences sharedpreferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);






    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflatedview = inflater.inflate(R.layout.fragment_two, container, false);

        txtName = (TextView) inflatedview.findViewById(R.id.name);
        txtEmail = (TextView) inflatedview.findViewById(R.id.email);
        btnLogout = (Button) inflatedview.findViewById(R.id.btnLogout);


        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        final SharedPreferences.Editor editor = sharedpreferences.edit();

        clear = (Button) inflatedview.findViewById(R.id.clearbutton);

        // SqLite database handler
        db = new SQLiteHandler(getActivity());

        // session manager
        session = new SessionManager(getActivity());




      /*  if (!session.isLoggedIn()) {
            logoutUser();
        }*/

        // Fetching user details from SQLite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");

        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);




        snames = (Spinner) inflatedview.findViewById(R.id.subnames);
        stimes = (Spinner) inflatedview.findViewById(R.id.subtimes);



        editTextName = (EditText) inflatedview.findViewById(R.id.editText);
       // tv = (TextView) inflatedview.findViewById(R.id.textview1);

        editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(v.getId() == R.id.editText && !hasFocus) {

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


                }
            }
        });


         ok = sharedpreferences.getString("admin","cserocz");
        b1 = sharedpreferences.getBoolean("update", false);
        b2 = sharedpreferences.getBoolean("clear",false);

        send = (Button) inflatedview.findViewById(R.id.sendbutton);
        receive = (Button)inflatedview.findViewById(R.id.receivebutton);



        send.setEnabled(b1);
        clear.setEnabled(b2);

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //logoutUser();


                if(editTextName.getText().toString().equals(ok)){

                    editor.putBoolean("update",true);
                    editor.putBoolean("clear", true);
                    editor.apply();
                    b1=sharedpreferences.getBoolean("clear",false);
                    b2=sharedpreferences.getBoolean("update",false);
                    send.setEnabled(b1);
                    clear.setEnabled(b2);

                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = editTextName.getText().toString();
                sname = String.valueOf(snames.getSelectedItem());
                stime = String.valueOf(stimes.getSelectedItem());
                 //tv.setText(sname+"    "+stime);

                insertToDatabase(sname, stime);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleardatabase();
            }
        });



        return inflatedview;

    }

    private void cleardatabase() {
        class cleardata extends AsyncTask<String,Void,String>{

            private ProgressDialog nDialog;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                nDialog = new ProgressDialog(getActivity());
                nDialog.setMessage("Clearing...");
                nDialog.setTitle("Please Wait:)");
                nDialog.setIndeterminate(false);
                nDialog.setCancelable(true);
                nDialog.show();
            }
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(JSON_SERVER1);

                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                } catch (ClientProtocolException e) {
                } catch (IOException e) {
                }return "Cleared Successfully";
            }


            protected void onPostExecute(String result) {
                nDialog.dismiss();
                super.onPostExecute(result);
                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();

            }




        }
        cleardata cd = new cleardata();
        cd.execute();
    }


    private void insertToDatabase(final String name, final String stime){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            private ProgressDialog nDialog;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                nDialog = new ProgressDialog(getActivity());
                nDialog.setMessage("Updating..");
                nDialog.setTitle("Please Wait:)");
                nDialog.setIndeterminate(false);
                nDialog.setCancelable(true);
                nDialog.show();
            }


            protected String doInBackground(String... params) {
                // String paramUsername = params[0];



                // String name = editTextName.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("time",stime));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(JSON_SERVER);
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                } catch (ClientProtocolException e) {
                } catch (IOException e) {
                }return "updated successfully";
            }

            protected void onPostExecute(String result) {
                nDialog.dismiss();
                super.onPostExecute(result);
                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name);
    }




   /* private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();

    }*/





    public ChangettFragment() {
        // Required empty public constructor
    }




}
