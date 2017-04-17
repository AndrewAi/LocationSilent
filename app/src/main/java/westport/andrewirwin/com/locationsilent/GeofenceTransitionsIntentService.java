package westport.andrewirwin.com.locationsilent;


import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndrewIrwin on 18/01/16.
 */
public class GeofenceTransitionsIntentService extends IntentService {

    protected static final String TAG = "GeofenceTransitionIS";



    /**
     * This constructor is required, and calls the super IntentService(String)
     * constructor with the name for a worker thread.
     */
    public GeofenceTransitionsIntentService() {
        // Use the TAG to name the worker thread.
        super(TAG);
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * Handles incoming intents.
     * @param intent sent by Location Services. This Intent is provided to Location
     *               Services (inside a PendingIntent) when addGeofences() is called.
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        Log.i(TAG,"onHandleIntent:");

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()){
            String errorMessage = GeofenceErrorMessages.getErrorString(this,
                    geofencingEvent.getErrorCode());
            Log.e(TAG, errorMessage);
            return;
        }

        // Get the Transistion Type
        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        // Test that the reported transisition was of interest.
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

            // Get the geofences that were triggered. A single event can trigger multiple geofences.
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();

            // Get the transistion details as a String.
            String geofenceTransitionDetails = getGeofenceTransitionDetails(
                    this,
                    geofenceTransition,
                    triggeringGeofences
            );


            //// TODO: 17/04/2017 Geofence Triggered, Action Point: Put Phone on silent here
            Toast.makeText(getApplicationContext(),"GeoFence Triggered",Toast.LENGTH_SHORT).show();






            Log.i(TAG, geofenceTransitionDetails);
        } else{
            // Log the error
            Log.e(TAG, getString(R.string.geofence_transition_invalid_type, geofenceTransition));

        }

    }


    /**
     * Gets transition details and returns them as a formatted string.
     *
     * @param context               The app context.
     * @param geofenceTransition    The ID of the geofence transition.
     * @param triggeringGeofences   The geofence(s) triggered.
     * @return                      The transition details formatted as String.
     */

    private String getGeofenceTransitionDetails(Context context, int geofenceTransition, List<Geofence> triggeringGeofences) {
        String geofenceTransitionString = getTransitionString(geofenceTransition);

        // Get the Ids of each geofence that was triggered.
        ArrayList triggeringGeofencesIdsList = new ArrayList();
        for (Geofence geofence : triggeringGeofences) {
            triggeringGeofencesIdsList.add(geofence.getRequestId());
        }

        String triggeringGeofencesIdsString = TextUtils.join(", ", triggeringGeofencesIdsList);


        return  geofenceTransitionString + ": "  + triggeringGeofencesIdsString;
    }








    /**
     * Maps geofence transition types to their human-readable equivalents.
     *
     * @param transitionType    A transition type constant defined in Geofence
     * @return                  A String indicating the type of transition
     */

    private String getTransitionString(int transitionType) {
        switch (transitionType) {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                return getString(R.string.geofence_transition_entered);
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                return getString(R.string.geofence_transition_exited);
            default:
                return getString(R.string.unknown_geofence_transition);
        }

    }
}
