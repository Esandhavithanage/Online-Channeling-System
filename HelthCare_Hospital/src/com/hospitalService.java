package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
}
