package westport.andrewirwin.com.locationsilent;

import android.content.Context;
import android.content.res.Resources;


import com.google.android.gms.location.GeofenceStatusCodes;

/**
 * Created by AndrewIrwin on 15/04/2017.
 */

public class GeofenceErrorMessages {

    private GeofenceErrorMessages(){

    }


    public static String getErrorString(Context context, int errorCode){
        Resources mRescources = context.getResources();

        switch (errorCode){
            case GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE:
                return mRescources.getString(R.string.geofence_not_available);

            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES:
                return mRescources.getString(R.string.geofence_too_many_geofences);

            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS:
                return mRescources.getString(R.string.geofence_too_many_pending_intents);

            default:
                return mRescources.getString(R.string.unknown_geofence_error);
        }
    }



}
