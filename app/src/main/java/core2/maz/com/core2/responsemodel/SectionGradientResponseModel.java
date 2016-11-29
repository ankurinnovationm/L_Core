package core2.maz.com.core2.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ankur Jain on 11-11-2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SectionGradientResponseModel {
    @JsonProperty("topColor")
    private String top;

    @JsonProperty("bottomColor")
    private String bottom;

    public String getTop() {
        return top;
    }

    public String getBottom() {
        return bottom;
    }
}
