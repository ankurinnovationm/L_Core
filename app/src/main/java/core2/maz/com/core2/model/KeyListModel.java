package core2.maz.com.core2.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ankur Jain on 17-11-2016.
 */
public class KeyListModel implements Serializable {

    private String key;
    private ArrayList<String> keyList;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<String> getKeyList() {
        return keyList;
    }

    public void setKeyList(ArrayList<String> keyList) {
        this.keyList = keyList;
    }
}
