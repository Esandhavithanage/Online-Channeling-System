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

	//Doctor/Doctors GET method
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return DocObj.readDoctor();
	}

	//Doctor/Doctors POST method
	
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
		
		//System.out.println(D_fname + " " + D_lname + " " + D_gender + " " + D_age + " " + D_docNIC + " " + D_docEmail
				//+ " " + D_password + " " + D_phonenumber);
		
		String output = DocObj.AddDoctor(D_fname, D_lname, D_gender, D_age, D_docNIC, D_docEmail, D_password,D_phonenumber);
		return output;
	}

	//Doctor/Doctors PUT method
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(String doctorData) {
		// Convert the input string to a JSON object
		JsonObject doctorObject = new JsonParser().parse(doctorData).getAsJsonObject();
		// Read the values from the JSON object
		String D_Id = doctorObject.get("docId").getAsString();
		String D_fname = doctorObject.get("fName").getAsString();
		String D_lname = doctorObject.get("lNmae").getAsString();
		String D_gender = doctorObject.get("gender").getAsString();
		String D_age = doctorObject.get("age").getAsString();
		String D_docNIC = doctorObject.get("docNIC").getAsString();
		String D_docEmail = doctorObject.get("docEmail").getAsString();
		String D_password = doctorObject.get("passwod").getAsString();
		String D_phoneNumber = doctorObject.get("phoneNumber").getAsString();

		String output = DocObj.updateDoctor(D_Id, D_fname, D_lname, D_gender, D_age, D_docNIC, D_docEmail, D_password, D_phoneNumber);
		return output;
	}

	//Doctor/Doctors DELETE method
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDoctor(String doctorData) {
		
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(doctorData, "", Parser.xmlParser());

		// Read the value from the element <docId>
		String D_Id = doc.select("docId").text();
		String output = DocObj.deleteDoctor(D_Id);
		return output;
	}
	
	
	//Doctor/Doctors/doctortimetable GET method
	@GET
	@Path("/doctortimetable")
	@Produces(MediaType.TEXT_HTML)
	public String viewdocTimeDate() {
		return DocObj.viewdocTimeDate();
	}	
	
	//Doctor/Doctors/doctortimetable POST method
	
	@POST
	@Path("/doctortimetable")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String AddDocTimeDate(
			@FormParam("docId") int D_Id,
			@FormParam("hospitalId") int H_Id,
			@FormParam("avaliableDate") String H_avaDate, 
			@FormParam("avaliableTime") String H_avatime){
		
		System.out.println(D_Id + " " + H_Id + " " + H_avaDate + " " + H_avatime);
		
		String output = DocObj.AddDocTimeDate(D_Id, H_Id, H_avaDate, H_avatime);
		return output;
	}
	
	//Doctor/Doctors/doctortimetable PUT method
	
	@PUT
	@Path("/doctortimetable")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatedocTimeDate(String doctorData) {
		// Convert the input string to a JSON object
		JsonObject doctorObject = new JsonParser().parse(doctorData).getAsJsonObject();
		// Read the values from the JSON object
		String D_Id = doctorObject.get("docId").getAsString();
		String H_Id = doctorObject.get("hospitalId").getAsString();
		String H_avaDate = doctorObject.get("avaliableDate").getAsString();
		String H_avatime = doctorObject.get("avaliableTime").getAsString();
	

		String output = DocObj.updatedocTimeDate(D_Id, H_Id, H_avaDate, H_avatime);
		return output;
	}
	

	

}
