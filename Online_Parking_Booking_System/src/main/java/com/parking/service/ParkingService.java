package com.parking.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.DTOs.DtoEntityConvertor;
import com.parking.DTOs.PaymentDto;
import com.parking.Entities.Payment;
import com.parking.repository.BookingRepository;
import com.parking.repository.FarecardRepository;
import com.parking.repository.PaymentRepository;

@Service
@Transactional
public class ParkingService {

	@Autowired
	private PaymentRepository paymentRepo;
	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	private FarecardRepository fareRepo;
	@Autowired
	private DtoEntityConvertor conversion;
	
	
/*	public PaymentDto fetchPayment(Long user,PaymentDto pay) {
		int duration =bookingRepo.getDuration(user,pay.getBookingid()); 
	    double fare=fareRepo.getfareCharge(pay.getVehicletypeId());
	    pay.setAmount(duration*fare);
	    int donpay=paymentRepo.persistPaymentRecord(pay.getAmount(),pay.getPaymentDate(),pay.getBookingid());
	    Payment persistPayment=paymentRepo.getPaymentDetailsWithBookingId(pay.getBookingid());
	    PaymentDto transPayment=conversion.fromPayment(persistPayment);
	    
		return transPayment;
		
		
	}
*/	
	public String fetchPayment(Long user,PaymentDto pay) {
		int duration =bookingRepo.getDuration(user,pay.getBookingid()); 
	    double fare=fareRepo.getfareCharge(pay.getVehicletypeId());
	    pay.setAmount(duration*fare);
	    pay.setPaymentDate(LocalDateTime.now());
	    int donpay=paymentRepo.persistPaymentRecord(pay.getAmount(),pay.getPaymentDate(),pay.getBookingid());
	   // Payment persistPayment=paymentRepo.getPaymentDetailsWithBookingId(pay.getBookingid());
	   // PaymentDto transPayment=conversion.fromPayment(persistPayment);
	    System.out.println(donpay);
	    //send postmant payment dt0
	    	return "success";
	    
		
		
		
	}
	

}
