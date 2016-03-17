package com.example.saibharath.cse2017;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sai bharath on 25-02-2016.
 */
public class TimeTableFragment extends Fragment {

    View inflatedview;

    Button bt0,bt1,bt2,bt3,bt4,bt5,bt6,bt7;

    Button receive;



    SQLiteHandler db;

    Boolean b1;

    String i0,i1,i2,i3,i4,i5,i6,i7;

    InterstitialAd interstitialAd;

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    SharedPreferences.Editor editor;

    static int count=0;

    int btn0=0,btn1=0,btn2=0,btn3=0,btn4=0,btn5=0,btn6=0,btn7=0;

    String JSON_CLIENT="http://cse2017.96.lt/csecli/receive_row.php";

    public TimeTableFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflatedview = inflater.inflate(R.layout.fragment_one, container, false);

        db = new SQLiteHandler(getActivity());

        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        editor = sharedpreferences.edit();

       // editor.putString("bt0", Integer.toString(Color.RED));
        //editor.apply();














        Button share = (Button) inflatedview.findViewById(R.id.whatsapp);
        receive = (Button) inflatedview.findViewById(R.id.receivebutton);

        bt0 = (Button) inflatedview.findViewById(R.id.button0);
        bt1 = (Button) inflatedview.findViewById(R.id.button1);
        bt2 = (Button) inflatedview.findViewById(R.id.button2);
        bt3 = (Button) inflatedview.findViewById(R.id.button3);
        bt4 = (Button) inflatedview.findViewById(R.id.button4);
        bt5 = (Button) inflatedview.findViewById(R.id.button5);
        bt6 = (Button) inflatedview.findViewById(R.id.button6);
        bt7 = (Button) inflatedview.findViewById(R.id.button7);

        int ok = sharedpreferences.getInt("records", 0);

        if(ok==0){
            db.addSubject(new Subject("","8:30"));
        db.addSubject(new Subject("","9:20"));
        db.addSubject(new Subject("","10:30"));
        db.addSubject(new Subject("","11:20"));
        db.addSubject(new Subject("","1:30"));
        db.addSubject(new Subject("","2:20"));
        db.addSubject(new Subject("","3:10"));
        db.addSubject(new Subject("","4:00"));
        db.addbunkrecord(new Bunk("SOA", 0, 0));
        db.addbunkrecord(new Bunk("MAD", 0, 0));
        db.addbunkrecord(new Bunk("RTS", 0, 0));
        db.addbunkrecord(new Bunk("DAPA", 0, 0));
        db.addbunkrecord(new Bunk("MIC LAB", 0, 0));
        db.addbunkrecord(new Bunk("CRYPTO", 0, 0));
        db.addbunkrecord(new Bunk("MAD LAB", 0, 0));
        db.addbunkrecord(new Bunk("IND LEC", 0, 0));
        db.addbunkrecord(new Bunk("MICRO", 0, 0));
        db.addbunkrecord(new Bunk("DAD", 0,0));
            editor.putInt("records", 1);
            editor.apply();
        }





        Log.d("Reading: ", "Reading all contacts..");
        List<Subject> subjects = db.getAllSubjects();
        List<Bunk> bunks = db.getbunkrecords();

