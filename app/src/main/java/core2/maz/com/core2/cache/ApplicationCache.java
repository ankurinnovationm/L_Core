package core2.maz.com.core2.cache;

import java.util.ArrayList;
import java.util.HashMap;

import core2.maz.com.core2.model.Feed;
import core2.maz.com.core2.model.Menu;

/**
 * Created by Ankur Jain on 15-11-2016.
 */
public class ApplicationCache {

    private static ApplicationCache applicationCache =null;
    private Feed feed;
    private HashMap<String,Menu> sectionMap;

    public HashMap<String, Menu> getSectionMap() {
        return sectionMap;
    }

    public void setSectionMap(HashMap<String, Menu> sectionMap) {
        this.sectionMap = sectionMap;
    }

    public ArrayList<Menu> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Menu> sections) {
        this.sections = sections;
    }

    private ArrayList<Menu> sections;

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public static synchronized ApplicationCache getInstance()
    {
        if(applicationCache == null)

        {
            applicationCache = new ApplicationCache();
        }


        return applicationCache;
    }

    //Releasing Resources
    public void removeApplicationCache()
    {
        applicationCache = null;
    }
}
