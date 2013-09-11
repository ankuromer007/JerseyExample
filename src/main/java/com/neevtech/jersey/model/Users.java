package com.neevtech.jersey.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "com.neevtech.jersey.model")
public class Users {
	private ArrayList<User> userList;

	@XmlElementWrapper(name = "user-list")
	@XmlElement(name = "user")
	public ArrayList<User> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}
}
