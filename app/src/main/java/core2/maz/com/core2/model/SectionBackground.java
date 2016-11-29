package core2.maz.com.core2.model;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import java.io.Serializable;

/**
 * Created by Kirti Verma on 29-08-2016.
 */
public class SectionBackground  implements Serializable{

    private String image;
    private String video;
    private SectionGradient sectionGradient;

    public SectionGradient getSectionGradient() {
        return  sectionGradient;
    }

    public String getImage() {
        return image;
    }

    public String getVideo() {return video;}

    public void setSectionGradient(SectionGradient sectionGradient) {

        this.sectionGradient = sectionGradient;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setVideo(String video) { this.video = video;}

    public GradientDrawable getGradientDrawable(){

        int top = Color.parseColor(sectionGradient.getTop());
        int bottom = Color.parseColor(sectionGradient.getBottom());
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{top, bottom});
        return gradientDrawable;
    }
}
