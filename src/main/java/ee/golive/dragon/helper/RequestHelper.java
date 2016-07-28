package ee.golive.dragon.helper;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.UnsupportedEncodingException;

/**
 * @author Taavi Ilves, Golive, ilves.taavi@gmail.com
 */
public class RequestHelper {

    private HttpClient httpClient;

    public RequestHelper(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Gets content from provided url
     *
     * @param url Url to GET the content from
     * @return Response body from the url
     */
    public String get(String url) {
        GetMethod method = new GetMethod(url);
        return execute(method);
    }

    /**
     * Put provided content to provided url as json string and return the response
     *
     * @param url Url to PUT the data
     * @param content Content to send
     * @return Response body from the url
     * @throws UnsupportedEncodingException
     */
    public String put(String url, String content) throws UnsupportedEncodingException {
        PutMethod method = new PutMethod(url);
        StringRequestEntity requestEntity = new StringRequestEntity(content, "application/json", "UTF-8");
        method.setRequestEntity(requestEntity);
        return execute(method);
    }

    /**
     * Executes the provided method and returns the response body as a string
     *
     * @param method Method to execute
     * @return Response body
     */
    private String execute(HttpMethodBase method) {
        String response = null;
        try {
            httpClient.executeMethod(method);
            response = method.getResponseBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return response;
    }
}
