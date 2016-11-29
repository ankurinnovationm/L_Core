package core2.maz.com.core2.responsemodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Ankur Jain on 15-11-2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemListResponseModel extends MasterResponse {
    private ArrayList<ItemResponseModel> items;

    @JsonCreator
    public ItemListResponseModel(ArrayList<ItemResponseModel> items)
    {
        super();
        this.items = items;
    }

    public ArrayList<ItemResponseModel> getItems()
    {
        return items;
    }

    public ItemListResponseModel()
    {
        items = new ArrayList<ItemResponseModel>();
    }


}
