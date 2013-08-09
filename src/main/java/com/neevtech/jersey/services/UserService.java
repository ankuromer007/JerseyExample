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
	@Path("/get/plain/{a}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPlainTextUserInfo(@PathParam("a") int id) {
		User user = (new JAXBConversion()).unmarshalling(id);
		String output = "";
		if(user != null) {
			output = "User with id: " + user.getUserID()
					+ ", name: " + user.getName() + ", age: "
					+ user.getAge() + " and location: "
					+ user.getLocation();
		}else
			output = "User not found with the given ID.";
		return output;
	}

	@GET
	@Path("/get/html/{a}")
	@Produces(MediaType.TEXT_HTML)
	public String getHtmlUserInfo(@PathParam("a") int id) {
		User user = (new JAXBConversion()).unmarshalling(id);
		String output = "";
		if(user != null) {
			output = "<html><title>Hello User</title><body><h1>Hello User</h1><p>Name: "
					+ user.getName()
					+ "<br>ID: "
					+ user.getUserID()
					+ "<br>Age: "
					+ user.getAge()
					+ "<br>Location: "
					+ user.getLocation() + "</p></body></html>";
		}else
			output = "User not found with the given ID.";
		return output;
	}
	
	@GET
	@Path("/get/xml/{a}")
	@Produces(MediaType.TEXT_XML)
	public User getXMLUserInfo(@PathParam("a") int id) {
		return (new JAXBConversion()).unmarshalling(id);
	}
	
	@GET
	@Path("/get/xml")
	@Produces(MediaType.TEXT_XML)
	public Users getXMLUserInfo() {
		return (new JAXBConversion()).unmarshalling();
	}
}
