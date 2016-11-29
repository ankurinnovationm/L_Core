package core2.maz.com.core2.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ankur Jain on 11-11-2016.
 */
public class MyApplication extends Application {

   private static Context appContext = null;

    @Override
    public void onCreate() {
        super.onCreate();

        this.appContext = this;

    }

    public static Context getAppContext()
    {

        return appContext;
    }
}
