package com;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.appointment;
import model.appointmentPOJO;

@Path("/appointment")
public class appointmentService {
	
	@POST
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addNewAppointment(String appointmentData) {
		System.out.println(appointmentData);
		JsonObject jsonObject = new JsonParser().parse(appointmentData).getAsJsonObject();
		
		appointmentPOJO appointmentPOJO = new appointmentPOJO();
		appointmentPOJO.setDocId(jsonObject.get("DocId").getAsInt());
		appointmentPOJO.setHospitalId(jsonObject.get("HospitalId").getAsInt());
		appointmentPOJO.setPatientId(jsonObject.get("PatientId").getAsInt());
		appointmentPOJO.setPaymentId(jsonObject.get("PaymentId").getAsInt());
		appointmentPOJO.setAppointmentDate(jsonObject.get("AppointmentDate").getAsString());
		
		appointment appointment = new appointment();
		
		return appointment.addNewAppointment(appointmentPOJO);
	}
	
	@POST
	@Path("/bydoctor")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<appointmentPOJO> getAppointmentbydoctor(String appointmentData) {
		System.out.println(appointmentData);
		JsonObject jsonObject = new JsonParser().parse(appointmentData).getAsJsonObject();
	
		String date = jsonObject.get("date").getAsString();
		int did = jsonObject.get("DocId").getAsInt();
		appointment appointment = new appointment();
		
		
		return appointment.getApointmentBydocAndDate(did, date);
	}
	
	@POST
	@Path("/bypatient")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<appointmentPOJO> getAppointmentbypatient(String appointmentData) {
		System.out.println(appointmentData);
		JsonObject jsonObject = new JsonParser().parse(appointmentData).getAsJsonObject();
	
		String date = jsonObject.get("date").getAsString();
		int pid = jsonObject.get("patientId").getAsInt();
		appointment appointment = new appointment();
		
		
		return appointment.getApointmentBypatientAndDate(pid, date);
	}
	
	@POST
	@Path("/byhospital")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<appointmentPOJO> getAppointmentbyhospital(String appointmentData) {
		System.out.println(appointmentData);
		JsonObject jsonObject = new JsonParser().parse(appointmentData).getAsJsonObject();
	
		String date = jsonObject.get("date").getAsString();
		int hid = jsonObject.get("hospitalId").getAsInt();
		appointment appointment = new appointment();
		
		
		return appointment.getApointmentByhospitalAndDate(hid, date);
	}
	
	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateAppointment(String appointmentData) {
		System.out.println(appointmentData);
		JsonObject jsonObject = new JsonParser().parse(appointmentData).getAsJsonObject();
		
		appointmentPOJO appointmentPOJO = new appointmentPOJO();
		appointmentPOJO.setDocId(jsonObject.get("DocId").getAsInt());
		appointmentPOJO.setAppointmentID(jsonObject.get("appointmentID").getAsInt());
		appointmentPOJO.setAppointmentDate(jsonObject.get("AppointmentDate").getAsString());
		
		appointment appointment = new appointment();
		
		return appointment.updateappoitment(appointmentPOJO);
		
	}
	
	@DELETE
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAppointment(String appointmentData) {
		System.out.println(appointmentData);
		JsonObject jsonObject = new JsonParser().parse(appointmentData).getAsJsonObject();
		
		appointmentPOJO appointmentPOJO = new appointmentPOJO();
		appointmentPOJO.setAppointmentID(jsonObject.get("appointmentID").getAsInt());
		appointment appointment = new appointment();
		return appointment.deleteappoitment(appointmentPOJO);
	}
	

}
