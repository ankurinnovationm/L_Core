package core2.maz.com.core2.managers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

import java.io.IOException;
import java.io.InputStream;

import core2.maz.com.core2.responsemodel.MasterResponse;

public class JacksonHandler
{
	private static final ObjectMapper OBJECT_MAPPER_FOR_REQUEST = new ObjectMapper();
	private static final ObjectMapper OBJECT_MAPPER_FOR_RESPONSE = new ObjectMapper();

	/*public static String createJson(MasterRequest requestObject) throws CoreException
	{
		String jsonString = "";

		try
		{
			jsonString = OBJECT_MAPPER_FOR_REQUEST.writeValueAsString(requestObject);
		}
		catch (JsonProcessingException exception)
		{
			ExceptionManager.dispatchExceptionDetails("1001", exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			ExceptionManager.dispatchExceptionDetails("1002", exception.getMessage(), exception);
		}

		return jsonString;
	}*/

	public static MasterResponse createResponse(String jsonResponseString, Class<? extends Object> classInstance) throws Exception
	{
		MasterResponse masterResponse = null;

		//Set the Property Naming Strategy
		OBJECT_MAPPER_FOR_RESPONSE.setPropertyNamingStrategy(new CustomizedPropertyNamingStrategy());
		
		try
		{
			masterResponse = (MasterResponse)OBJECT_MAPPER_FOR_RESPONSE.readValue(jsonResponseString, classInstance);
		}
		catch (JsonParseException exception)
		{
		}
		catch (JsonMappingException exception)
		{
			exception.printStackTrace();
		}
		catch (IOException exception)
		{
		}
		catch (Exception exception)
		{
		}

		return masterResponse;
	}
	
	public static MasterResponse createResponse(InputStream inputStream, Class<? extends Object> classInstance) throws Exception
	{
		MasterResponse masterResponse = null;

		//Set the Property Naming Strategy
		OBJECT_MAPPER_FOR_RESPONSE.setPropertyNamingStrategy(new CustomizedPropertyNamingStrategy());
		
		try
		{
			masterResponse = (MasterResponse)OBJECT_MAPPER_FOR_RESPONSE.readValue(inputStream, classInstance);
		}
		catch (JsonParseException exception)
		{
		}
		catch (JsonMappingException exception)
		{
		}
		catch (IOException exception)
		{
		}
		catch (Exception exception)
		{
		}

		return masterResponse;
	}

	private static class CustomizedPropertyNamingStrategy extends PropertyNamingStrategy
	{
		/**
		 * Auto Generated serialVersionUID
		 */
		private static final long serialVersionUID = 837479668977910450L;

		@Override
		public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName)
		{
			return convertName(defaultName);
		}

		@Override
		public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName)
		{
			return convertName(defaultName);
		}

		private String convertName(String defaultName)
		{
			return (Character.toUpperCase(defaultName.charAt(0)) + defaultName.substring(1));
		}
	}
}
