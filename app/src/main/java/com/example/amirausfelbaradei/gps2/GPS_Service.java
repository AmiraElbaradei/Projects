package com.example.amirausfelbaradei.gps2;
import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import java.util.Date;

/**
 * Created by Amira Usf Elbaradei on 3/8/2018.
 */

public class GPS_Service extends Service implements LocationListener {

    FileUtil fu;
    String fileName = "GPS2.csv";
    String filePath;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("HI", "GPS Service started on create");

        fu = new FileUtil();
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        filePath=intent.getExtras().get("filePath").toString();
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onLocationChanged(Location location) {
        double longitude= location.getLongitude();
        double latitude = location.getLatitude();
        double accuracy=location.getAccuracy();
        double speed=location.getSpeed();
        double altitude=location.getAltitude();
        double time=location.getTime();
        double bearing=location.getBearing();
        Date currentDate=new java.util.Date();
        String row=currentDate.toString()+";"+longitude+";"+latitude+";"+altitude;
        fu.writeInFile(fileName,row,filePath);
        Log.i("Geo_Location","Latitude: "+latitude+", Longitude: "+longitude+", Accuracy: "+accuracy+", Speed: "+speed+", Altitude: "+altitude+", Time: "+time+", Bearing: "+bearing);

    }



    @Override
    public void onProviderDisabled(String s) {

    }
    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
