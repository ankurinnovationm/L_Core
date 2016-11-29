package core2.maz.com.core2.managers;

import core2.maz.com.core2.exception.CoreException;

/**
 * Created by Rahul Singh on 6/1/2016.
 */
public class ExceptionManager
{
    public static void dispatchExceptionDetails(String exceptionCode, String exceptionMessage, Exception exceptionObject) throws CoreException
    {
        if (exceptionObject instanceof CoreException)
        {
            throw (CoreException)exceptionObject;
        }

        //Log Exception details
      //  logException(exceptionCode, exceptionMessage, exceptionObject);

        //Throw Exception
        throw new CoreException(exceptionCode, exceptionMessage);
    }
}
