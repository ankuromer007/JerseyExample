package com.neevtech.jersey.util;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClient {
	
	private static WebResource webResource;
	
	public JerseyClient(String service, String method){
		Client client = Client.create();
		webResource = client.resource("http://localhost:8080/JerseyExample/rest");
		webResource = webResource.path(service).path(method);
	}

	public static void main(String[] args) {
		try {
			new JerseyClient("hello", "get");

			ClientResponse response = webResource.accept(MediaType.TEXT_HTML).get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println("In HTML:\n" + output + "\n\n");
			System.out.println("In XML:\n" + webResource.accept(MediaType.TEXT_XML).get(String.class) + "\n\n");
			System.out.println("In Plain Text:\n" + webResource.accept(MediaType.TEXT_PLAIN).get(String.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void userService(String format, int userID){
		ClientResponse response;
		if(userID == 0)
			response = webResource.accept(format).get(ClientResponse.class);
		else
			response = webResource.path("" + userID).accept(format).get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		}

		String output = response.getEntity(String.class);
		System.out.println("Output from Server .... \n");
		System.out.println("In " + format + ":\n" + output);
	}
}
