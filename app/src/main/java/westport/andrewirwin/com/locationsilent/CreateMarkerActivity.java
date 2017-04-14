package westport.andrewirwin.com.locationsilent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CreateMarkerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_marker);

        getSupportActionBar().setTitle("Create Location");


        Button  openMapButton = (Button) findViewById(R.id.openMapBtn);
        openMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchMapActivity = new Intent(CreateMarkerActivity.this, MapsActivity.class);
                startActivity(launchMapActivity);
            }
        });
    }
}
