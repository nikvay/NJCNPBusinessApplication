package com.nikvay.cnp_master.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nikvay.cnp_master.model.VisitListAddModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SalesPersonVisitList.db";
    public static final String TABLE_NAME = "SalesPersonVisitListTable";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "CONTACT_PERSON";
    public static final String COL_3 = "PHONE_NUMBER";
    public static final String COL_4 = "EMAIL_ID";
    public static final String COL_5 = "MESSAGE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_VISIT_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_1 + " TEXT," +
                COL_2 + " TEXT," +
                COL_3 + " TEXT," +
                COL_4 + " TEXT," +
                COL_5 + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_VISIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);

    }


    public int addVisit(VisitListAddModel visitListModel) {

        long insert;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_1, visitListModel.getName());
        values.put(COL_2, visitListModel.getContact_person());
        values.put(COL_3, visitListModel.getMobile_number());
        values.put(COL_4, visitListModel.getEmail());
        values.put(COL_5, visitListModel.getMessage());


        insert = db.insert(TABLE_NAME, null, values);

        if (insert == -1) {
            db.close();
            return 0;
        }
        db.close();
        return 1;


    }


    public ArrayList<VisitListAddModel> getAllContacts() {
        ArrayList<VisitListAddModel> visitListModelArrayList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                VisitListAddModel visitListModel = new VisitListAddModel();
                visitListModel.setName(cursor.getString(0));
                visitListModel.setContact_person(cursor.getString(1));
                visitListModel.setMobile_number(cursor.getString(2));
                visitListModel.setEmail(cursor.getString(3));
                visitListModel.setMessage(cursor.getString(4));


                visitListModelArrayList.add(visitListModel);
            } while (cursor.moveToNext());
        }

        return visitListModelArrayList;
    }

    public void deleteVisit() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null,null);
        db.close();
    }
}
