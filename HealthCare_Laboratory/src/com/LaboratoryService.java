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
	
	
	
	@PUT 
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) public String updateLab(String LabData) 
	
	{  
		//Convert the input string to a JSON object  
		JsonObject LabDetailsObject = new JsonParser().parse(LabData).getAsJsonObject(); 
		 
		 //Read the values from the JSON object  
		String Lab_Id = LabDetailsObject.get("labId").getAsString();  
		String Lab_Type = LabDetailsObject.get("type").getAsString();  
		//Date Lab_date = LabDetailsObject.get("LabDate").getAsString();
		String Lab_date = LabDetailsObject.get("LabDate").getAsString();
		String Des = LabDetailsObject.get("Desacription").getAsString();  
		String PatientId = LabDetailsObject.get("patientId").getAsString(); 
		String HospitalId = LabDetailsObject.get("hospitalId").getAsString(); 
		 
		String output = LabObj.Updatelab(Lab_Id, Lab_Type, Lab_date, Des, PatientId,HospitalId); 
		 System.out.println(output);
		 return output; 
		 
	}
	
	
	@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteLabDetails(String LabData) 
	
	{  
		//Convert the input string to an XML document  
		Document doc = Jsoup.parse(LabData, "", Parser.xmlParser());     
		
		//Read the value from the element <Lab_Id>  
		String Lab_Id = doc.select("labId").text(); 
	
		 
		 String output = LabObj.RemoveLab(Lab_Id);
		 
		 return output;
	} 

	
}