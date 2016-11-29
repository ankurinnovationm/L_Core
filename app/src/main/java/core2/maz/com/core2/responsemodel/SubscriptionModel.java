package core2.maz.com.core2.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Kirti Verma on 26-08-2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionModel {

    @JsonProperty("iap")
    private IAPModel iapModel;
    @JsonProperty("duration")
    private int duration;
    @JsonProperty("freeTrial")
    private int freeTrial;
    @JsonProperty("isDefault")
    private boolean isDefault;

    public IAPModel getIapModel() {
        return iapModel;
    }


    public int getDuration() {
        return duration;
    }

    public int getFreeTrial() {
        return freeTrial;
    }

    public boolean isDefault() {
        return isDefault;
    }

}
