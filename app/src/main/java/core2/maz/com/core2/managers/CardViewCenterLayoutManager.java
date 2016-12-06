package core2.maz.com.core2.managers;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;

import core2.maz.com.core2.R;
import core2.maz.com.core2.utills.AppUtils;

/**
 * Card View Center Layout Manager
 * This class maintain the height and width
 *
 * Created by Nikhil Jain on 6/17/2016.
 */
public class CardViewCenterLayoutManager extends LinearLayoutManager
{
    private int widthOfItemView;
    private int heightOfItemView;
    private int padding;
    private Activity activity;

    /**
     * Constructor
     * @param activity
     */
    public CardViewCenterLayoutManager(Activity activity)
    {
        super(activity);

        this.activity = activity;

        //Get Display Metrics
        DisplayMetrics displaymetrics = AppUtils.getDisplayMetrics(activity);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        //Get is Tab or Mobile (for Tab value is 2.0 and for mobile value is1.0)
        float tabOrMobile = activity.getResources().getDimension(R.dimen.is_tab_or_mobile);

        //Get Is Device In Portrait mode
        boolean isDeviceInPortraitView = AppUtils.isDeviceInPortraitView();

        //Get height, width and padding in Portrait Mode
        if(isDeviceInPortraitView)
        {
            //For Smart Phone
            if(tabOrMobile == 0.0)
            {
                widthOfItemView = width - ((int)activity.getResources().getDimension(R.dimen.padding_left_right_cards) * 2);
                padding = (int)activity.getResources().getDimension(R.dimen.padding_left_right_cards);

                heightOfItemView = (height / 2);
            }
            // For Tab
            else
            {
                widthOfItemView = width - width / 4 ;
                padding = (width - widthOfItemView) / 2;
                heightOfItemView = (height / 2);
            }
        }
        //Get height, width and padding in Landscape Mode
        else
        {
            //For Smart Phone
            if(tabOrMobile == 0.0)
            {
                widthOfItemView = width / 2;
                padding = (width - widthOfItemView) / 2;
                heightOfItemView = height - height / 4;
            }
            //For Tab
            else
            {
                widthOfItemView = width / 2;
                padding = (width - widthOfItemView) / 2;
                heightOfItemView = height / 2;
            }
        }
    }

    /**
     * Get Padding Left
     * @return
     */
    @Override
    public int getPaddingLeft()
    {
        return padding;
    }

    /**
     * Get Padding Right
     * @return
     */
    @Override
    public int getPaddingRight()
    {
        return padding;
    }

    @Override
    public int getPaddingTop() {
        return (int)activity.getResources().getDimension(R.dimen.padding_left_right_cards);
    }

    public int getHeightOfItemView() {
        return heightOfItemView;
    }

    public int getWidthOfItemView() {
        return widthOfItemView;
    }
}
