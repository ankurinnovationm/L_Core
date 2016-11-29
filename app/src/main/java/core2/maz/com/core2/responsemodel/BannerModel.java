package core2.maz.com.core2.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Kirti Verma on 26-08-2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class BannerModel implements Serializable{

    @JsonProperty("id")
    private String id;
    @JsonProperty("url")
    private String asset;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("sizes")
    private int[] sizes;
    @JsonProperty("name")
    private String name;
    @JsonProperty("actionUrl")
    private String actionUrl;

    public String getId() {
        return id;
    }

    public String getAsset() {
        return asset;
    }

    public String getModified() {
        return modified;
    }

    public int[] getSizes() {
        return sizes;
    }


    public String getName() {
        return name;
    }

    public String getActionUrl() {return actionUrl;}
}
