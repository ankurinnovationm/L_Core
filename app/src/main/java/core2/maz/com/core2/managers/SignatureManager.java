package core2.maz.com.core2.managers;

import core2.maz.com.core2.constants.AppConstants;
import core2.maz.com.core2.responsemodel.SignatureResponseModel;

/**
 * Created by Ankur Jain on 11-11-2016.
 */
public class SignatureManager
{

    /**
     * Get Signature For API Access
     */
    public static String getSignature() throws Exception
    {
        SignatureResponseModel getSignatureResponseModel = null;

        String url = AppConstants.GET_SIGNATURE;

        //Call Web Service
        String jsonResponseString = WebServiceManager.callWebService(url, null, AppConstants.HTTP_METHOD_GET, AppConstants.READ_TIMEOUT_SMALL);

        //Parse Json Response to Model
        getSignatureResponseModel = (SignatureResponseModel) ParseManager.prepareWebServiceResponseObject(SignatureResponseModel.class, jsonResponseString);

        //Process Response

        //Store Signature in Shared Preference
        String signature = storeDataInSharedPreference(getSignatureResponseModel);

        return signature;
    }

    /**
     * Store Signature for API Access in Shared Preference
     * @param getSignatureResponseModel
     */
    private static String storeDataInSharedPreference(SignatureResponseModel getSignatureResponseModel) throws Exception
    {
        String signature = getSignatureResponseModel.getSignature();
        PersistentManager.setSignature(signature);

        return signature;
    }


}
