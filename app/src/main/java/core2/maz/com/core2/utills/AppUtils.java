package core2.maz.com.core2.utills;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import core2.maz.com.core2.application.MyApplication;

/**
 * Created by Ankur Jain on 15-11-2016.
 */
public class AppUtils {

    public static boolean isEmpty(String data)
    {
        return !containsData(data);
    }

    public static boolean containsData(String data)
    {
        boolean containsData = true;
        if (data == null || data.trim().equals(""))
        {
            containsData = false;
        }

        return containsData;
    }


    public static boolean isDeviceInPortraitView()
    {
        //Get App Context
        Context appContext = MyApplication.getAppContext();

        //Get Device Orientation
        int deviceOrientation = appContext.getResources().getConfiguration().orientation;

        return deviceOrientation == Configuration.ORIENTATION_PORTRAIT;

    }

    /**
     * Get Screen Height and Width
     * @param activity
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Activity activity)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        return displaymetrics;
    }

}