        for (Subject cn : subjects) {
            String log = " ,Name: " + cn.getName() + " ,time: " + cn.gettime();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

        for (Bunk cn : bunks) {
            String log = " sname: " + cn.getsname() + " bunk: " + cn.getbunk() + "total: "+ cn.getTotal();
            // Writing Contacts to log
            Log.d("bunk: ", log);
        }


        bt0.setText(db.getSubject("8:30"));


        bt1.setText(db.getSubject("9:20"));
        bt2.setText(db.getSubject("10:30"));
        bt3.setText(db.getSubject("11:20"));
        bt4.setText(db.getSubject("1:30"));
        bt5.setText(db.getSubject("2:20"));
        bt6.setText(db.getSubject("3:10"));
        bt7.setText(db.getSubject("4:00"));






        i0 =sharedpreferences.getString("bt0", Integer.toString(Color.GREEN));
        i1=sharedpreferences.getString("bt1",Integer.toString(Color.GREEN));
        i2=sharedpreferences.getString("bt2",Integer.toString(Color.GREEN));
        i3=sharedpreferences.getString("bt3",Integer.toString(Color.GREEN));
        i4=sharedpreferences.getString("bt4",Integer.toString(Color.GREEN));
        i5=sharedpreferences.getString("bt5",Integer.toString(Color.GREEN));
        i6=sharedpreferences.getString("bt6",Integer.toString(Color.GREEN));
        i7=sharedpreferences.getString("bt7",Integer.toString(Color.GREEN));





        if(!bt0.getText().toString().equals("")){
            bt0.setBackgroundColor(Integer.parseInt(i0));
            bt0.setEnabled(true);

        }else {
            bt0.setBackgroundColor(Color.GRAY);
            bt0.setEnabled(false);

        }

        if(!bt1.getText().toString().equals("")){
            bt1.setBackgroundColor(Integer.parseInt(i1));
            bt1.setEnabled(true);

        }else {
            bt1.setBackgroundColor(Color.GRAY);
            bt1.setEnabled(false);

        }

        if(!bt2.getText().toString().equals("")){
            bt2.setBackgroundColor(Integer.parseInt(i2));
            bt2.setEnabled(true);

        }else {
            bt2.setBackgroundColor(Color.GRAY);
            bt2.setEnabled(false);

        }

        if(!bt3.getText().toString().equals("")){
            bt3.setBackgroundColor(Integer.parseInt(i3));
            bt3.setEnabled(true);

        }else {
            bt3.setBackgroundColor(Color.GRAY);
            bt3.setEnabled(false);

        }

        if(!bt4.getText().toString().equals("")){
            bt4.setBackgroundColor(Integer.parseInt(i4));
            bt4.setEnabled(true);

        }else {
            bt4.setBackgroundColor(Color.GRAY);
            bt4.setEnabled(false);

        }

        if(!bt5.getText().toString().equals("")){
            bt5.setBackgroundColor(Integer.parseInt(i5));
            bt5.setEnabled(true);

        }else {
            bt5.setBackgroundColor(Color.GRAY);
            bt5.setEnabled(false);

        }

        if(!bt6.getText().toString().equals("")){
            bt6.setBackgroundColor(Integer.parseInt(i6));
            bt6.setEnabled(true);

        }else {
            bt6.setBackgroundColor(Color.GRAY);
            bt6.setEnabled(false);

        }

        if(!bt7.getText().toString().equals("")){
            bt7.setBackgroundColor(Integer.parseInt(i7));
            bt7.setEnabled(true);

        }else {
            bt7.setBackgroundColor(Color.GRAY);
            bt7.setEnabled(false);

        }

       /* bt1.setBackgroundColor(Integer.parseInt(i1));
        bt2.setBackgroundColor(Integer.parseInt(i2));
        bt3.setBackgroundColor(Integer.parseInt(i3));
        bt4.setBackgroundColor(Integer.parseInt(i4));
        bt5.setBackgroundColor(Integer.parseInt(i5));
        bt6.setBackgroundColor(Integer.parseInt(i6));
        bt7.setBackgroundColor(Integer.parseInt(i7));*/


        final ColorDrawable buttonColor0 = (ColorDrawable) bt0.getBackground();
        final ColorDrawable buttonColor1 = (ColorDrawable) bt1.getBackground();
        final ColorDrawable buttonColor2 = (ColorDrawable) bt2.getBackground();
        final ColorDrawable buttonColor3 = (ColorDrawable) bt3.getBackground();
        final ColorDrawable buttonColor4 = (ColorDrawable) bt4.getBackground();
        final ColorDrawable buttonColor5 = (ColorDrawable) bt5.getBackground();
        final ColorDrawable buttonColor6 = (ColorDrawable) bt6.getBackground();
        final ColorDrawable buttonColor7 = (ColorDrawable) bt7.getBackground();







        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(buttonColor0.getColor()== Color.GREEN){


                    editor.putString("bt0", Integer.toString(Color.RED));
                    editor.apply();
                    String i=sharedpreferences.getString("bt0","");
                    bt0.setBackgroundColor(Integer.parseInt(i));

                    //count=1;
                }else{

                    editor.putString("bt0", Integer.toString(Color.GREEN));
                    editor.apply();
                    String i= sharedpreferences.getString("bt0", "");
                    bt0.setBackgroundColor(Integer.parseInt(i));
                }
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(buttonColor1.getColor()== Color.GREEN){


                    editor.putString("bt1", Integer.toString(Color.RED));
                    editor.apply();
                    String i=sharedpreferences.getString("bt1","");
                    bt1.setBackgroundColor(Integer.parseInt(i));

                    //count=1;
                }else{

                    editor.putString("bt1", Integer.toString(Color.GREEN));
                    editor.apply();
                    String i= sharedpreferences.getString("bt1", "");
                    bt1.setBackgroundColor(Integer.parseInt(i));
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(buttonColor2.getColor()== Color.GREEN){


                    editor.putString("bt2", Integer.toString(Color.RED));
                    editor.apply();
                    String i=sharedpreferences.getString("bt2","");
                    bt2.setBackgroundColor(Integer.parseInt(i));

                    //count=1;
                }else{

                    editor.putString("bt2", Integer.toString(Color.GREEN));
                    editor.apply();
                    String i= sharedpreferences.getString("bt2", "");
                    bt2.setBackgroundColor(Integer.parseInt(i));
                }
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(buttonColor3.getColor()== Color.GREEN){


                    editor.putString("bt3", Integer.toString(Color.RED));
                    editor.apply();
                    String i=sharedpreferences.getString("bt3","");
                    bt3.setBackgroundColor(Integer.parseInt(i));

                    //count=1;
                }else{

                    editor.putString("bt3", Integer.toString(Color.GREEN));
                    editor.apply();
                    String i= sharedpreferences.getString("bt3", "");
                    bt3.setBackgroundColor(Integer.parseInt(i));
                }
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(buttonColor4.getColor()== Color.GREEN){


                    editor.putString("bt4", Integer.toString(Color.RED));
                    editor.apply();
                    String i=sharedpreferences.getString("bt4","");
                    bt4.setBackgroundColor(Integer.parseInt(i));

                    //count=1;
                }else{

                    editor.putString("bt4", Integer.toString(Color.GREEN));
                    editor.apply();
                    String i= sharedpreferences.getString("bt4", "");
                    bt4.setBackgroundColor(Integer.parseInt(i));
                }
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(buttonColor5.getColor()== Color.GREEN){


                    editor.putString("bt5", Integer.toString(Color.RED));
                    editor.apply();
                    String i=sharedpreferences.getString("bt5","");
                    bt5.setBackgroundColor(Integer.parseInt(i));

                    //count=1;
                }else{

                    editor.putString("bt5", Integer.toString(Color.GREEN));
                    editor.apply();
                    String i= sharedpreferences.getString("bt5", "");
                    bt5.setBackgroundColor(Integer.parseInt(i));
                }
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(buttonColor6.getColor()== Color.GREEN){


                    editor.putString("bt6", Integer.toString(Color.RED));
                    editor.apply();
                    String i=sharedpreferences.getString("bt6","");
                    bt6.setBackgroundColor(Integer.parseInt(i));

                    //count=1;
                }else{

                    editor.putString("bt6", Integer.toString(Color.GREEN));
                    editor.apply();
                    String i= sharedpreferences.getString("bt6", "");
                    bt6.setBackgroundColor(Integer.parseInt(i));
                }
            }
        });

        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(buttonColor7.getColor()== Color.GREEN){


                    editor.putString("bt7", Integer.toString(Color.RED));
                    editor.apply();
                    String i=sharedpreferences.getString("bt7","");
                    bt7.setBackgroundColor(Integer.parseInt(i));

                    //count=1;
                }else{

                    editor.putString("bt7", Integer.toString(Color.GREEN));
                    editor.apply();
                    String i= sharedpreferences.getString("bt7", "");
                    bt7.setBackgroundColor(Integer.parseInt(i));
                }
            }
        });

        Button b = (Button) inflatedview.findViewById(R.id.bunk);




        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM HH:mm a");
                date = sdf.format(new Date());*/
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View promptView = layoutInflater.inflate(R.layout.alert_bunk, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setView(promptView);

                final TextView bunktime = (TextView) promptView.findViewById(R.id.bunktime);



                alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (!bt0.getText().toString().equals(""))
                            if (buttonColor0.getColor() == Color.RED) {
                                Bunk bunk = db.getbunk(bt0.getText().toString());
                                int b, t;
                                b = bunk.getbunk();
                                t = bunk.getTotal();
                                db.updatebunkrecord(bt0.getText().toString(), b + 1, t + 1);
                            } else {
                                Bunk bunk = db.getbunk(bt0.getText().toString());
                                int b, t;
                                b = bunk.getbunk();
                                t = bunk.getTotal();
                                db.updatebunkrecord(bt0.getText().toString(), b, t + 1);
                            }

                        if (!bt1.getText().toString().equals(""))
                            if (buttonColor1.getColor() == Color.RED) {
                                Bunk bunk = db.getbunk(bt1.getText().toString());
                                int b, t;
                                b = bunk.getbunk();
                                t = bunk.getTotal();
                                db.updatebunkrecord(bt1.getText().toString(), b + 1, t + 1);
                            } else {
                                Bunk bunk = db.getbunk(bt1.getText().toString());
                                int b, t;
                                b = bunk.getbunk();
                                t = bunk.getTotal();
                                db.updatebunkrecord(bt1.getText().toString(), b, t + 1);
                            }

                        if (!bt2.getText().toString().equals(""))
                            if (buttonColor2.getColor() == Color.RED) {
                                Bunk bunk = db.getbunk(bt2.getText().toString());
                                int b, t;
                                b = bunk.getbunk();
                                t = bunk.getTotal();
                                db.updatebunkrecord(bt2.getText().toString(), b + 1, t + 1);
                            } else {
                                Bunk bunk = db.getbunk(bt2.getText().toString());
                                int b, t;
                                b = bunk.getbunk();
                                t = bunk.getTotal();
                                db.updatebunkrecord(bt2.getText().toString(), b, t + 1);
                            }

                        if (!bt3.getText().toString().equals(""))
                            if (buttonColor3.getColor() == Color.RED) {
                                Bunk bunk = db.getbunk(bt3.getText().toString());
                                int b, t;
                                b = bunk.getbunk();
                                t = bunk.getTotal();
                                db.updatebunkrecord(bt3.getText().toString(), b + 1, t + 1);
                            } else {
                                Bunk bunk = db.getbunk(bt3.getText().toString());
                                int b, t;
                                b = bunk.getbunk();
                                t = bunk.getTotal();
                                db.updatebunkrecord(bt3.getText().toString(), b, t + 1);
                            }

                        if (!bt4.getText().toString().equals(""))
                            if (buttonColor4.getColor() == Color.RED) {
                                Bunk bunk = db.getbunk(bt4.getText().toString());
                                int b, t;
                                b = bunk.getbunk();
                                t = bunk.getTotal();
                                db.updatebunkrecord(bt4.getText().toString(), b + 1, t + 1);
                            } else {
                                Bunk bunk = db.getbunk(bt4.getText().toString());
                                int b, t;
                                b = bunk.getbunk();
                                t = bunk.getTotal();
                                db.updatebunkrecord(bt4.getText().toString(), b, t + 1);
                            }

                        if (!bt5.getText().toString().equals(""))
                            if (buttonColor5.getColor() == Color.RED) {
                                Bunk bunk = db.getbunk(bt5.getText().toString());
                                int b, t;
                                b = bunk.getbunk();
                                t = bunk.getTotal();
                                db.updatebunkrecord(bt5.getText().toString(), b + 1, t + 1);
                            } else {
                                Bunk bunk = db.getbunk(bt5.getText().toString());
                                int b, t;
                                b = bunk.getbunk();
                                t = bunk.getTotal();
                                db.updatebunkrecord(bt5.getText().toString(), b, t + 1);
                            }

                        if (!bt6.getText().toString().equals(""))
                            if (buttonColor6.getColor() == Color.RED) {
                                Bunk bunk = db.getbunk(bt6.getText().toString());
                                int b, t;
                                b = bunk.getbunk();
                                t = bunk.getTotal();
                                db.updatebunkrecord(bt6.getText().toString(), b + 1, t + 1);
                            } else {
                                Bunk bunk = db.getbunk(bt6.getText().toString());
                                int b, t;
                                b = bunk.getbunk();
                                t = bunk.getTotal();
                                db.updatebunkrecord(bt6.getText().toString(), b, t + 1);
                            }

                        if (!bt7.getText().toString().equals(""))
                            if (buttonColor7.getColor() == Color.RED) {
                                Bunk bunk = db.getbunk(bt7.getText().toString());
                                int b, t;
                                b = bunk.getbunk();
                                t = bunk.getTotal();
                                db.updatebunkrecord(bt7.getText().toString(), b + 1, t + 1);
                            } else {
                                Bunk bunk = db.getbunk(bt7.getText().toString());
                                int b, t;
                                b = bunk.getbunk();
                                t = bunk.getTotal();
                                db.updatebunkrecord(bt7.getText().toString(), b, t + 1);
                            }




                        Toast.makeText(getActivity(), "Bunks Updated!!", Toast.LENGTH_LONG).show();

                    }

                });
                alertDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });




                AlertDialog alert = alertDialogBuilder.create();

                alert.show();


            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickWhatsApp(v);
            }
        });


        receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn0=0;
                btn1=0;
                btn2=0;
                btn3=0;
                btn4=0;btn5=0;btn6=0;btn7=0;
                bt0.setText("");
                bt1.setText("");
                bt2.setText("");
                bt3.setText("");
                bt4.setText("");
                bt5.setText("");
                bt6.setText("");
                bt7.setText("");
                db.updateSubjects();
                getJSON(JSON_CLIENT);

            }
        });

        return inflatedview;
    }


    public void onClickWhatsApp(View view) {

        PackageManager pm=getActivity().getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");

            String text="";
            if(!bt0.getText().equals(""))
                text= text+"8:30  "+bt0.getText()+"\n";
            if(!bt1.getText().equals(""))
                text=text+"9:20  "+bt1.getText()+"\n";
            if(!bt2.getText().equals(""))
                text=text+"10:30  "+bt2.getText()+"\n";
            if(!bt3.getText().equals(""))
                text=text+"11:20  "+bt3.getText()+"\n";
            if(!bt4.getText().equals(""))
                text=text+"1:30  "+bt4.getText()+"\n";
            if(!bt5.getText().equals(""))
                text=text+"2:20  "+bt5.getText()+"\n";
            if(!bt6.getText().equals(""))
                text=text+"3:10  "+bt6.getText()+"\n";
            if(!bt7.getText().equals(""))
                text=text+"4:00  "+bt7.getText()+"\n";



            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(getActivity(), "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }



    private void getJSON(String url) {
        class GetJSON extends AsyncTask<String, Void, JSONObject> {
            private ProgressDialog nDialog;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                nDialog = new ProgressDialog(getActivity());
                nDialog.setMessage("Refreshing...");
                nDialog.setTitle("Please Wait:)");
                nDialog.setIndeterminate(false);
                nDialog.setCancelable(true);
                nDialog.show();
            }

            @Override
            protected JSONObject doInBackground(String... params) {

                String uri = params[0];
                StringBuilder sb;
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }
                    Log.e("json", sb.toString());
                    // return
                    String jobj=sb.toString().trim();
                    //String sname = null;
                    JSONObject jro = null;
                    try{
                        jro = new JSONObject(jobj);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return jro;
                }catch(Exception e){
                    return null;
                }

            }

            @Override
            protected void onPostExecute(JSONObject jro) {

                nDialog.dismiss();
                String sname=null,sutime=null;
                JSONArray ja = null;
                int i,j=0;
                // ttable = new String[10];
                ArrayList<String> timetable = new ArrayList<String>();

                try {
                    ja = jro.getJSONArray("res");
                    int k=0,l=7;
                    for (i = 0,j = 0; i < ja.length(); i++ , j++) {
                        JSONObject jo = ja.getJSONObject(i);
                        sname = jo.getString("name");
                        sutime = jo.getString("time");
                        //ttable[j] = sname+" "+sutime;
                        if(!sutime.equals(""))
                            timetable.add(sname+" "+sutime);

                       // b1 = sharedpreferences.getBoolean("colordiff", false);
                       // bt0.setEnabled(b1);



                        switch(sutime){
                            case "8:30":bt0.setText(sname);
                                btn0=1;
                                db.updateSubject(new Subject(sname, "8:30"));break;
                            case "9:20":bt1.setText(sname);btn1=1; db.updateSubject(new Subject(sname, "9:20"));break;
                            case "10:30":bt2.setText(sname);btn2=1; db.updateSubject(new Subject(sname, "10:30"));break;
                            case "11:20":bt3.setText(sname);btn3=1;db.updateSubject(new Subject(sname, "11:20"));break;
                            case "1:30":bt4.setText(sname);btn4=1; db.updateSubject(new Subject(sname, "1:30"));break;
                            case "2:20":bt5.setText(sname);btn5=1; db.updateSubject(new Subject(sname, "2:20"));break;
                            case "3:10":bt6.setText(sname);btn6=1; db.updateSubject(new Subject(sname, "3:10"));break;
                            case "4:00":bt7.setText(sname);btn7=1; db.updateSubject(new Subject(sname, "4:00"));break;
                        }
                        Log.d("sai",btn0+"");
                        if(btn0==1){
                            bt0.setBackgroundColor(Integer.parseInt(i0));
                           /* editor.putBoolean("colordiff", true);
                            editor.apply();
                            b1=sharedpreferences.getBoolean("colordiff",false);

                            // bt0.setEnabled(b1);*/
                            bt0.setEnabled(true);
                            //btn0 = 0;
                            k++;
                        }else {
                            bt0.setBackgroundColor(Color.GRAY);

                            bt0.setEnabled(false);
                            k++;
                        }


                        if(btn1==1){
                            bt1.setBackgroundColor(Integer.parseInt(i1));
                           /* editor.putBoolean("colordiff", true);
                            editor.apply();
                            b1=sharedpreferences.getBoolean("colordiff",false);

                            // bt0.setEnabled(b1);*/
                            bt1.setEnabled(true);
                            //btn1 = 0;
                            k++;
                        }else {
                            bt1.setBackgroundColor(Color.GRAY);

                            bt1.setEnabled(false);
                            k++;
                        }

                        if(btn2==1){
                            bt2.setBackgroundColor(Integer.parseInt(i2));
                           /* editor.putBoolean("colordiff", true);
                            editor.apply();
                            b1=sharedpreferences.getBoolean("colordiff",false);

                            // bt0.setEnabled(b1);*/
                            bt2.setEnabled(true);
                           // btn2 = 0;
                            k++;
                        }else {
                            bt2.setBackgroundColor(Color.GRAY);

                            bt2.setEnabled(false);
                            k++;
                        }

                        if(btn3==1){
                            bt3.setBackgroundColor(Integer.parseInt(i3));
                           /* editor.putBoolean("colordiff", true);
                            editor.apply();
                            b1=sharedpreferences.getBoolean("colordiff",false);

                            // bt0.setEnabled(b1);*/
                           // btn3 = 0;
                            bt3.setEnabled(true);
                            k++;
                        }else {
                            bt3.setBackgroundColor(Color.GRAY);

                            bt3.setEnabled(false);
                            k++;
                        }

                        if(btn4==1){
                            bt4.setBackgroundColor(Integer.parseInt(i4));
                           /* editor.putBoolean("colordiff", true);
                            editor.apply();
                            b1=sharedpreferences.getBoolean("colordiff",false);

                            // bt0.setEnabled(b1);*/
                            bt4.setEnabled(true);
                           // btn4 = 0;
                            k++;
                        }else {
                            bt4.setBackgroundColor(Color.GRAY);

                            bt4.setEnabled(false);
                            k++;
                        }

                        if(btn5==1){
                            bt5.setBackgroundColor(Integer.parseInt(i5));
                           /* editor.putBoolean("colordiff", true);
                            editor.apply();
                            b1=sharedpreferences.getBoolean("colordiff",false);

                            // bt0.setEnabled(b1);*/
                            bt5.setEnabled(true);
                           // btn5 = 0;
                            k++;
                        }else {
                            bt5.setBackgroundColor(Color.GRAY);

                            bt5.setEnabled(false);
                            k++;
                        }

                        if(btn6==1){
                            bt6.setBackgroundColor(Integer.parseInt(i6));
                           /* editor.putBoolean("colordiff", true);
                            editor.apply();
                            b1=sharedpreferences.getBoolean("colordiff",false);

                            // bt0.setEnabled(b1);*/
                            bt6.setEnabled(true);
                           // btn6 = 0;
                            k++;
                        }else {
                            bt6.setBackgroundColor(Color.GRAY);

                            bt6.setEnabled(false);
                            k++;
                        }

                        if(btn7==1){
                            bt7.setBackgroundColor(Integer.parseInt(i7));
                           /* editor.putBoolean("colordiff", true);
                            editor.apply();
                            b1=sharedpreferences.getBoolean("colordiff",false);

                            // bt0.setEnabled(b1);*/
                            bt7.setEnabled(true);
                           // btn7 = 0;
                            k++;
                        }else {
                            bt7.setBackgroundColor(Color.GRAY);

                            bt7.setEnabled(false);
                            k++;
                        }

                        // Log.e("mess",timetable.get(j));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }catch (NullPointerException npe){
                    npe.printStackTrace();
                }







                //ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1 , timetable );

                //ListView listView = (ListView) inflatedview.findViewById(R.id.list1);
                //listView.setAdapter(adapter);

                // showtimetable();
                // ttable[0]="jjj";ttable[1]="hvh";

                // tv.setText(sname+" "+sutime);
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute(url);
    }



}
