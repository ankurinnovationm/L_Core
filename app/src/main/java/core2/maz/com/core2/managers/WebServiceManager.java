package core2.maz.com.core2.managers;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Locale;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import core2.maz.com.core2.constants.AppConstants;
import core2.maz.com.core2.exception.CoreBusinessException;

/**
 * Created by Ankur Jain on 10-11-2016.
 */
public class WebServiceManager {
    private static String TAG = "WebServiceManager";

    // Hit Web Service
    public static String callWebService(String url, String jsonRequestString, int httpRequestType, int readTimeOutType) throws Exception {
        String jsonResponseString = WebServiceManager.requestServer(url, jsonRequestString, httpRequestType, readTimeOutType);
        return jsonResponseString;
    }

    /**
     * Request Web Service for Getting Response
     *
     * @param targetUrl
     * @param jsonRequestString
     * @param httpMethod
     * @param readTimeOutType
     * @return jsonResponseString
     */
    private static String requestServer(String targetUrl, String jsonRequestString, int httpMethod, int readTimeOutType) throws Exception{
        String jsonResponseString = null;
        HttpsURLConnection httpsUrlConnection = null;
        URL url;
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[]
                    {
                            new X509TrustManager() {
                                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                                }

                                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                                }

                                public X509Certificate[] getAcceptedIssuers() {
                                    return new X509Certificate[]{};
                                }
                            }
                    }, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());

            HttpsURLConnection.setDefaultHostnameVerifier(
                    new HostnameVerifier() {
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    });


            url = new URL(targetUrl);
            httpsUrlConnection = (HttpsURLConnection) url.openConnection();

            httpsUrlConnection.setConnectTimeout(AppConstants.TIME_CONNECTION_TIMEOUT);
            if (readTimeOutType == AppConstants.READ_TIMEOUT_SMALL)
            {
                httpsUrlConnection.setReadTimeout(AppConstants.TIME_CONNECTION_DATA_WAIT_TIMEOUT_SMALL);
            }
            else
            {
                httpsUrlConnection.setReadTimeout(AppConstants.TIME_CONNECTION_DATA_WAIT_TIMEOUT_LARGE);
            }

            httpsUrlConnection.setDoInput(true);

            if (httpMethod == AppConstants.HTTP_METHOD_POST)
            {

                httpsUrlConnection.setDoOutput(true); // to enable data sending
                // (as post request)

                httpsUrlConnection.setRequestProperty("Accept", "application/json");
                httpsUrlConnection.setRequestProperty("Content-type", "application/json");

                String localeCode = Locale.getDefault().toString();
                httpsUrlConnection.setRequestProperty("Accept-Language", localeCode);
                // connection to send request data
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsUrlConnection.getOutputStream());
                // sending request
                outputStreamWriter.write(jsonRequestString);
                // data
                outputStreamWriter.flush();
            }
            else if (httpMethod == AppConstants.HTTP_METHOD_DELETE) {
                // (as post request)
                httpsUrlConnection.setRequestMethod("DELETE");
                httpsUrlConnection.setRequestProperty("Accept", "application/json");
                httpsUrlConnection.setRequestProperty("Content-type", "application/json");

                String localeCode = Locale.getDefault().toString();
                httpsUrlConnection.setRequestProperty("Accept-Language", localeCode);
            }
            else
            {
                httpsUrlConnection.setDoOutput(false); // disabling post request
            }

            int statusCode = httpsUrlConnection.getResponseCode();
            // Log.e("status code", String.valueOf(statusCode));

            InputStream inputStream = null;

            if (statusCode == 200)
            {
                inputStream = httpsUrlConnection.getInputStream();
            }
            else if (statusCode == 403)
            {
                throw new CoreBusinessException(AppConstants.ERROR_CODE_403, "Unauthorized");
            }
            else
            {
                inputStream = httpsUrlConnection.getErrorStream();
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String jsonResponseLine;
            StringBuilder jsonStringBuilder = new StringBuilder();

            while ((jsonResponseLine = bufferedReader.readLine()) != null) {
                jsonStringBuilder.append(jsonResponseLine);
            }

            bufferedReader.close();
            jsonResponseString = jsonStringBuilder.toString();

        }
        catch (MalformedURLException exception)
        {
            ExceptionManager.dispatchExceptionDetails(AppConstants.ERROR_CODE_1008, exception.getMessage(), exception);
        }
        catch (IOException exception)
        {
            ExceptionManager.dispatchExceptionDetails(AppConstants.ERROR_CODE_1009, exception.getMessage(), exception);
        }
        catch(CoreBusinessException exception)
        {
            ExceptionManager.dispatchExceptionDetails(AppConstants.ERROR_CODE_403, exception.getMessage(), exception);
        }
        catch (Exception exception)
        {
            ExceptionManager.dispatchExceptionDetails(AppConstants.ERROR_CODE_1010, exception.getMessage(), exception);
        }
        finally
        {
            if (httpsUrlConnection != null)
            {
                httpsUrlConnection.disconnect();
            }
        }
        if (jsonResponseString != null)
        {
            Log.e("RESPONSE: " + targetUrl, jsonResponseString);
        }
        else
        {
            Log.e("RESPONSE: " + targetUrl, "Null");
        }
        return jsonResponseString;
    }
    public static String requestServer(String targetUrl, String jsonRequestString, int httpMethod, int readTimeOutType, boolean isIgnore) throws Exception {
        String jsonResponseString = null;
        HttpURLConnection httpUrlConnection = null;
        URL url;
        try {
            if (isIgnore) {
                SSLContext ctx = SSLContext.getInstance("TLS");
                ctx.init(null, new TrustManager[]
                        {
                                new X509TrustManager() {
                                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                                    }

                                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                                    }

                                    public X509Certificate[] getAcceptedIssuers() {
                                        return new X509Certificate[]{};
                                    }
                                }
                        }, null);
                HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());

                HttpsURLConnection.setDefaultHostnameVerifier(
                        new HostnameVerifier() {
                            public boolean verify(String hostname, SSLSession session) {
                                return true;
                            }
                        });

            }
            url = new URL(targetUrl);
            httpUrlConnection = (HttpURLConnection) url.openConnection();

            httpUrlConnection.setConnectTimeout(AppConstants.TIME_CONNECTION_TIMEOUT);
            if (readTimeOutType == AppConstants.READ_TIMEOUT_SMALL) {
                httpUrlConnection.setReadTimeout(AppConstants.TIME_CONNECTION_DATA_WAIT_TIMEOUT_SMALL);
            } else {
                httpUrlConnection.setReadTimeout(AppConstants.TIME_CONNECTION_DATA_WAIT_TIMEOUT_LARGE);
            }

            httpUrlConnection.setDoInput(true);
            if (httpMethod == AppConstants.HTTP_METHOD_POST) {
                httpUrlConnection.setDoOutput(true); // to enable data sending
                // (as post request)

                httpUrlConnection.setRequestProperty("Accept", "application/json");
                httpUrlConnection.setRequestProperty("Content-type", "application/json");

                String localeCode = Locale.getDefault().toString();
                httpUrlConnection.setRequestProperty("Accept-Language", localeCode);
                // connection to send request data
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpUrlConnection.getOutputStream());
                // sending request
                outputStreamWriter.write(jsonRequestString);
                // data
                outputStreamWriter.flush();
            } else if (httpMethod == AppConstants.HTTP_METHOD_DELETE) {
                // (as post request)
                httpUrlConnection.setRequestMethod("DELETE");
                httpUrlConnection.setRequestProperty("Accept", "application/json");
                httpUrlConnection.setRequestProperty("Content-type", "application/json");

                String localeCode = Locale.getDefault().toString();
                httpUrlConnection.setRequestProperty("Accept-Language", localeCode);
            } else {
                httpUrlConnection.setDoOutput(false); // disabling post request
            }

            int statusCode = httpUrlConnection.getResponseCode();
            // Log.e("status code", String.valueOf(statusCode));

            InputStream inputStream = null;

            if (statusCode == 200) {
                inputStream = httpUrlConnection.getInputStream();
            } else if (statusCode == 403) {
            } else {
                inputStream = httpUrlConnection.getErrorStream();
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String jsonResponseLine;
            StringBuilder jsonStringBuilder = new StringBuilder();

            while ((jsonResponseLine = bufferedReader.readLine()) != null) {
                jsonStringBuilder.append(jsonResponseLine);
            }

            bufferedReader.close();
            jsonResponseString = jsonStringBuilder.toString();

        } catch (MalformedURLException exception) {
        } catch (IOException exception) {
        } catch (Exception exception) {
        } finally {
            if (httpUrlConnection != null) {
                httpUrlConnection.disconnect();
            }
        }
        if (jsonResponseString != null) {
            Log.e("RESPONSE: " + targetUrl, jsonResponseString);
        } else {
            Log.e("RESPONSE: " + targetUrl, "Null");
        }
        return jsonResponseString;
    }

}
