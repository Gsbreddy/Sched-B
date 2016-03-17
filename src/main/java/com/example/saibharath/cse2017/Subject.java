package com.example.saibharath.cse2017;

/**
 * Created by sai bharath on 27-02-2016.
 */
public class Subject {

    //private variables

    String _name;
    String time;


    // Empty constructor
    public Subject(){

    }
    // constructor
    public Subject( String name,String time){

        this._name = name;
        this.time = time;

    }


    // getting ID
    public String gettime(){
        return this.time;
    }

    // setting id
    public void settime(String time){
        this.time=time;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

}
