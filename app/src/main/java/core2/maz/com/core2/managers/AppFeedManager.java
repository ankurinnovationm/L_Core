package core2.maz.com.core2.managers;

import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;

import core2.maz.com.core2.application.MyApplication;
import core2.maz.com.core2.constants.AppConstants;
import core2.maz.com.core2.exception.CoreBusinessException;
import core2.maz.com.core2.model.Banner;
import core2.maz.com.core2.model.Feed;
import core2.maz.com.core2.model.KeyListModel;
import core2.maz.com.core2.model.Menu;
import core2.maz.com.core2.model.SectionBackground;
import core2.maz.com.core2.model.SectionGradient;
import core2.maz.com.core2.model.Sponsor;
import core2.maz.com.core2.responsemodel.AppFeedResponseModel;
import core2.maz.com.core2.responsemodel.BannerModel;
import core2.maz.com.core2.responsemodel.ItemListResponseModel;
import core2.maz.com.core2.responsemodel.ItemResponseModel;
import core2.maz.com.core2.responsemodel.SectionBackgroundModel;
import core2.maz.com.core2.responsemodel.SectionGradientResponseModel;
import core2.maz.com.core2.responsemodel.SectionResponseModel;
import core2.maz.com.core2.responsemodel.SponsorResponseModel;
import core2.maz.com.core2.utills.AppUtils;

/**
 * Created by Ankur Jain on 10-11-2016.
 */
public class AppFeedManager {

    public static HashMap<String,Menu> myMap = new HashMap<>();
    public static HashMap<String,KeyListModel> keyHashMap = new HashMap<>();
    public static HashMap<String,String> parentMap = new HashMap<>();

    private static AppFeedResponseModel getAppFeed() throws Exception
    {
        AppFeedResponseModel model = null;
        String signature =null;
        try
        {

            signature = getSignature();
            String url = AppConstants.GET_APP_FEED + signature;
            String jsonResponseString = WebServiceManager.callWebService(url, "", AppConstants.HTTP_METHOD_GET, AppConstants.TIME_CONNECTION_DATA_WAIT_TIMEOUT_LARGE);
            model = (AppFeedResponseModel) ParseManager.prepareWebServiceResponseObject(AppFeedResponseModel.class, jsonResponseString);
            Feed feed = fillAppFeedModel(model);
            writeFeedDataToFile(feed);
            handleItemFeed(feed.getSections(), null);
            return model;
        }
        catch (CoreBusinessException exception)
        {
            if(exception.getExceptionCode().equals(AppConstants.ERROR_CODE_403))
            {
                SignatureManager.getSignature();
                getAppFeed();
            }
        }
        return model;
    }


    private static void handleItemFeed(ArrayList<Menu> menuArrayList,Menu parent)throws Exception
    {
        ItemListResponseModel itemListResponseModel = null;

        ArrayList<Menu> menus = menuArrayList;
        int size= menus.size();
        int count =0;
        String signature = getSignature();
        for(Menu menu :menus)
        {
            count++;
            myMap.put(menu.getIdentifier(), menu);

            if (menu.getType().equalsIgnoreCase("menu") || menu.getType().equals("fake"))
            {
                String url = menu.getContentUrl() + signature;
                String jsonResponseString = WebServiceManager.callWebService(url, "", AppConstants.HTTP_METHOD_GET, AppConstants.TIME_CONNECTION_DATA_WAIT_TIMEOUT_LARGE);
                itemListResponseModel = (ItemListResponseModel) ParseManager.prepareWebServiceResponseObject(ItemListResponseModel.class, jsonResponseString);
                ArrayList<Menu> menus1 = prepareMenuList(itemListResponseModel);
                KeyListModel keyListModel = new KeyListModel();
                ArrayList<String> keys = new ArrayList<>();

                for (Menu menu1 : menus1)
                {
                    parentMap.put(menu1.getIdentifier(),menu.getIdentifier());
                    keys.add(menu1.getIdentifier());
                }

                keyListModel.setKeyList(keys);
                keyHashMap.put(menu.getIdentifier(), keyListModel);
                writeFileToStorage(menu.getIdentifier(), menus1);
                handleItemFeed(menus1, menu);


            }
            if (count == size)
            {
                if (parent != null)
                {
                    fireBroadcast(parent.getTitle()+" is Complete");
                }
            }
        }

    }

