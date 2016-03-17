package com.example.saibharath.cse2017;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sai bharath on 05-03-2016.
 */
public class BunkActivity extends AppCompatActivity{

    TextView t1,t2,t0,t3;

    Button b1,b2,b3,b4;

    EditText et1,et2;

    SQLiteHandler db;
    String NAME = "sname" , sname;

    int b, t;
    float p;

    Bunk bunk;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bunkactivity);

        db = new SQLiteHandler(this);

        t0 = (TextView) findViewById(R.id.titletext);
        t1 =(TextView) findViewById(R.id.bunkview);
        t2 =(TextView) findViewById(R.id.totalview);
        t3 =(TextView) findViewById(R.id.per);




        b1 = (Button) findViewById(R.id.edit);
        
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showinputdialog();
            }
        });

        Intent i = getIntent();

        sname = i.getStringExtra(NAME);

        t0.setText(sname);

        bunk = db.getbunk(sname);

        b = bunk.getbunk();
        t = bunk.getTotal();

try{        p = ((t-b)*100)/t;}
catch (ArithmeticException e){
    e.printStackTrace();
}

        t1.setText(b + "");
        t2.setText(t + "");
        t3.setText(p + "");



    }

    private void showinputdialog() {

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.edit_bunk, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptView);


        et1 = (EditText) promptView.findViewById(R.id.bunkedit);
        et2 = (EditText) promptView.findViewById(R.id.totaledit);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try{int b = Integer.parseInt(et1.getText().toString());
                        int t = Integer.parseInt(et2.getText().toString());

                        if(b<=t && b>=0 )
                        db.updatebunkrecord(sname,b ,t );
                        else Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                        Bunk bunk = db.getbunk(sname);

                        t1.setText(bunk.getbunk() + "");
                        t2.setText(bunk.getTotal() + "");
                        p = ((bunk.getTotal()-bunk.getbunk())*100)/bunk.getTotal();t3.setText(p + "");}
                       catch (NumberFormatException e){
                           e.printStackTrace();
                       }
                       catch (ArithmeticException e){
                           t3.setText("100.0");
                       }

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
