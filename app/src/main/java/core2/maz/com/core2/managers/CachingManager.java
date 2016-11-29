package core2.maz.com.core2.managers;

import java.util.ArrayList;
import java.util.HashMap;

import core2.maz.com.core2.cache.ApplicationCache;
import core2.maz.com.core2.model.Feed;
import core2.maz.com.core2.model.Menu;

/**
 * Created by Ankur Jain on 15-11-2016.
 */
public class CachingManager {

    public static void setAppFeed(Feed feed)
    {
        ApplicationCache.getInstance().setFeed(feed);
    }

    public static Feed getAppFeed()
    {
        Feed feed = ApplicationCache.getInstance().getFeed();
        return feed;
    }

    public static void setSections(ArrayList<Menu> sections)
    {
        ApplicationCache.getInstance().setSections(sections);
    }

    public static ArrayList<Menu> getSections()
    {
        return ApplicationCache.getInstance().getSections();
    }

    public static void  setSectionMap(HashMap<String,Menu> sectionMap)
    {
        ApplicationCache.getInstance().setSectionMap(sectionMap);
    }

    public static HashMap<String,Menu> getSectionMap()
    {
        return ApplicationCache.getInstance().getSectionMap();
    }

}
