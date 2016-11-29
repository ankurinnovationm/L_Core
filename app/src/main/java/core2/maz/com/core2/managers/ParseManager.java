package core2.maz.com.core2.managers;


import core2.maz.com.core2.responsemodel.MasterResponse;

public class ParseManager
{
	//Prepare JSON request string
	/*public static String prepareJsonRequest(MasterRequest requestObject) throws CoreException
	{
		//Create JSON String
		String jsonRequestString = JacksonHandler.createJson(requestObject);

		return jsonRequestString;
	}
*/
	//Prepare Web Service Object Model
	public static MasterResponse prepareWebServiceResponseObject(Class<? extends Object> classInstance, String jsonResponseString) throws Exception
	{
		//Get Response Object
		MasterResponse masterResponse = JacksonHandler.createResponse(jsonResponseString, classInstance);

		return masterResponse;
	}
}
