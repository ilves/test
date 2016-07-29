/*
 * The MIT License
 *
 * Copyright (c) 2016 Taavi Ilves (https://github.com/ilves)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
