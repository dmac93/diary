package com.example.dominik.diary;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class CreateItem extends Activity {
    private static int RESULT_LOAD_IMG = 1;
    private String picturePath;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);
        Button Addphoto = (Button) findViewById(R.id.AddPhotoBut);

        Addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMG && null != data) {
            Uri selectedImg = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImg,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            ImageView img = (ImageView) findViewById(R.id.IMG);

            img.setImageBitmap(BitmapFactory.decodeFile(picturePath, options));
            cursor.close();
        }
    }

    public void onSubmitClick(View view){
        EditText tx = (EditText) findViewById(R.id.editText);
        String text = tx.getText().toString();
        insertToDB(text, picturePath);
    }

    public void insertToDB(String text, String imgPath){

        SQLiteManager sql = new SQLiteManager(this);
        sql.addRecord(text,imgPath);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();

    }

}
