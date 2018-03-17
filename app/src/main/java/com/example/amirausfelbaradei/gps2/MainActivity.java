package com.example.amirausfelbaradei.gps2;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn =(Button) findViewById(R.id.startbtn);
        Button stopBtn =(Button) findViewById(R.id.stopbtn);

        //get app folder
        final File root=this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

        final Intent GPSService =new Intent(this, GPS_Service.class);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //send data to service
                GPSService.putExtra("filePath",root.getAbsolutePath()+"/download");
                //start service
                startService(GPSService);

                //print app path
                Log.i("storage path",root.getAbsolutePath()+"/download");

            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(GPSService);

            }
        });
    }
}

