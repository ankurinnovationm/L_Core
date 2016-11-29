package core2.maz.com.core2.exception;

/**
 * Core Exception - Custom Exception Class. Used as Parent class for all other Custom Exceptions.
 *
 * We will convert System generated Exceptions into one of our own Custom Exception.
 *
 * Created by Nikhil Jain on 5/31/2016.
 */
public class CoreException extends Exception
{
    protected String		exceptionCode;
    protected String	exceptionMessage;

    /**
     * Default Constructor
     */
    public CoreException()
    {
    }

    /**
     * Parameterized Constructor
     * @param exceptionCode
     */
    public CoreException(String exceptionCode)
    {
        this.exceptionCode = exceptionCode;
    }

    /**
     * Parameterized Constructor
     * @param exceptionCode
     * @param exceptionMessage
     */
    public CoreException(String exceptionCode, String exceptionMessage)
    {
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionCode()
    {
        return exceptionCode;
    }

    public String getExceptionMessage()
    {
        return exceptionMessage;
    }
}
