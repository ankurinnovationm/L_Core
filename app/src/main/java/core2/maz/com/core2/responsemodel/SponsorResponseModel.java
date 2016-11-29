package core2.maz.com.core2.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ankur Jain on 11-11-2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SponsorResponseModel {

    @JsonProperty("modified")
    private String modified;

    @JsonProperty("duration")
    private int duration;

    @JsonProperty("imageUrl")
    private String image;

    @JsonProperty("name")
    private String name;

    @JsonProperty("headerText")
    private String header;

    @JsonProperty("bgGradient")
    private SectionGradientResponseModel sponsorBGGradient;

    @JsonProperty("bgImageUrl")
    private String bgImageUrl;

    public String getModified(){
        return modified;
    }

    public String getImage(){
        return image;
    }

    public int getDuration(){
        return duration;
    }

    public String getName(){
        return name;
    }

    public String getHeader(){
        return header;
    }

    public SectionGradientResponseModel getSponsorBGGradient(){
        return sponsorBGGradient;
    }

    public String getBgImageUrl() {return bgImageUrl;}
}
