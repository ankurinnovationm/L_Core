package core2.maz.com.core2.responsemodel;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ankur Jain on 15-11-2016.
 */
public class SignatureResponseModel extends MasterResponse {
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @JsonProperty("signature")
    private String signature;
}
