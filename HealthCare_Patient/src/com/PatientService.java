package com;

import model.Patient;

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

@Path("/Patients")
public class PatientService {
	Patient patientObj = new Patient();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return patientObj.readPatient();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String AddPatient(
			@FormParam("fNmae") String P_fname, 
			@FormParam("IName") String P_lname,
			@FormParam("gender") String P_gender, 
			@FormParam("age") int P_age,
			@FormParam("patientNIC") String P_patientNIC, 
			@FormParam("address") String P_address,
			@FormParam("patientEmail") String P_email, 
			@FormParam("passwod") String P_password,
			@FormParam("phoneNumber") int P_phoneNo) {
		
		System.out.println(P_fname + " " + P_lname + " " + P_gender + " " + P_age + " " + P_patientNIC + " " + P_address + " " + P_email + " " + P_password + " " + P_phoneNo);
		
		String output = patientObj.AddPatient(P_fname, P_lname, P_gender, P_age, P_patientNIC, P_address, P_email,P_password, P_phoneNo);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient(String PatientData) {
		
		// Convert the input string to a JSON object
		JsonObject PatientObject = new JsonParser().parse(PatientData).getAsJsonObject();
		// Read the values from the JSON object
		
		String P_Id = PatientObject.get("patientId").getAsString();
		String P_fname = PatientObject.get("fNmae").getAsString();
		String P_lname = PatientObject.get("lName").getAsString();
		
		
		System.out.println(P_Id +' '+ P_fname +' '+ P_lname);

		String output = patientObj.updatePatient(P_Id, P_fname, P_lname);
		return output;
	}
	
	

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String PatientData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(PatientData, "", Parser.xmlParser());

		// Read the value from the element <docId>
		String patientId = doc.select("patientId").text();
		String output = patientObj.deletePatient(patientId);
		return output;
	}

}
