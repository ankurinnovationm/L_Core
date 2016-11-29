package core2.maz.com.core2.model;

/**
 * Created by Kirti Verma on 29-08-2016.
 */
public class Banner {

    private String id;
    private String asset;
    private String modified;
    private int[] sizes;
    private String name;
    private String actionUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public int[] getSizes() {
        return sizes;
    }

    public void setSizes(int[] sizes) {
        this.sizes = sizes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActionUrl(){ return  actionUrl;}

    public void setActionUrl(String actionUrl){this.actionUrl = actionUrl;}
}
