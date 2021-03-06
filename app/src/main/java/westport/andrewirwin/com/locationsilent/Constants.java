package westport.andrewirwin.com.locationsilent;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by AndrewIrwin on 15/04/2017.
 */

public class Constants {

    private Constants(){

    }


    public static final String PACKAGE_NAME = "westport.andrewirwin.com.locationsilent";

    public static final String SHARED_PREFERENCES_NAME = PACKAGE_NAME + ".SHARED_PREFERENCES_NAME";

    public static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";


    /**
     * Used to set an expiration time for a geofence. After this amount of time Location Services
     * stops tracking the geofence.
     */

    public static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;

    /**
     * For this sample, geofences expire after twelve hours.
     */

    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    public static final float GEOFENCE_RADIUS_IN_METERS = 300;


    /**
     * Map for storing information about airports in the San Francisco bay area.
     */

    public static final HashMap<String, LatLng>  locations = new HashMap<>();
    static {

        // GooglePlex.
        //BAY_AREA_LANDMARKS.put("Work", new LatLng(53.279005,-9.008917));
    }

    // Galway Lat 53.279005 long -9.008917
    //


}
