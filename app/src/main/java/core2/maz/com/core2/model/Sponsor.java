package core2.maz.com.core2.model;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import java.io.Serializable;

/**
 * Created by Kirti Verma on 29-08-2016.
 */
public class Sponsor implements Serializable{

    private String modified;
    private int duration;
    private String image;
    private String name;
    private String header;
    private SectionGradient sponsorBGGradient;
    private String bgImageUrl;

    public String getModified() {
        return modified;
    }

    public String getImage() {
        return image;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public SectionGradient getSponsorBGGradient() {
        return sponsorBGGradient;
    }

    public String getBgImageUrl(){ return bgImageUrl;}

    public void setModified(String modified) {
        this.modified = modified;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setSponsorBGGradient(SectionGradient sponsorBGGradient) {
        this.sponsorBGGradient = sponsorBGGradient;
    }

    public void setBgImageUrl(String bgImageUrl){
        this.bgImageUrl = bgImageUrl;
    }

    public GradientDrawable getGradientDrawable(){
        int top = Color.parseColor(sponsorBGGradient.getTop());
        int bottom = Color.parseColor(sponsorBGGradient.getBottom());
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{top, bottom});
        return gradientDrawable;
    }
}