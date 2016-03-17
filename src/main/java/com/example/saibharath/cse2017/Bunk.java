package com.example.saibharath.cse2017;

/**
 * Created by sai bharath on 04-03-2016.
 */
public class Bunk {

    String sname;
    int bunk;
    int total;


    // Empty constructor
    public Bunk(){

    }
    // constructor
    public Bunk( String name,int b,int t){

        this.sname = name;
        this.bunk = b;
        this.total = t;

    }


    // getting ID
    public int getbunk(){
        return this.bunk;
    }

    // setting id
    public void setbunk(int b){
        this.bunk=b;
    }

    public int getTotal(){
        return this.total;
    }

    // setting id
    public void setTotal(int t){
        this.total=t;
    }

    // getting name
    public String getsname(){
        return this.sname;
    }

    // setting name
    public void setsname(String name){
        this.sname = name;
    }

}
