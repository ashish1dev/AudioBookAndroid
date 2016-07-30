package com.example.ashish.audiobookfortablet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class AfterCameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_camera);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        String selectedImagePath = intent.getStringExtra("selectedImagePath");

        if(selectedImagePath !=null){
            Log.d("selectedImagePath = ",selectedImagePath);
            ImageView iv=(ImageView)findViewById(R.id.imageView3);
            iv.setImageBitmap(decodeFile(selectedImagePath));
        }else{
            ImageView iv=(ImageView)findViewById(R.id.imageView3);
            iv.setImageBitmap(decodeFile(CameraActivity.selectedImagePath));
        }

    }


    public void bookBtnClicked(View v) {
        if (v.getId() == R.id.bookButton) {
            Log.i("TAG", "book button clicked !");
            Intent myIntent = new Intent(this, SlidingActivity.class);
            this.startActivity(myIntent);
        }
    }

    public Bitmap decodeFile(String path) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, o);
            // The new size we want to scale to
            final int REQUIRED_SIZE = 2000;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
//            while (o.outWidth /

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeFile(path, o2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;

    }

}