    private static void  writeFileToStorage(String key,ArrayList<Menu> menus) throws Exception
    {
        String folderDirectory = FileManager.getFolderOnExternalDirectory(key).getAbsolutePath();

        String fileName = folderDirectory;
        for(Menu menu: menus)
        {
            FileManager.writeObject(menu, fileName);
        }


    }
    private static void  writeFileToStorage(String key,String logo) throws Exception
    {
        String folderDirectory = FileManager.getFolderOnExternalDirectory(key).getAbsolutePath();

        String fileName = folderDirectory;
        FileManager.writeObject(logo, fileName);


    }

    private static void  writeFileToStorage(String key,Object object) throws Exception
    {
        String folderDirectory = FileManager.getFolderOnExternalDirectory(key).getAbsolutePath();
        String fileName = folderDirectory+"/"+"sponsor";
        FileManager.writeObject(object, fileName);
    }


    private static void fireBroadcast(String identifier)
    {
        Intent intent  = new Intent("com.maz.node_complete_broadcast");
        intent.putExtra("identifier",identifier);
        MyApplication.getAppContext().sendBroadcast(intent);
    }


    /**
     * get Token if exist in shared preference return token  else call webApi
     * @return
     * @throws Exception
     */

    public static String getSignature() throws Exception
    {

        String signature = PersistentManager.getSignature();
        if(AppUtils.isEmpty(signature))
        {
            signature = SignatureManager.getSignature();

        }
        return signature;
    }


    /**
     * refresh complete feed on the basis of modified date and sends broadcast as one node is access and prepare completely
     * @throws Exception
     */
    public static void refreshFeed()throws Exception
    {
        getAppFeed();
    }

    /**
     * return parent of any item if exist else return null
     * @param key
     * @return
     */
    public static Menu getParent(String key)
    {
        String parentIdentifier = parentMap.get(key);
        Menu parent = myMap.get(parentIdentifier);
        return parent;
    }

    /**
     * return particular menu on the basis of identifier
     * @param identifier
     * @return
     */
    public static Menu getItem(String identifier)
    {
        return myMap.get(identifier);
    }

    /**
     * return all child of its parent regarding key as identifier
     * @param mapKey
     * @return
     */

    public static ArrayList<Menu> getMenus(String mapKey)
    {
        KeyListModel model = keyHashMap.get(mapKey);
        if(model!=null)
        {
            ArrayList<String> keyes = keyHashMap.get(mapKey).getKeyList();
            ArrayList<Menu> menus = new ArrayList<>();
            for (String key : keyes) {
                Menu menu = myMap.get(key);
                if (menu != null)
                {
                    menus.add(myMap.get(key));
                }
            }
            return menus;
        }

        else
        {
            return null;
        }

    }

    private class getSignatureAsynkTask extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private static Feed fillAppFeedModel(AppFeedResponseModel model) throws Exception
    {
        Feed  feed = new Feed();

        if (model.getBanners() != null)
        {
            feed.setBanners(getBanners(model.getBanners()));
        }
        if (model.getModified() != null)
        {
            feed.setModified(model.getModified());
        }
        feed.setLogo(model.getLogo().getUrl());
        feed.setSponsor(getSponsor(model.getSponsor()));
        feed.setHideSubscriberLogin(model.isHideSubscriberLogin());
        feed.setSections(getSection(model.getSections()));
        feed.setSubscriptionAllAccess(model.isSubscriptionAllAccess());
        feed.setSubscriptionLabel(model.getSubscriptionLabel());
        feed.setSubscriptionTitle(model.getSubscriptionTitle());
        feed.setPrimaryColor(model.getPrimaryThemeColor());
        feed.setSecondaryColor(model.getSecondaryThemeColor());
        CachingManager.setAppFeed(feed);
        CachingManager.setSections(getSection(model.getSections()));
        return feed;
    }

    private static void writeFeedDataToFile(Feed feed) throws Exception
    {
        String logo = feed.getLogo();
        if(logo!=null && !logo.isEmpty())
        {
            writeFileToStorage("Logo", logo);
        }
        ArrayList<Menu> menus = feed.getSections();
        if(menus!=null && !menus.isEmpty())
        {
            writeFileToStorage("Sections",menus);
        }
        Sponsor sponsor = feed.getSponsor();
        if(sponsor!=null )
        {
            writeFileToStorage("Sponsor",sponsor);
        }
        ArrayList<Banner> banner = feed.getBanners();

    }



