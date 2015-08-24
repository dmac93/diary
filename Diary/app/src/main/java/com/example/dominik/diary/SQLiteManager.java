package com.example.dominik.diary;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteManager extends SQLiteOpenHelper {


    public SQLiteManager(Context context) {
        super(context, "diarybase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table diaryapp(" +
                        "id integer primary key autoincrement," +
                        "content text," +
                        "created_date datetime," +
                        "photo_path text);" +
                        "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addRecord(String content, String imgpath){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("content", content);
        values.put("photo_path",imgpath);
        values.put("created_date","CURRENT_DATE");
        db.insertOrThrow("diaryapp",null,values);
    }

    public void editRecord(int id, String content, String imgpath){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("content",content);
        values.put("photo_path",imgpath);
        values.put("created_date", "CURRENT_DATE");
        String args[]={id+""};
        db.update("diary", values, "nr=?", args);
    }

    public void deleteRecord(int id){
        SQLiteDatabase db = getWritableDatabase();
        String[] args={""+id};
        db.delete("diary", "nr=?", args);
    }

    public Cursor getRecords(){
        String[] collums={"id","content","photo_path","created_date"};
        SQLiteDatabase db = getReadableDatabase();
        return db.query("diaryapp", collums,null,null,null,null,null);
    }

}
