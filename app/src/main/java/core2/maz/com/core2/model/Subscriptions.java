package core2.maz.com.core2.model;

/**
 * Created by Kirti Verma on 29-08-2016.
 */
public class Subscriptions {

    private IAP iap;
    private int duration;
    private int freeTrial;
    private boolean isDefault;

    public IAP getIap() {
        return iap;
    }

    public void setIap(IAP iap) {
        this.iap = iap;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getFreeTrial() {
        return freeTrial;
    }

    public void setFreeTrial(int freeTrial) {
        this.freeTrial = freeTrial;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
}
