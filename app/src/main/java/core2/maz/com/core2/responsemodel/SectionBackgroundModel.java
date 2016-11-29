package core2.maz.com.core2.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Kirti Verma on 10-08-2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SectionBackgroundModel {

    @JsonProperty("imageUrl")
    private String image;

    @JsonProperty("videoUrl")
    private String video;

    @JsonProperty("gradient")
    private SectionGradientResponseModel sectionGradientModel;

    public SectionGradientResponseModel getSectionGradientModel() {
       return  sectionGradientModel;
    }

    public String getImage() {
        return image;
    }

    public String getVideo() { return video;}

}
