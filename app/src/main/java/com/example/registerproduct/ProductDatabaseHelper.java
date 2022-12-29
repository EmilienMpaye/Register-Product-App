package com.example.registerproduct;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabaseHelper extends SQLiteOpenHelper {

    public static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    public static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COLUMN_PRODUCT_QUANTITY = "CUSTOMER_QUANTITY";
    public static final String COLUMN_PRODUCT_PRICE = "PRODUCT_PRICE";
    public static final String COLUMN_ID = "ID";

    public ProductDatabaseHelper( Context context) {
        super(context, "product.db", null, 1);
    }

    @Override
    //this called the first time  database is accessed,there should be code here to create a new  database
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = " CREATE TABLE " + PRODUCT_TABLE + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_PRODUCT_NAME + " TEXT ," + COLUMN_PRODUCT_QUANTITY + " TEXT , " + COLUMN_PRODUCT_PRICE + " INT ) ";

        db.execSQL(createTableStatement);
        //this called the first time a database is  accessed .there should be some code here to create new database
    }

    @Override
    //this is called if the database version numbers change
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public boolean addOne( ProductModel productModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PRODUCT_NAME, productModel.getName());
        cv.put(COLUMN_PRODUCT_QUANTITY, productModel.getQuantity());
        cv.put(COLUMN_PRODUCT_PRICE, productModel.getPrice());

        long insert = db.insert(PRODUCT_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }


    public List<ProductModel> getEveryone(){

        List<ProductModel> returnList = new ArrayList<>();
        //get data from database
        String queryString ="SELECT * FROM " + PRODUCT_TABLE ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor= db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
//look through the cursor (result set) and create new customer object
            do {
                int productId = cursor.getInt(0);
                String productName = cursor.getString(1);
                String productQuantity = cursor.getString(2);
                int productPrice = cursor.getInt(3);
              //  boolean customerActive = cursor.getInt(3)==1?true:false;

                    ProductModel newProduct = new ProductModel(productId,productName,productQuantity,productPrice);
                returnList.add(newProduct);



            }  while (cursor.moveToNext()) ;



        }

        else
        {
//failure.don't add anything to the list
        }
//close cursor and db when its done

        cursor.close();
        db.close();
        return returnList;
    }
    }
