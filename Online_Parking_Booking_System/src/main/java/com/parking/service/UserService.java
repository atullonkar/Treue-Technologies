package com.parking.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.DTOs.DtoEntityConvertor;
import com.parking.DTOs.SlotBookingDto;
import com.parking.DTOs.UserDto;
import com.parking.DTOs.UserSignInDto;
import com.parking.DTOs.UserSignUpDto;
import com.parking.Entities.Booking;
import com.parking.Entities.Parking;
import com.parking.Entities.Payment;
import com.parking.Entities.User;
import com.parking.Entities.Vehicle;
import com.parking.repository.BookingRepository;
import com.parking.repository.ParkingRepository;
import com.parking.repository.UserRepository;
import com.parking.repository.VehicleRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private DtoEntityConvertor conversion;
	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	private VehicleRepository vehicleRepo;
	@Autowired
	private ParkingRepository parkrepo;

	public UserDto authenticateUser(String email, String password) {
		User authenticatedUser = userRepo.findByEmailAndPassword(email, password);
		UserDto convertedUserDto = conversion.toUserDto(authenticatedUser);
		return convertedUserDto;
	}

	public Map<String, Object> UserSignUpService(UserSignUpDto signUp) {
		User persistUser = conversion.toUserEntity(signUp);
		User user = userRepo.save(persistUser);
		return Collections.singletonMap("peristed", user);
	}
//exit_time,duration,slot_number,entry_time,park_id,user_id,v_id
	public String generatedBooking(int slotno, SlotBookingDto addbooking) {
		int result=bookingRepo.addBookingToEntity(addbooking.getExitTime(),addbooking.getParikingDuration(),slotno, addbooking.getStartTime(),addbooking.getPid(),addbooking.getUserId(),addbooking.getVehicleId());
	System.out.println(result);
		return "done";
		
	}

	public String generatedBookings(int slotno, SlotBookingDto addbooking) {
		
		
		if(userRepo.existsById(addbooking.getUserId()))
		{   User newUser=userRepo.findById(addbooking.getUserId()).orElse(null);
		   	
		Booking booking=conversion.toBookingEntity(addbooking);
		booking.setUser(new User (newUser.getUserId()));
		//booking.setUser(newUser);
		Parking newParking=parkrepo.findById(addbooking.getPid()).orElse(null);
		booking.setParking(new Parking(newParking.getParkingId()));
		
		Vehicle newVeh=vehicleRepo.findById(addbooking.getVehicleId()).orElse(null); 
        booking.setVehicle(new Vehicle(newVeh.getVehicleId()));
		System.out.println(booking);
		booking.setSlotNo(slotno);
		LocalDateTime entry =addbooking.getStartTime();
		LocalDateTime exit =addbooking.getExitTime();
		Duration diff = Duration.between(entry, exit);
		int hrs = (int) (Math.ceil(diff.getSeconds())/3600);
		booking.setParikingDuration(hrs);
		Booking persisted=bookingRepo.save(booking);
		
//	Booking persisted=bookingRepo.bookingPresist(slotno,newUser.getUserId(),newParking.getParkingId(),newVeh.getVehicleId());
		return "success";
		}
		else
		return "fail"  ;
	
		
		/* Booking booking = conversion.toBookingEntity(addbooking);
        booking.setVehicle();
        booking.setParking(new Parking(parker.getParkingId()));
        booking.setUser(new User(user.getUserId()) );
		booking.setSlotNo(slotno);
		LocalDateTime entry =addbooking.getStartTime();
		LocalDateTime exit =addbooking.getExitTime();
		Duration diff = Duration.between(entry, exit);
		int hrs = (int) (Math.ceil(diff.getSeconds())/3600);
		booking.setParikingDuration(hrs);
		Booking savedbooking = bookingRepo.save(booking);
		if (savedbooking != null) {
			return "Booking added succesfully!!!!";
		}
		else 
			return "something went wrong!!!";
		}
*/
	
	}

	public SlotBookingDto getbookingById(Long useid) {
	Booking book=bookingRepo.getbookbyid(useid);
	SlotBookingDto book1 =conversion.fromBookingEntity(book);
 		return book1;
 		
	}	
	
	
	
	
	
	
	
	

}
