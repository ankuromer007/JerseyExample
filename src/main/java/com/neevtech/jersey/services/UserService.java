package com.neevtech.jersey.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.neevtech.jersey.model.User;
import com.neevtech.jersey.model.Users;
import com.neevtech.jersey.util.JAXBConversion;

@Path("/user")
public class UserService {

	@GET
	@Path("/get/{a}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPlainTextUserInfo(@PathParam("a") int id) {
		Users users = (new JAXBConversion()).unmarshalling();
		String output = "";
		for (User user : users.getUserList()) {
			if(user.getUserID() == id) {				
				output = "User with id: " + user.getUserID()
						+ ", name: " + user.getName() + ", age: "
						+ user.getAge() + " and location: "
						+ user.getLocation();
			}
		}
		if(output.length() == 0)
			output = "User not found with the given ID.";
		return output;
	}

	@GET
	@Path("/get")
	@Produces(MediaType.TEXT_XML)
	public Users getXMLUserInfo() {
		return (new JAXBConversion()).unmarshalling();
	}

	@GET
	@Path("/get/{a}")
	@Produces(MediaType.TEXT_HTML)
	public String getHtmlUserInfo(@PathParam("a") int id) {
		Users users = (new JAXBConversion()).unmarshalling();
		String output = "";
		for (User user : users.getUserList()) {
			if(user.getUserID() == id) {
				output = "<html><title>Hello User</title><body><h1>Hello User</h1><p>Name: "
						+ user.getName()
						+ "<br>ID: "
						+ user.getUserID()
						+ "<br>Age: "
						+ user.getAge()
						+ "<br>Location: "
						+ user.getLocation() + "</p></body></html>";
			}
		}
		if(output.length() == 0)
			output = "User not found with the given ID.";
		return output;
	}
}
