package core2.maz.com.core2.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import core2.maz.com.core2.managers.AppFeedManager;
import core2.maz.com.core2.utills.UiUtil;

/**
 * Created by Ankur Jain on 24-11-2016.
 */
public class SplashActivity  extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AppFeedManager.myMap != null && !AppFeedManager.myMap.isEmpty())
        {
            onSuccessGetAppFeedAsynkTask();

        }
        else
        {
            new GetAppFeedAsynkTask().execute();
        }
    }


    public class GetAppFeedAsynkTask extends AsyncTask<Void, Void, Void> {
        Dialog dialog;
        Exception exception;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = UiUtil.showProgressDialog(SplashActivity.this);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                AppFeedManager.refreshFeed();
            } catch (Exception exception) {
                this.exception = exception;
                exception.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            UiUtil.dismissDialog(dialog);
            if (exception == null)
            {
                onSuccessGetAppFeedAsynkTask();
            }
            else
            {

            }

        }
    }

    private void onSuccessGetAppFeedAsynkTask()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
