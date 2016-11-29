package core2.maz.com.core2.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ankur Jain on 31-05-2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MasterResponse
{
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("error")
    private String error;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
