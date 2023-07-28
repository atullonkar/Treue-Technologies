package com.parking.controller;

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

import com.parking.DTOs.PaymentDto;
import com.parking.DTOs.Response;
import com.parking.Entities.Payment;
import com.parking.service.ParkingService;
@CrossOrigin ("*")
@RestController
@RequestMapping("/ParkifyPaymentManager")
public class PaymentController {
	@Autowired
	private ParkingService parkService;
	
	@PostMapping(value="/getPayment/{userid}")
	public ResponseEntity<?> generatePayment(@PathVariable ("userid") Long user ,@DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss") @RequestBody PaymentDto pay)
	{
	    String payment = parkService.fetchPayment(user,pay);	
		return Response.success(payment);
	}

}
