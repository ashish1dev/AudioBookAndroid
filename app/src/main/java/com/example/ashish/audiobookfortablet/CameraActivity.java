package com.example.ashish.audiobookfortablet;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.content.Intent;
import android.graphics.Bitmap;
import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;
import android.os.Environment;
import java.io.File;
import android.text.format.DateFormat;
import java.util.Date;
import java.io.IOException;
import android.net.Uri;
import android.util.Log;
import android.provider.MediaStore.Images.Media;
import android.graphics.BitmapFactory;
import android.database.Cursor;
import android.provider.MediaStore.MediaColumns;

public class CameraActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 121;
    ImageView iv;
 ;
    String imgPath = "";
    private static final int Permission_Request = 211;
    private static final int CAMERA_REQUEST = 1888;
    public static  String selectedImagePath;
    private CameraActivity context ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        final String dir =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+ "/Folder/";
        File newdir = new File(dir);
        newdir.mkdirs();
        String file = dir+DateFormat.format("yyyy-MM-dd_hhmmss", new Date()).toString()+".jpg";


        File newfile = new File(file);
        try {
            newfile.createNewFile();
        } catch (IOException e) {}


        Button b1=(Button)findViewById(R.id.captureImageButton);
        iv=(ImageView)findViewById(R.id.cameraImageView);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, setImageUri());
//                startActivityForResult(intent, CAMERA_REQUEST);

                if (android.os.Build.VERSION.SDK_INT >= 23) {
                    // only for gingerbread and newer versions
                    String permission = Manifest.permission.CAMERA;
                    if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                    } else {
                        // Add your function here which open camera
                        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, setImageUri());
                        startActivityForResult(intent, CAMERA_REQUEST);
                    }
                } else {
                    // Add your function here which open camera
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, setImageUri());
                    startActivityForResult(intent, CAMERA_REQUEST);
                }
            }
        });



    }


    public Uri setImageUri() {
        // Store image in dcim
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/", "image" + new Date().getTime() + ".png");
        Uri imgUri = Uri.fromFile(file);
        this.imgPath = file.getAbsolutePath();
        return imgUri;
    }


    public String getImagePath() {
        return imgPath;
    }



    public String getAbsolutePath(Uri uri) {
        String[] projection = { MediaColumns.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            selectedImagePath = getImagePath();
//            iv.setImageBitmap(decodeFile(selectedImagePath));
            Intent myIntent = new Intent(this, AfterCameraActivity.class);
            myIntent.putExtra("selectedImagePath", selectedImagePath); //Optional parameters
            this.startActivity(myIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Add your function here which open camera
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, setImageUri());
                    startActivityForResult(intent, CAMERA_REQUEST);
                } else {
                    Toast.makeText(getApplication(), "Permission required", Toast.LENGTH_LONG).show();
                } return; }
        }
    }

}
