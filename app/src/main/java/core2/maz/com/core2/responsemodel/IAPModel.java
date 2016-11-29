package core2.maz.com.core2.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Kirti Verma on 26-08-2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class IAPModel implements Serializable
{
    @JsonProperty("identifier")
    private String identifier;
    @JsonProperty("price")
    private float price;

    public String getIdentifier() {
        return identifier;
    }

    public float getPrice() {
        return price;
    }
}
