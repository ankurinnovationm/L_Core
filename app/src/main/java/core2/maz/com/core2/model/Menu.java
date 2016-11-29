package core2.maz.com.core2.model;

import java.util.ArrayList;

/**
 * Created by Kirti Verma on 29-08-2016.
 */
public class Menu extends Item{


    private boolean locked;
    private boolean shareable;
    private String coverDate;
    private boolean isDefault;
    private MenuAccess menuAccess;
    private String layout;
    private SectionBackground background;
    private ArrayList<Item> itemList;
    private ArrayList<Menu> menus;

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    private boolean isSection;
    private boolean showBanner;
    private String customUrl;
    private boolean customVisited;
    private Menu lastMenu;
    private String logo;
    private boolean noMenu;
    private boolean special;

    public Menu(){ itemList = new ArrayList<Item>();}

    public ArrayList<Item> getItemList(){ return itemList;}

    public void addItem(Item item){
        itemList.add(item);
    }



    public String getCoverDate(){ return coverDate;}
    public boolean isLocked(){return locked;}
    public boolean isShareable(){return shareable;}
    public boolean isDefault(){ return isDefault;}
    public MenuAccess getMenuAccess(){return menuAccess;}
    public String getLayout(){return layout;}
    public SectionBackground getBackground(){return background;}
    public boolean isSection(){return isSection;}
    public boolean isShowBanner(){return showBanner;}
    public String getCustomUrl(){return customUrl;}
    public boolean isCustomVisited(){return customVisited;}
    public Menu getLastMenu(){return lastMenu;}
    public String getLogo(){return logo;}
    public boolean isNoMenu(){return noMenu;}
    public boolean isSpecial(){return special;}

    public void setLocked(boolean locked){this.locked = locked;}
    public void setShareable(boolean shareable){this.shareable = shareable;}
    public void setCoverDate(String coverDate){this.coverDate = coverDate;}
    public void setDefault(boolean isDefault){this.isDefault = isDefault;}
    public void setMenuAccess(MenuAccess menuAccess){this.menuAccess = menuAccess;}
    public void setLayout(String layout){ this.layout = layout;}
    public void setBackground(SectionBackground background){ this.background = background;}
    public void setSection(boolean isSection){ this.isSection = isSection;}
    public void setShowBanner(boolean showBanner){ this.showBanner = showBanner;}
    public void setCustomUrl(String customUrl){ this.customUrl = customUrl;}
    public void setCustomVisited(boolean customVisited){this.customVisited = customVisited;}
    public void setLastMenu(Menu lastMenu){this.lastMenu = lastMenu;}
    public void setLogo(String logo){ this.logo = logo;}
    public void setNoMenu(boolean noMenu){this.noMenu = noMenu;}
    public void setSpecial(boolean special){this.special = special;}
}
