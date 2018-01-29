package com.example.root.shoppingassistance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by root on 1/28/18.
 */

public class DatabaseConnector extends SQLiteOpenHelper {

    private static final int db_version=1;
    public final static String dbName="itemdb";
    Cursor cursor;

    //Item table
    private final static String Table_Item="item";
    //item table columns
    private final static String Key_Item_ID="id";
    private final static String Key_Name="name";
    private final static String Key_Item_type_ID="type_id";
    private final static String Key_attrib1="attrib1";
    private final static String Key_attrib2="attrib2";
    private final static String Key_attrib3="attrib3";
    private final static String Key_attrib4="attrib4";
    private final static String Key_attrib5="attrib5";
    private final static String Key_attrib6="attrib6";
    private final static String Key_attrib7="attrib7";
    private final static String Key_attrib8="attrib8";
    private final static String Key_attrib9="attrib9";

    //Item type table
    private final static String Table_Item_Type="item_type";
    //item type table columns
    private final static String Key_Item_Type_ID="item_type_id";
    private final static String Key_attrib1_def="attrib1_def";
    private final static String Key_attrib2_def="attrib2_def";
    private final static String Key_attrib3_def="attrib3_def";
    private final static String Key_attrib4_def="attrib4_def";
    private final static String Key_attrib5_def="attrib5_def";
    private final static String Key_attrib6_def="attrib6_def";
    private final static String Key_attrib7_def="attrib7_def";
    private final static String Key_attrib8_def="attrib8_def";
    private final static String Key_attrib9_def="attrib9_def";

    private static DatabaseConnector databaseConnector=null;

    public static DatabaseConnector getInstance(Context context){
        if(databaseConnector==null){
            databaseConnector=new DatabaseConnector(context);
        }
        return databaseConnector;
    }


    public DatabaseConnector(Context context) {
        super(context, dbName, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create Apartments table
        String Create_Item_Table="CREATE TABLE "+Table_Item+"("
                +Key_Item_ID+" INTEGER PRIMARY KEY,"
                +Key_Name+" TEXT,"
                +Key_Item_type_ID+" INTEGER,"
                +Key_attrib1+" TEXT,"
                +Key_attrib2+" TEXT,"
                +Key_attrib3+" TEXT,"
                +Key_attrib4+" TEXT,"
                +Key_attrib5+" TEXT,"
                +Key_attrib6+" TEXT,"
                +Key_attrib7+" TEXT,"
                +Key_attrib8+" TEXT,"
                +Key_attrib9+" TEXT"+")";

        //create Apartments table
        String Create_Item_Type_Table="CREATE TABLE "+Table_Item_Type+"("
                +Key_Item_Type_ID+" INTEGER PRIMARY KEY,"
                +Key_attrib1_def+" TEXT,"
                +Key_attrib2_def+" TEXT,"
                +Key_attrib3_def+" TEXT,"
                +Key_attrib4_def+" TEXT,"
                +Key_attrib5_def+" TEXT,"
                +Key_attrib6_def+" TEXT,"
                +Key_attrib7_def+" TEXT,"
                +Key_attrib8_def+" TEXT,"
                +Key_attrib9_def+" TEXT"+")";

        db.execSQL(Create_Item_Table);
        db.execSQL(Create_Item_Type_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //save new property
    public void addProperty() {
        //test////
        SQLiteDatabase testdb =this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Key_Name,"Trouser");
        values.put(Key_Item_type_ID, 1);
        values.put(Key_attrib1, "Boss");
        testdb.insert(Table_Item,null, values);

        ContentValues values2=new ContentValues();
        values2.put(Key_attrib1_def,"brand");
        testdb.insert(Table_Item_Type,null, values2);

        testdb.close();

    }

    //get all the saved apartments from the database
    public ArrayList<String> viewAllApartments(){
        SQLiteDatabase db =this.getReadableDatabase();
        String query="select * from item";
        cursor=db.rawQuery(query,null);
        ArrayList<String> items=new ArrayList<String>();
        while(cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex("name"))!=null)
                items.add(cursor.getString(cursor.getColumnIndex("name")));

        }
        return items;

    }

    //get matched item names
    public ArrayList<String> getItemAttribs(String name){
        SQLiteDatabase db =this.getReadableDatabase();
        ArrayList<String> itemAttribs = new ArrayList<String>();
        String query="select * from item";
        cursor=db.rawQuery(query,null);
        ArrayList<String> items=new ArrayList<String>();
        while(cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex("name"))!=null) {
                String itemName = cursor.getString(cursor.getColumnIndex("name"));
                if (name.contains(itemName)){
                    itemAttribs.add(itemName);
                }
            }
        }
        return itemAttribs;
    }
}
