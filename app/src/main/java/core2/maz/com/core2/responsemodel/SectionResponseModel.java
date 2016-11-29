package core2.maz.com.core2.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ankur Jain on 11-11-2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SectionResponseModel extends MasterResponse
{
    @JsonProperty("identifier")
    private String identifier;

    @JsonProperty("title")
    private String name;

    @JsonProperty("modified")
    private String modified;

    @JsonProperty("isDefault")
    private boolean isDefault;

    @JsonProperty("background")
    private SectionBackgroundModel sectionBackgroundModel;

    @JsonProperty("regularLayout")
    private String layout;

    @JsonProperty("contentUrl")
    private String items;

    @JsonProperty("type")
    private String type;

    @JsonProperty("showBanner")
    private boolean showBanner;

    @JsonProperty("customUrl")
    private String customUrl;

    @JsonProperty("logo")
    private ArticleCoverModel logo;

    @JsonProperty("locked")
    private boolean locked;

    @JsonProperty("shareable")
    private boolean shareable;

    @JsonProperty("access")
    private MenuAccessModel menuAccessModel;

    @JsonProperty ("iconUrl")
    private String iconUrl;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    public String getIdentifier(){return identifier;}

    public String getName(){return name;}

    public String getModified(){return modified;}

    public boolean isDefault(){return isDefault;}

    public SectionBackgroundModel getSectionBackgroundModel(){ return sectionBackgroundModel; }

    public String getLayout(){return layout;}

    public String getItems(){return items;}

    public String getType(){return type;}

    public boolean isShowBanner(){return showBanner;}

    public String getCustomUrl(){ return customUrl;}

    public ArticleCoverModel getLogo(){return logo;}

    public boolean isLocked(){return locked;}
    public boolean isShareable(){return shareable;}
    public MenuAccessModel getMenuAccessModel(){return menuAccessModel;}


}
