package com.parking.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.DTOs.AddParkingDto;
import com.parking.DTOs.DtoEntityConvertor;
import com.parking.DTOs.SlotBookingDto;
import com.parking.Entities.Booking;
import com.parking.Entities.Parking;
import com.parking.repository.BookingRepository;

@Service
@Transactional
public class StaffService {
	
	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	private DtoEntityConvertor conversion;

	public List<SlotBookingDto> getSlotByParkingId(Long id) {
		List<Booking> persisted=bookingRepo.getListofslot(id);
		
		return persisted.stream().map(booking -> conversion.fromBookingEntity(booking)).collect(Collectors.toList());

	}
	
	

}
