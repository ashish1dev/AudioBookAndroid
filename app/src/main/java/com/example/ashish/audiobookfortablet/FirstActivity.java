package com.example.ashish.audiobookfortablet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.content.Intent;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void moveToScreen2(View v) {
        if (v.getId() == R.id.imageView2) {
            Log.i("TAG", "image clicked !");
            Intent myIntent = new Intent(this, CameraActivity.class);
//          myIntent.putExtra("key", value); //Optional parameters

            this.startActivity(myIntent);
        }
    }



}
