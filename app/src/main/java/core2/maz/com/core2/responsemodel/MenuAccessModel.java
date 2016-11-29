package core2.maz.com.core2.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Kirti Verma on 26-08-2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuAccessModel implements Serializable {
        @JsonProperty("timed")
        private int timed;
        @JsonProperty("iap")
        private IAPModel iapModel;

    public int getTimed() {
        return timed;
    }

    public IAPModel getIapModel() {
        return iapModel;
    }

}