    private static ArrayList<Banner> getBanners(ArrayList<BannerModel> bannerResponseModel)
    {
        ArrayList<Banner> bannerList = new ArrayList<>();
        Banner banner = new Banner();
        for(BannerModel bannerMoedl: bannerResponseModel)
        {
            banner.setActionUrl(bannerMoedl.getActionUrl());
            banner.setAsset(bannerMoedl.getAsset());
            banner.setId(bannerMoedl.getId());
            banner.setModified(bannerMoedl.getModified());
            banner.setName(bannerMoedl.getName());
            banner.setSizes(bannerMoedl.getSizes());
            bannerList.add(banner);
        }
        return bannerList;
    }

    private static Sponsor getSponsor(SponsorResponseModel sponsorResponseModel)
    {
        Sponsor sponsor = new Sponsor();
        sponsor.setModified(sponsorResponseModel.getModified());
        sponsor.setName(sponsorResponseModel.getName());
        sponsor.setBgImageUrl(sponsorResponseModel.getBgImageUrl());
        sponsor.setDuration(sponsorResponseModel.getDuration());
        sponsor.setHeader(sponsorResponseModel.getHeader());
        sponsor.setImage(sponsorResponseModel.getImage());
        return sponsor;
    }

    private static ArrayList<Menu> getSection(ArrayList<SectionResponseModel> sections) throws Exception
    {
        ArrayList<Menu> items = new ArrayList<>();
        HashMap<String,Menu> sectionMap = new HashMap<>();
        for(SectionResponseModel sectionResponseModel:sections)
        {
            Menu menu = new Menu();
            menu.setCustomUrl(sectionResponseModel.getCustomUrl());
            menu.setIdentifier(sectionResponseModel.getIdentifier());
            menu.setDefault(sectionResponseModel.isDefault());
            menu.setLayout(sectionResponseModel.getLayout());
            menu.setLocked(sectionResponseModel.isLocked());
            if(sectionResponseModel.getLogo()!=null)
            {
                menu.setLogo(sectionResponseModel.getLogo().getUrl());
            }
            menu.setModified(sectionResponseModel.getModified());
            menu.setShareable(sectionResponseModel.isShareable());
            menu.setType(sectionResponseModel.getType());
            menu.setTitle(sectionResponseModel.getName());
            menu.setShowBanner(sectionResponseModel.isShowBanner());
            menu.setContentUrl(sectionResponseModel.getItems());
            menu.setImage(sectionResponseModel.getIconUrl());
            sectionMap.put(menu.getIdentifier(), menu);

            CachingManager.setSectionMap(sectionMap);
            items.add(menu);
        }

        return items;
    }

    private static ArrayList<Menu> prepareMenuList(ItemListResponseModel itemListResponseModel)
    {
        ArrayList<ItemResponseModel> itemListResponseModels = itemListResponseModel.getItems();
        ArrayList<Menu> menus = new ArrayList<>();
        for(ItemResponseModel item: itemListResponseModels)
        {
            Menu menu =  new Menu();
            menu.setContentUrl(item.getItems());
            menu.setDefault(item.isDefault());
            if(item.getArticleCoverModel()!=null)
            {
                menu.setImage(item.getArticleCoverModel().getUrl());
            }
            menu.setShowBanner(item.isShowBanner());
            menu.setBackground(getBackGround(item.getBackgroundModel()));
            menu.setCoverDate(item.getCoverDate());
            menu.setCustomUrl(item.getCustomUrl());
            menu.setLocked(item.isLocked());
            menu.setLayout(item.getLayout());
            menu.setType(item.getType());
            menu.setTitle(item.getTitle());
            menu.setIdentifier(item.getIdentifier());
            menus.add(menu);
        }
        return menus;
    }

    private static SectionBackground getBackGround(SectionBackgroundModel model)
    {
        SectionBackground sectionBackground = new SectionBackground();
//        sectionBackground.setImage(model.getImage());
//        sectionBackground.setSectionGradient(getSectionBackgroundGradient(model.getSectionGradientModel()));
//        sectionBackground.setVideo(model.getVideo());
        return sectionBackground;
    }

    private static SectionGradient getSectionBackgroundGradient(SectionGradientResponseModel sectionGradientResponseModel)
    {
        SectionGradient sectionGradient  = new SectionGradient();
        sectionGradient.setBottom(sectionGradientResponseModel.getBottom());
        sectionGradient.setTop(sectionGradientResponseModel.getTop());
        return sectionGradient;
    }
}
