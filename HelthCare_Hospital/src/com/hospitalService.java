package com;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.hospital;
import model.hospitalDeo;

@Path("/hospital")
public class hospitalService {

	@POST
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addNewHospital(String hospitalData) {
		System.out.println(hospitalData);
		JsonObject obj = new JsonParser().parse(hospitalData).getAsJsonObject();
		
		hospitalDeo deo = new hospitalDeo();//jsonObject.get("DocId").getAsInt()
		deo.setHospitalName(obj.get("name").getAsString());;
		deo.setHospitalAddress(obj.get("address").getAsString());;
		deo.setHospitalEmail(obj.get("email").getAsString());;
		deo.setPhone(obj.get("phone").getAsString());
		deo.setPassword(obj.get("password").getAsString());
		
		hospital hospital = new hospital();
		
		return hospital.addnewhospital(deo);
	}
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<hospitalDeo> getHospital() {
		hospital hospital = new hospital();
		return hospital.getHospitalDetails();
	}
	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateHospital(String hospitalData) {
		
		JsonObject obj = new JsonParser().parse(hospitalData).getAsJsonObject();
		
		hospitalDeo deo = new hospitalDeo();
		deo.setHospitalName(obj.get("name").getAsString());;
		deo.setHospitalAddress(obj.get("address").getAsString());;
		deo.setHospitalEmail(obj.get("email").getAsString());;
		deo.setPhone(obj.get("phone").getAsString());
		deo.setPassword(obj.get("password").getAsString());
		deo.setHospitalId(obj.get("hospitalId").getAsInt());
		
		hospital hospital = new hospital();
		
		return hospital.updateHospitalDetails(deo);
	}
	@DELETE
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteHospital(String hospitalData) {
	
		JsonObject obj = new JsonParser().parse(hospitalData).getAsJsonObject();
		
		hospitalDeo deo = new hospitalDeo();
		deo.setHospitalId(obj.get("hospitalId").getAsInt());
		hospital hospital = new hospital();
		return hospital.deletehospital(deo);
	}
}
