package com.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.DTOs.AddParkingDto;
import com.parking.DTOs.Response;
import com.parking.DTOs.SlotBookingDto;
import com.parking.repository.BookingRepository;
import com.parking.service.StaffService;

@RestController
@RequestMapping("/parkifyStaffManagement")
@CrossOrigin ("*")
public class StaffController {
	
	@Autowired
	private StaffService service;
	
	
	@GetMapping(value="/getBookedSlots/{parkId}")
	public ResponseEntity<?> getBookedSlotsFromParking(@PathVariable ("parkId") Long id){
		
	       List<SlotBookingDto> parking=service.getSlotByParkingId(id);
		
	 	return Response.success(parking);
	}
	
//	@PostMapping(value="/bookSlotByStaff/{slotno}")
//  public ResponseEntity<?> staffBookingBySlotNo(@PathVariable("slotno")int slot )
//  {
//		return null;
//  }
}
