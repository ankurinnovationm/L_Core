package core2.maz.com.core2.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ankur Jain on 15-11-2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemResponseModel {

    @JsonProperty("type")
    private String type;

    @JsonProperty("identifier")
    private String identifier;

    @JsonProperty("title")
    private String title;

    @JsonProperty("cover")
    private ArticleCoverModel articleCoverModel;

    @JsonProperty("modified")
    private String modified;

    @JsonProperty("locked")
    private boolean locked;

    @JsonProperty("shareable")
    private boolean shareable;

    @JsonProperty("coverDate")
    private String coverDate;

    @JsonProperty("contentUrl")
    private String items;

    @JsonProperty("isDefault")
    private boolean isDefault;

    @JsonProperty("access")
    private MenuAccessModel menuAccessModel;


    @JsonProperty("regularLayout")
    private String layout;

    @JsonProperty("background")
    private SectionBackgroundModel backgroundModel;

    @JsonProperty("summary")
    private String description;

    @JsonProperty("subtitle")
    private String subtitle;

    @JsonProperty("showBanner")
    private boolean showBanner;

    @JsonProperty("customUrl")
    private String customUrl;



    @JsonProperty("special")
    private boolean special;


    public String getType(){ return type;}
    public String getModified(){ return modified;}
    public String getCoverDate(){ return coverDate;}
    public String getItems(){return items;}
    public boolean isLocked(){return locked;}
    public boolean isShareable(){return shareable;}
    public boolean isDefault(){ return isDefault;}
    public String getIdentifier(){ return identifier;}
    public String getTitle(){ return title;}
    public ArticleCoverModel getArticleCoverModel(){return articleCoverModel;}
    public MenuAccessModel getMenuAccessModel(){return menuAccessModel;}
    public String getLayout(){return layout;}
    public SectionBackgroundModel getBackgroundModel(){return  backgroundModel;}
    public String getDescription(){return description;}
    public String getSubtitle(){return subtitle;}
    public boolean isShowBanner(){return showBanner;}
    public String getCustomUrl(){ return customUrl;}
    public boolean isSpecial(){return special;}
}
