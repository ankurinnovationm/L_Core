package core2.maz.com.core2.exception;


public class CoreAppException extends CoreException
{
    /**
     * Default Constructor
     */
    public CoreAppException()
    {
    }

    /**
     * Parameterized Constructor
     * @param exceptionCode
     */
    public CoreAppException(String exceptionCode)
    {
        super(exceptionCode);
    }

    /**
     * Parameterized Constructor
     * @param exceptionCode
     * @param exceptionMessage
     */
    public CoreAppException(String exceptionCode, String exceptionMessage)
    {
        super(exceptionCode, exceptionMessage);
    }
}
