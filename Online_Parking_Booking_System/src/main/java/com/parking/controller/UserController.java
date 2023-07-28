 package com.parking.controller;

import java.time.Duration;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.DTOs.Response;
import com.parking.DTOs.UserDto;
import com.parking.DTOs.UserSignInDto;
import com.parking.DTOs.UserSignUpDto;
import com.parking.Entities.Booking;
import com.parking.DTOs.SlotBookingDto;
import com.parking.service.UserService;
@CrossOrigin ("*")
@RestController
@RequestMapping("/parkifyUserFunctions")
public class UserController {
	@Autowired
	private UserService service;
@PostMapping("/signIn")	
public ResponseEntity<?> signIn(@RequestBody UserSignInDto signinDto )
{
            UserDto result=service.authenticateUser(signinDto.getEmail(), signinDto.getPassword());	
	return Response.success(result);
	}
@PostMapping("/signUp")
public ResponseEntity<?> signUpUser(@RequestBody UserSignUpDto signUp){
                     Map<String,Object> mappedResponse= service.UserSignUpService(signUp);
	return Response.success(mappedResponse);	
}
@PostMapping(value="/addBookingBySlot/{slot}")
	public ResponseEntity<?> addBooking (@PathVariable ("slot") int slotno ,@DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")@RequestBody SlotBookingDto addbooking ){
	LocalDateTime entry =addbooking.getStartTime();
	LocalDateTime exit =addbooking.getExitTime();
	Duration diff = Duration.between(entry, exit);
	int hrs = (int) (Math.ceil(diff.getSeconds())/3600);
	addbooking.setParikingDuration(hrs);
	String persistedbooking=service.generatedBooking(slotno,addbooking);
	
	//	String persistedbooking=service.generatedBookings(slotno, addbooking);
		return Response.success(persistedbooking);
	}

@GetMapping(value="/getBooking/{userId}")
public ResponseEntity<?> showBooing(@PathVariable ("userId") Long useid )
{
	SlotBookingDto dto=service.getbookingById(useid);
	return Response.success(dto);
}

}
