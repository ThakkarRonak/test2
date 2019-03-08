package com.example.paresh.test.Test2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Categoty1.db";
    public static final String TABLE_USER = "user_table";
    public static final String TABLE_CATEGOTY = "categoty_table";

    public static final String USER_EMAIL = "EMAIL";
    public static final String CATEGORY_ID = "ITEM_ID";
    public static final String CATEGORY_NAME = "ITEM_NAME";
    public static final String PARENT_CAT_ID = "parent_cat_id";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_USER + " (EMAIL TEXT PRIMARY KEY ,PSW INTEGER)");
        db.execSQL("CREATE TABLE " + TABLE_CATEGOTY + " (" + CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CATEGORY_NAME + " TEXT," + PARENT_CAT_ID + " INTEGER )");
    }


    public boolean insertCategory(int parentCatId, String itemName) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CATEGORY_NAME, itemName);
        contentValues.put(PARENT_CAT_ID, parentCatId);

//        db.insert(TABLE_CATEGOTY,contentValues);


        long result = db.insert(TABLE_CATEGOTY, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_CATEGOTY, null);
        return res;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGOTY);
        onCreate(db);
    }
/*public void deleteTable()
{
    SQLiteDatabase db = this.getReadableDatabase();

    db.execSQL("DROP TABLE "+ TABLE_CATEGOTY);
}*/
/*
    public boolean showSubCategory(String itemName, int catId, String parentId) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(CATEGORY_NAME, itemName);
        db.update(TABLE_CATEGOTY, contentValues, "ITEM_ID = ?", new String[]{parentId});
        return true;
    }*/

    public List<DataModel> getalldata(int parentCateId) {
        List<DataModel> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String s[] = {PARENT_CAT_ID + " = " + parentCateId};

        Cursor cursor = db.rawQuery("select * from " + TABLE_CATEGOTY + " WHERE " + PARENT_CAT_ID + " = '" + parentCateId + "'", null);

        if (cursor.moveToFirst()) {
            do {

                Log.d(TAG, "getalldata: " + cursor.getCount());
                DataModel dataModel = new DataModel();
                dataModel.setCategoryID(cursor.getInt(cursor.getColumnIndex(CATEGORY_ID)));
                dataModel.setParentCategoryID(cursor.getInt(cursor.getColumnIndex(PARENT_CAT_ID)));
                dataModel.setCategoryName(cursor.getString(cursor.getColumnIndex(CATEGORY_NAME)));
//                dataModel.setUserEmail(cursor.getString(cursor.getColumnIndex(CATEGORY_NAME)));
                list.add(dataModel);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public int getalldataCount(int parentCateId) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCount = db.rawQuery("select  count(*) from " + TABLE_CATEGOTY + " WHERE " + PARENT_CAT_ID + " = '" + parentCateId + "'", null);

//        Cursor mCount = db.rawQuery("select count(*) from " + TABLE_CATEGOTY + " WHERE '"+ PARENT_CAT_ID + "' = "+ parentCateId, null);

        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        db.close();
        return count;
    }
}