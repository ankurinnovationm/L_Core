package core2.maz.com.core2.model;

import java.io.Serializable;

/**
 * Created by KirtiVerma on 9/7/16.
 */
public class Item implements Serializable {

    private String identifier;
    private String type;
    private String title;
    private String modified;
    private String contentUrl;
    private String summary;
    private String image;
    private String subtitle;
    private boolean isFirstArticle;

    private Item parent;




    public String getModified(){ return modified;}
    public String getType(){ return type;}
    public String getContentUrl(){return contentUrl;}
    public String getSummary(){return summary;}
    public String getIdentifier(){ return identifier;}
    public String getTitle(){ return title;}
    public Item getParent(){return parent;}
    public String getImage(){return image;}
    public String getSubtitle(){return subtitle;}
    public boolean isFirstArticle(){ return isFirstArticle;}

    public void setModified(String modified) {this.modified = modified;}
    public void setSummary(String summary){ this.summary = summary;}
    public void setIdentifier(String identifier){
        this.identifier = identifier;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setParent(Item parent){this.parent = parent;}
    public void setImage(String image){ this.image = image;}
    public void setContentUrl(String contentUrl){this.contentUrl = contentUrl;}
    public void setType(String type){ this.type = type;}
    public void setSubtitle(String subtitle){ this.subtitle = subtitle;}
    public void setFirstArticle(boolean isFirstArticle) { this.isFirstArticle = isFirstArticle;}

    @Override
    public String toString() {
        return "Article{" +
                "id=" + identifier +
                ", title='" + title + '\'' +
                ", videoUrl='" + contentUrl + '\'' +
                ", ImageUrl='" + image + '\'' +
                '}';
    }


}
