package core2.maz.com.core2.model;

import java.util.ArrayList;

/**
 * Created by Kirti Verma on 22-02-2016.
 */
public class FeedManager {

    private ArrayList<Menu> sections ;
    private ArrayList<Menu> menus;
    private ArrayList<String> savedArticles;
    private ArrayList<Item> myQueue;
    private ArrayList<Item> vidList;
    private ArrayList<Banner> banners;
    private Sponsor sponsor;
    private Subscriptions defaultSub;
    private ArrayList<Subscriptions> subscriptionsArrayList;
    String modifiedDate;
    private String logo;

    private static FeedManager mInstance = null;

    private FeedManager()
    { if(mInstance!=null)
    {
        throw new IllegalStateException("Already instantiated");
    }

    }

    public void initialize(){

       sections = new ArrayList<Menu>();
        modifiedDate = null;
        savedArticles = new ArrayList<String>();
        myQueue = new ArrayList<Item>();
        menus = new ArrayList<Menu>();
        vidList = new ArrayList<Item>();
        banners = new ArrayList<Banner>();
        subscriptionsArrayList = new ArrayList<Subscriptions>();
    }

    public static FeedManager getInstance()
    {      if(mInstance == null)
        mInstance = new FeedManager();
        return mInstance;
    }

    public void addSection(Menu section){

        sections.add(section);
    }

    public ArrayList<Menu> getSections(){

        return sections;
    }

    public void addBanner(Banner banner){

        banners.add(banner);
    }

    public ArrayList<Banner> getBanners(){
        return banners;
    }

    public void setSponsor(Sponsor sponsor){
     this.sponsor = sponsor;
    }

    public Sponsor getSponsor(){ return  sponsor;}


    public void addArticle(Item article){
        vidList.add(article);
    }

    public ArrayList<Item> getVidList(){

        return vidList;
    }

    public void setDefaultSub(Subscriptions defaultSub){
        this.defaultSub = defaultSub;
    }

    public Subscriptions getDefaultSub(){ return defaultSub;}

    public void addMenu(Menu menu){
       menus.add(menu);
    }

    public ArrayList<Menu> getMenus(){
        return menus;
    }

    public String getModifiedDate(){
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate){
        this.modifiedDate = modifiedDate;
    }

    public ArrayList<String> getSavedArticles(){
        return savedArticles;
    }

    public void addSavedArticles(String articleId){
        savedArticles.add(articleId);
    }

    public void addMyQueue(Item article){
        myQueue.add(article);
    }

    public void addToFrontMyQueue(Item article) { myQueue.add(0,article);}

    public ArrayList<Item> getMyQueue(){
        return myQueue;
    }

    public void clearMyQueue(){

        myQueue.clear();
        savedArticles.clear();
    }

    public void deleteFromMyQueue(Item article){

        if(myQueue.contains(article))
             myQueue.remove(article);

        String articleId = article.getIdentifier();

        if(savedArticles.contains(articleId))
            savedArticles.remove(articleId);
    }

    public void eraseData(){

        sections.clear();
        myQueue.clear();
        savedArticles.clear();

    }


    public Item getArticleFromId(String articleId){
        ArrayList<Item> movies = new ArrayList<Item>();
        ArrayList<Item> articleArrayList = FeedManager.getInstance().getVidList();
        for(int j=0;j<articleArrayList.size();j++)
            movies.add(articleArrayList.get(j));

        for(int i=0;i<movies.size();i++){
            if(articleId.equals(movies.get(i).getIdentifier()))
                return movies.get(i);
        }

        return null;
    }

    public void setLogo(String logo){
        this.logo = logo;
    }

    public String getLogo(){
        return logo;
    }

    public ArrayList<Subscriptions> getSubList() {
        return subscriptionsArrayList;
    }

    public void addSub(Subscriptions subscriptions){
        int flag=0;
        int i;
        if(subscriptionsArrayList.size()==0)
            subscriptionsArrayList.add(subscriptions);
        else{
            for(i=0;i<subscriptionsArrayList.size();i++){
                if(subscriptions.getDuration()<subscriptionsArrayList.get(i).getDuration()) {
                    subscriptionsArrayList.add(i, subscriptions);
                    flag++;
                }
            }
            if(flag<1)
                subscriptionsArrayList.add(i,subscriptions);
        }
    }

}
