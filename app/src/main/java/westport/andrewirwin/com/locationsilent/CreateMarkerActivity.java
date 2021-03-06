package westport.andrewirwin.com.locationsilent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class CreateMarkerActivity extends AppCompatActivity {

    private static final String TAG = "CreateMarkerActivity";
    private TextView geoFenceNameTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_marker);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Create Location");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();


        //--- text view---
        geoFenceNameTextView = (TextView) findViewById(R.id.GeoFenceNameET);

        geoFenceNameTextView.setText(preferences.getString("GeofenceNameET","Name"));





        Button openMapButton = (Button) findViewById(R.id.openMapBtn);
        openMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("GeofenceNameET", geoFenceNameTextView.getText().toString());


                Intent launchMapActivity = new Intent(CreateMarkerActivity.this, MapsActivity.class);
                startActivity(launchMapActivity);
            }
        });



    }// END OnCreate()








    public void saveButton (View view){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();



        double doubleLat = preferences.getFloat("lat1", 0);
        Log.i(TAG,"addGeofencesButtonHandler: lat= " + doubleLat);


        double doubleLon = preferences.getFloat("lon1", 0);
        Log.i(TAG, "addGeofencesButtonHandler: lon= " + doubleLon);

        String GeoFenceName = geoFenceNameTextView.getText().toString();
        Log.i(TAG, "addGeofencesButtonHandler: GeoFenceName= " + GeoFenceName);



        if (doubleLat != 0 || doubleLon != 0) {
            Constants.locations.put(GeoFenceName, new LatLng(doubleLat, doubleLon));

            Log.i(TAG, "addGeofencesButtonHandler: Gefence Added Success! lat= " + doubleLat + " lon= " + doubleLon);
            Toast.makeText(getApplicationContext(), "Gefence Added Success", Toast.LENGTH_LONG).show();

            editor.putString("GeofenceNameET", "Name");

        } else {
            Log.e(TAG,"addGeofencesButtonHandler: Gefence Error lat= " + doubleLat + "lon= " + doubleLon);
            Toast.makeText(getApplicationContext(),"Gefence Error",Toast.LENGTH_LONG).show();
        }


        Intent launchMaintActivity = new Intent(this, MainActivity.class);
        startActivity(launchMaintActivity);

    }












}
