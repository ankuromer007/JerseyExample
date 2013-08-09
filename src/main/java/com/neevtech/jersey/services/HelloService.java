package com.neevtech.jersey.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloService {

	@GET
	@Path("/get")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}

	@GET
	@Path("/get")
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>\n\t" + "<hello> Hello Jersey " + "</hello>";
	}

	@GET
	@Path("/get")
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html>\n\t" + "<title>" + "Hello Jersey" + "</title>\n\t"
				+ "<body>\n\t\t<h1>" + "Hello Jersey" + "</h1>\n\t</body>\n" + "</html> ";
	}
}
