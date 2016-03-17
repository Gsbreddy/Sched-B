package com.example.saibharath.cse2017;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by sai bharath on 25-02-2016.
 */
public class BunkFragment extends Fragment {

    SharedPreferences sharedpreferences;


    public static final String MyPREFERENCES = "MyPrefs" ;

    View inflatedview,view;

    String NAME = "sname";

    Button bt0,bt1,bt2,bt3,bt4,bt5,bt6,bt7;
    ListView lv;

    public BunkFragment() {
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
        inflatedview = inflater.inflate(R.layout.fragment_bunk, container, false);

        view = inflater.inflate(R.layout.fragment_one, container, false);

        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

       /* bt0 = (Button) view.findViewById(R.id.button0);
        bt1 = (Button) view.findViewById(R.id.button1);
        bt2 = (Button) view.findViewById(R.id.button2);
        bt3 = (Button) view.findViewById(R.id.button3);
        bt4 = (Button) view.findViewById(R.id.button4);
        bt5 = (Button) view.findViewById(R.id.button5);
        bt6 = (Button) view.findViewById(R.id.button6);
        bt7 = (Button) view.findViewById(R.id.button7);


        final String i = sharedpreferences.getString("bt0","");
        String i1=sharedpreferences.getString("bt1","");
        String i2=sharedpreferences.getString("bt2","");
        String i3=sharedpreferences.getString("bt3","");
        String i4=sharedpreferences.getString("bt4","");
        String i5=sharedpreferences.getString("bt5","");
        String i6=sharedpreferences.getString("bt6","");
        String i7=sharedpreferences.getString("bt7","");*/


        //final String btn = bt0.getText().toString();


        lv = (ListView) inflatedview.findViewById(R.id.listview);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.subnames,R.layout.listitem);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String name = parent.getItemAtPosition(position).toString();
                Intent i = new Intent(getActivity(),BunkActivity.class);
                i.putExtra(NAME,name);
                startActivity(i);
                //Toast.makeText(getActivity(), "Item: " + name, Toast.LENGTH_SHORT).show();
            }
        });







        return inflatedview;
    }




}
