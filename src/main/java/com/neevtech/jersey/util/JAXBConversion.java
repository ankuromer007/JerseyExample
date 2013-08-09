package com.neevtech.jersey.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.neevtech.jersey.model.User;
import com.neevtech.jersey.model.Users;

public class JAXBConversion {

	private static final String USERS_XML = "src/main/resources/users-jaxb.xml";
	private static JAXBContext jaxbContext;
	
	public JAXBConversion(){
		try{
			jaxbContext = JAXBContext.newInstance(Users.class);			
		} catch(JAXBException ex){
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) throws JAXBException, IOException {

		ArrayList<User> userList = new ArrayList<User>();

		User user1 = new User();
		user1.setUserID(1);
		user1.setName("ankur");
		user1.setAge(24);
		user1.setLocation("Bangalore");
		userList.add(user1);
		
		User user2 = new User();
		user2.setUserID(2);
		user2.setName("meenu");
		user2.setAge(25);
		user2.setLocation("Lucknow");
		userList.add(user2);

		Users users = new Users();
		users.setUserList(userList);

		new JAXBConversion();
		Marshaller m = jaxbContext.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		m.marshal(users, System.out);
		m.marshal(users, new File(USERS_XML));

		System.out.println();
		new JerseyClient("user", "get").userService(MediaType.TEXT_HTML, 1);
		new JerseyClient("user", "get").userService(MediaType.TEXT_PLAIN, 2);
		new JerseyClient("user", "get").userService(MediaType.TEXT_XML, 0);
		new JerseyClient("user", "get").userService(MediaType.TEXT_XML, 1);
	}
	
	public User unmarshalling(int id) {
		Users users = null;
		User user = null;
		try{			
			Unmarshaller um = jaxbContext.createUnmarshaller();
			users = (Users) um.unmarshal(new FileReader(USERS_XML));
		} catch(JAXBException ex){
			ex.printStackTrace();
		} catch(IOException ex){
			ex.printStackTrace();
		}
		for (User obj : users.getUserList()) {
			if(obj.getUserID() == id)
				user = obj;
		}
		return user;
	}
	
	public Users unmarshalling() {
		Users users = null;
		try{			
			Unmarshaller um = jaxbContext.createUnmarshaller();
			users = (Users) um.unmarshal(new FileReader(USERS_XML));
		} catch(JAXBException ex){
			ex.printStackTrace();
		} catch(IOException ex){
			ex.printStackTrace();
		}
		return users;
	}
}
