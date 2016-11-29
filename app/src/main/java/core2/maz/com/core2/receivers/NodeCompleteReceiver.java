package core2.maz.com.core2.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Ankur Jain on 18-11-2016.
 */
public class NodeCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context,intent.getStringExtra("identifier"),Toast.LENGTH_SHORT).show();
    }
}
