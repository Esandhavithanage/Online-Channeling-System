package com;

import model.Doctor;

//For REST Service 
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Doctors")
public class DoctorService {
	Doctor DocObj = new Doctor();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return DocObj.readDoctor();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String AddDoctor(
			@FormParam("fName") String D_fname,
			@FormParam("lNmae") String D_lname,
			@FormParam("gender") String D_gender, 
			@FormParam("age") int D_age, 
			@FormParam("docNIC") String D_docNIC,
			@FormParam("docEmail") String D_docEmail,
			@FormParam("passwod") String D_password,
			@FormParam("phoneNumber") int D_phonenumber) {
		
		System.out.println(D_fname + " " + D_lname + " " + D_gender + " " + D_age + " " + D_docNIC + " " + D_docEmail
				+ " " + D_password + " " + D_phonenumber);
		
		String output = DocObj.AddDoctor(D_fname, D_lname, D_gender, D_age, D_docNIC, D_docEmail, D_password,D_phonenumber);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(String doctorData) {
		// Convert the input string to a JSON object
		JsonObject doctorObject = new JsonParser().parse(doctorData).getAsJsonObject();
		// Read the values from the JSON object
		int docId = doctorObject.get("docId").getAsInt();
		String D_fname = doctorObject.get("fName").getAsString();
		String D_lname = doctorObject.get("lNmae").getAsString();
		String D_gender = doctorObject.get("gender").getAsString();
		int D_age = doctorObject.get("age").getAsInt();
		String D_docNIC = doctorObject.get("docNIC").getAsString();
		String D_docEmail = doctorObject.get("docEmail").getAsString();
		String D_password = doctorObject.get("passwod").getAsString();
		int D_phonenumber = doctorObject.get("phoneNumber").getAsInt();

		String output = DocObj.updateDoctor(D_fname, D_lname, D_gender, D_age, D_docNIC, D_docEmail, D_password,D_phonenumber);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDoctor(String doctorData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(doctorData, "", Parser.xmlParser());

		// Read the value from the element <docId>
		String docId = doc.select("docId").text();
		String output = DocObj.deleteDoctor(docId);
		return output;
	}

}
