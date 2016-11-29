package core2.maz.com.core2.constants;

import core2.maz.com.core2.R;
import core2.maz.com.core2.application.MyApplication;

/**
 * Created by Ankur Jain on 10-11-2016.
 */
public class AppConstants {


    public static final int TIME_CONNECTION_TIMEOUT = 30 * 1000;
    public static final int TIME_CONNECTION_DATA_WAIT_TIMEOUT_SMALL = 15 * 1000;
    public static final int TIME_CONNECTION_DATA_WAIT_TIMEOUT_LARGE = 65 * 1000;
    public static final int READ_TIMEOUT_SMALL = 30 * 1000;

    public static final int HTTP_METHOD_POST = 0;
    public static final int HTTP_METHOD_GET = 1;
    public static final int HTTP_METHOD_DELETE = 2;
    public static final String GET_METHOD = "GET";

    public static final String STAGING_API_KEY = "4d43463a-fb2f-44e8-b14e-812875bfeefb";
    public static final String APP_ID = MyApplication.getAppContext().getResources().getString(R.string.CoreAppId);
    public static final String GET_APP_FEED = "https://cloud.mazdigital.com/feeds/staging/comboapp/94/api/v2/app_feed";
    public static final String GET_SIGNATURE = "https://maz.vps561.speedyrails.ca/services/cf_access?app_id=" + APP_ID + "&app_type=" + "comboapp" + "&api_token=" + STAGING_API_KEY;
    public static final String GET_ITEM_FEED = "https://cloud.mazdigital.com/feeds/staging/comboapp/94/api/v2/997/item_feed";

    public static final String KEY_SIGNATURE = "KEY_SIGNATURE";


    public static final String KEY_CARD_FRAGMENT = "cardFragment";
    public static final String KEY_CAROUSEL_FRAGMENT= "carouselFragment";
    public static final String KEY_EDGE_FRAGMENT = "edgeFragment";
    public static final String KEY_MOSAIC_FRAGMENT = "mosaicFragment";
    public static final String KEY_LIST_FRAGMENT = "listFragment";

    public static final String KEY_LOGO_FOLDER_NAME = "Logo";
    public static final String KEY_LOGO_FILE_NAME = "logo";
    public static final String KEY_SPONSOR_FOLDER_NAME = "Sponsor";
    public static final String KEY_SPONSOR_FILE_NAME = "sponsor";
    public static final String KEY_MODIFIED_DATE = "modified";
    public static final String KEY_PRIMARY_COLOR = "primary_color";
    public static final String KEY_SECONDARY_COLOR = "primary_color";
    public static final String KEY_BANNER_FOLDER = "banner";

    public static final String ERROR_CODE_403  = "403";
    public static final String ERROR_CODE_1008 = "1008";
    public static final String  ERROR_CODE_1009= "1009";
    public static final String ERROR_CODE_1010 = "1010";
}
