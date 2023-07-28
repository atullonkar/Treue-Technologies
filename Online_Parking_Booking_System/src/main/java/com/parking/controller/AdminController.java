package com.parking.controller;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.DTOs.AddParkingDto;
import com.parking.DTOs.DateRangeDto;
import com.parking.DTOs.FareCardDto;
import com.parking.DTOs.Response;
import com.parking.DTOs.UserDto;
import com.parking.service.AdminService;


@CrossOrigin ("*")
@RestController
@RequestMapping("/parkifyAdmin")

public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@GetMapping("/getUserByRole/{role}")
	
	public ResponseEntity<?> getAllUsers(@PathVariable ("role") String role) {
		List<UserDto> transientUser=service.getAllUserList(role);
		return Response.success(transientUser);
	}
	@PostMapping("/getCollection")
	public ResponseEntity<?> getTotalCollection (@DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")@RequestBody DateRangeDto date){
		
		double collection=service.totalCollectionByDate(date.getInitialDate(),date.getEndDate());
		return Response.success(collection);
	}
	
	@PatchMapping("/updateTwowheelerFare/{fare}")
	public ResponseEntity<?> updateFareForTwowheeler (@PathVariable ("fare") double fare){
		String update = service.updatedfareofTwoWheeler(fare);
		return Response.success(update);
	}
	
	@PatchMapping("/updateFourwheelerFare/{fare}")
	public ResponseEntity<?> updateFareForFourwheeler (@PathVariable ("fare")double fare){
		String update = service.updatedfareofFourWheeler(fare);

		return Response.success(update);
	}
	
	@PostMapping ("/addNewParkingArea")
	 public ResponseEntity<?> addNewParking (@RequestBody AddParkingDto parking){
		String insertParrking= service.addParking(parking);
		
			return Response.success(insertParrking);
	 }
	
	@GetMapping("/getFareDetails")
	public ResponseEntity<?> getFareDetails (){
		List<FareCardDto> fdto= service.getFareDetails ();
		return Response.success(fdto);
	}
	
	@GetMapping("/getAllParkingAreaList")
	public ResponseEntity<?> getAllParkingArea (){
		List<AddParkingDto> get= service.getAllParkingList ();
		return Response.success(get);
	}
	
}
