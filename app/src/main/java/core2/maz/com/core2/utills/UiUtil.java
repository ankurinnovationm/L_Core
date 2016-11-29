package core2.maz.com.core2.utills;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import core2.maz.com.core2.R;

/**
 * Created by Ankur Jain on 15-11-2016.
 */
public class UiUtil {


    /**
     * Show Progress Dialog
     * @param context
     * @return
     */
    public static Dialog showProgressDialog(Context context)
    {
        Dialog dialog = new ProgressDialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        dialog.show();
        View view = LayoutInflater.from(context).inflate(context.getResources().getLayout(R.layout.progress_bar_layout), null);
        dialog.setContentView(view);

        return dialog;
    }

    public static void dismissDialog(Dialog dialog)
    {
        try
        {
            if (dialog != null && dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
        catch (Exception exception)
        {
            //Consume it
        }
    }
}
