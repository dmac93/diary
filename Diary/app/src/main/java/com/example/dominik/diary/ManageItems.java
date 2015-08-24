package com.example.dominik.diary;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;


public class ManageItems extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_items);

        TextView tx = (TextView) findViewById(R.id.textView);
        SQLiteManager sql = new SQLiteManager(this);

        Cursor c = sql.getRecords();

        while(c.moveToNext()){
            int id = c.getInt(0);
            String content = c.getString(1);
            String photopath = c.getString(2);
            String date = c.getString(3);
            tx.setText(tx.getText()+"\n"+id+"--"+content+"--"+photopath+"--"+date+"--");

        }

    }




}
