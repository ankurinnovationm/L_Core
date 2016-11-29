package core2.maz.com.core2.model;

import java.util.ArrayList;

/**
 * Created by Kirti Verma on 06-06-2016.
 */
public class Feed {

    ArrayList<Menu> sections;
    String modified;
    private Sponsor sponsor;
    private ArrayList<Banner> banners;
    private String subscriptionTitle;
    private String subscriptionLabel;
    private boolean subscriptionAllAccess;
    private ArrayList<Subscriptions> subscriptions;
    private boolean hideSubscriberLogin;
    private String logo;
    private String primaryColor;
    private String secondaryColor;


    public Feed(){

    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public void setSections(ArrayList<Menu> sections) {
        this.sections = sections;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public void setSponsor(Sponsor sponsor){
        this.sponsor = sponsor;
    }

    public void setBanners(ArrayList<Banner> banners){
        this.banners = banners;
    }

    public void setSubscriptionTitle(String subscriptionTitle){
        this.subscriptionTitle = subscriptionTitle;
    }

    public void setSubscriptionLabel(String subscriptionLabel){
        this.subscriptionLabel = subscriptionLabel;
    }
    public void setSubscriptionAllAccess(boolean subscriptionAllAccess){
        this.subscriptionAllAccess = subscriptionAllAccess;
    }

    public void setHideSubscriberLogin(boolean hideSubscriberLogin){
        this.hideSubscriberLogin = hideSubscriberLogin;
    }

    public void setSubscriptions(ArrayList<Subscriptions> subscriptions){this.subscriptions = subscriptions;}

    public void setLogo(String logo){ this.logo = logo;}

    public ArrayList<Menu> getSections() {
        return sections;
    }
    public String getModified() {
        return modified;
    }


    public Sponsor getSponsor(){
        return  sponsor;
    }

    public ArrayList<Banner> getBanners(){
        return banners;
    }

    public String getSubscriptionTitle(){
        return subscriptionTitle;
    }

    public String getSubscriptionLabel(){
        return subscriptionLabel;
    }

    public boolean isSubscriptionAllAccess(){
        return subscriptionAllAccess;
    }

    public boolean isHideSubscriberLogin(){
        return hideSubscriberLogin;
    }

    public ArrayList<Subscriptions> getSubscriptions() {return subscriptions;}

    public String getLogo(){return logo;}

}
