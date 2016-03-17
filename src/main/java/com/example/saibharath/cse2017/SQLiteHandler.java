/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 * */
package com.example.saibharath.cse2017;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLiteHandler extends SQLiteOpenHelper {

	private static final String TAG = SQLiteHandler.class.getSimpleName();

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 10;

	// Database Name
	private static final String DATABASE_NAME = "android_api";

	// Login table name
	private static final String TABLE_USER = "user";
	private static final String TABLE_SUBJECTS = "subjects";
	private static final String TABLE_BUNK = "bunkrecord";

	// Subjects Table Columns names
	//private static final String KEY_ID = "id";
	private static final String KEY_SNAME = "sname";
	private static final String KEY_STIME ="stime";

	// Login Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_UID = "uid";
	private static final String KEY_CREATED_AT = "created_at";

	private static final String KEY_BUNK = "bunk";
	private static final String KEY_TOTAL = "total";


    String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_EMAIL + " TEXT UNIQUE," + KEY_UID + " TEXT,"
            + KEY_CREATED_AT + " TEXT" + ")";


    String CREATE_SUBJECTS_TABLE = "CREATE TABLE " + TABLE_SUBJECTS + "("
            + KEY_SNAME + " TEXT," + KEY_STIME + " TEXT" + ")";

	String CREATE_BUNK_RECORD = "CREATE TABLE "+ TABLE_BUNK + "("
			+ KEY_SNAME + " TEXT," + KEY_BUNK + " INTEGER," + KEY_TOTAL + " INTEGER" + ")";

	public SQLiteHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}



	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(CREATE_LOGIN_TABLE);


        db.execSQL(CREATE_SUBJECTS_TABLE);

		db.execSQL(CREATE_BUNK_RECORD);

		Log.d(TAG, "Database tables created");
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUNK);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing user details in database
	 * */
	public void addUser(String name, String email, String uid, String created_at) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name); // Name
		values.put(KEY_EMAIL, email); // Email
		values.put(KEY_UID, uid); // Email
		values.put(KEY_CREATED_AT, created_at); // Created At

		// Inserting Row
		long id = db.insert(TABLE_USER, null, values);
		db.close(); // Closing database connection

		Log.d(TAG, "New user inserted into sqlite: " + id);
	}

	/**
	 * Getting user data from database
	 * */
	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> user = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE_USER;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.put("name", cursor.getString(1));
			user.put("email", cursor.getString(2));
			user.put("uid", cursor.getString(3));
			user.put("created_at", cursor.getString(4));
		}
		cursor.close();
		db.close();
		// return user
		Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

		return user;
	}

    void addSubject(Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SNAME, subject.getName()); // Subject Name
        values.put(KEY_STIME, subject.gettime());


        // Inserting Row
        db.insert(TABLE_SUBJECTS, null, values);
        db.close(); // Closing database connection
    }

	public String getSubject(String stime) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_SUBJECTS, new String[] {
						KEY_SNAME, KEY_STIME }, KEY_STIME + "=?",
				new String[] { String.valueOf(stime) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		//Subject subject = new Subject(cursor.getString(0), cursor.getString(1));
		// return contact
		return cursor.getString(cursor.getColumnIndex(KEY_SNAME));
	}

    public List<Subject> getAllSubjects() {
        List<Subject> subjectList = new ArrayList<Subject>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SUBJECTS;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Subject subject = new Subject();

                subject.setName(cursor.getString(cursor.getColumnIndex(KEY_SNAME)));
                subject.settime(cursor.getString(cursor.getColumnIndex(KEY_STIME)));
                // Adding subject to list
                subjectList.add(subject);
            } while (cursor.moveToNext());
        }

        // return subject list
        return subjectList;
    }

    public int updateSubject(Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SNAME, subject.getName());
        values.put(KEY_STIME, subject.gettime());

        // updating row
        return db.update(TABLE_SUBJECTS, values, KEY_STIME + " = ?",
                new String[]{String.valueOf(subject.gettime())});
    }

    public int updateSubjects() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SNAME, "");
       // values.put(KEY_STIME, stime);

        // updating row
        return db.update(TABLE_SUBJECTS, values,null, null);
    }


	public void addbunkrecord(Bunk bunk){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_SNAME, bunk.getsname());
		values.put(KEY_BUNK, bunk.getbunk());
		values.put(KEY_TOTAL,bunk.getTotal());


		// Inserting Row
		db.insert(TABLE_BUNK, null, values);
		db.close(); // Closing database connection


	}

	public List<Bunk> getbunkrecords() {
		List<Bunk> bunkList = new ArrayList<>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_BUNK;

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Bunk bunk = new Bunk();

				bunk.setsname(cursor.getString(cursor.getColumnIndex(KEY_SNAME)));
				bunk.setbunk(cursor.getInt(cursor.getColumnIndex(KEY_BUNK)));
				bunk.setTotal(cursor.getInt(cursor.getColumnIndex(KEY_TOTAL)));
				// Adding subject to list
				bunkList.add(bunk);
			} while (cursor.moveToNext());
		}

		// return subject list
		return bunkList;
	}

	public Bunk getbunk(String sname) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_BUNK, new String[]{
						KEY_SNAME, KEY_BUNK ,KEY_TOTAL }, KEY_SNAME + "=?",
				new String[]{String.valueOf(sname)}, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		 Bunk bunk = new Bunk(cursor.getString(0),cursor.getInt(1), cursor.getInt(2));
		 return bunk;
		//return cursor.getString(cursor.getColumnIndex(KEY_SNAME));
	}


	public int updatebunkrecord(String s,int b,int t) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		//values.put(KEY_SNAME, "");
		values.put(KEY_BUNK, b);
		values.put(KEY_TOTAL,t);

		// updating row
		return db.update(TABLE_BUNK, values, KEY_SNAME + " = ?",
				new String[]{String.valueOf(s)});
	}



	public void deleteUsers() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_USER, null, null);
		db.close();

		Log.d(TAG, "Deleted all user info from sqlite");
	}


}
