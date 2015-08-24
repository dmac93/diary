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


public class EditItem extends Activity {

    private static int RESULT_LOAD_IMG = 1;
    private String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Button Changephoto = (Button) findViewById(R.id.AddPhotoButton);
        Button Submit = (Button) findViewById(R.id.SubmitButton);

        Changephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=100; //pamietac zeby to zmienic
                SQLiteManager sql = new SQLiteManager(getApplicationContext());
                EditText et = (EditText) findViewById(R.id.editText2);
                sql.editRecord(id,et.getText().toString(),picturePath);
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();
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
            ImageView img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(BitmapFactory.decodeFile(picturePath, options));
            cursor.close();
        }
    }




}
