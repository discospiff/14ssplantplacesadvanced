package com.plantplaces.plantplaces14ssadvanced.dao;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Low level networking utlitiy.
 * @author Brandan
 *
 */
public class NetworkDAO {

	/**
	 * Make a network call to the URI, and return the data that results.
	 * @param uri the URI that we wish to invoke, using the GET method.
	 * @return the data from the URI
	 * @throws ClientProtocolException if we have any trouble making the network call.
	 * @throws IOException if we have any trouble making the network call.
	 */
	public String request(String uri) throws ClientProtocolException, IOException {
		// declare our return variable.
		String result = "";

		// make a get call to HTTP.
		HttpGet httpGet = new HttpGet(uri);
		
		// handle the response that we get in return
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
 		
		// create an HTTPClient, which will coordiate the get and response.
		HttpClient httpClient = new DefaultHttpClient();
		
		// send the URI to the get method, and have the response handler parse it and return a 
		// result to us.
		result = httpClient.execute(httpGet, responseHandler);
		
		// return our return variable.
		return result;
	}
	
	
}
