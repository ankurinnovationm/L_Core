package core2.maz.com.core2.exception;

/**
 * Core Business Exception- Custom Exception Class. This is the Exception which will be throw by the methods on any business Failure.
 * Created by Nikhil Jain on 5/31/2016.
 */
public class CoreBusinessException extends CoreException
{
    /**
     * Default Constructor
     */
    public CoreBusinessException()
    {
    }

    /**
     * Parameterized Constructor
     * @param exceptionCode
     */
    public CoreBusinessException(String exceptionCode)
    {
        super(exceptionCode);
    }

    /**
     * Parameterized Constructor
     * @param exceptionCode
     * @param exceptionMessage
     */
    public CoreBusinessException(String exceptionCode, String exceptionMessage)
    {
        super(exceptionCode, exceptionMessage);
    }
}
