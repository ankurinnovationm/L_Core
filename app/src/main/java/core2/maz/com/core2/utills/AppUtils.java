package core2.maz.com.core2.utills;

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
}
