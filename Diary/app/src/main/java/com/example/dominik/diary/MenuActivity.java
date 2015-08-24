package com.example.dominik.diary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onNewNoteClick(View view){
        Intent intent = new Intent(this, CreateItem.class);
        startActivity(intent);
    }

    public void onPreviousNotesClick(View view){
        Intent intent = new Intent(this, ViewItems.class);
        startActivity(intent);
    }

    public void onManageNotesClick(View view){
        Intent intent = new Intent(this, ManageItems.class);
        startActivity(intent);
    }

}
