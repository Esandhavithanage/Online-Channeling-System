package com;

import model.Laboratory;

import java.sql.Date;

//For REST Service 
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Labs")
public class LaboratoryService {
	Laboratory LabObj = new Laboratory();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String  ViewLabDetails() {
		return LabObj.ViewLabDetails();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayments(@FormParam("type") String Lab_Type, @FormParam("LabDate") Date Lab_date ,
			@FormParam("Desacription") String Des, @FormParam("patientId") String PatientId,@FormParam("hospitalId") String HospitalId){
		String output = LabObj.AddLabDetails(Lab_Type, Lab_date, Des, PatientId,HospitalId);
		return output;
	}
}