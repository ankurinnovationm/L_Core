package core2.maz.com.core2.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Kirti Verma on 10-08-2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleCoverModel {

    @JsonProperty("url")
    private String url;
    @JsonIgnore(true)
    @JsonProperty("height")
    private int height;
    @JsonIgnore(true)
    @JsonProperty("width")
    private int width;

    public String getUrl() {
        return url;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
