package com.parking.DTOs;

import java.util.List;

import org.springframework.stereotype.Component;

import com.parking.Entities.Booking;
import com.parking.Entities.FareCard;
import com.parking.Entities.Parking;
import com.parking.Entities.Payment;
import com.parking.Entities.User;
import com.parking.Entities.Vehicle;

@Component
public class DtoEntityConvertor {

	


	// ============entity to dto conversion==========
	// for SignInDTO
	public UserDto toUserDto(User user) {
		UserDto dto = new UserDto();
		dto.setUserId(user.getUserId());
		dto.setFirstName(user.getFirstName());
		dto.setEmail(user.getEmail());
		dto.setLastName(user.getLastName());
		dto.setMobileNo(user.getMobileNo());
		dto.setUserRole(user.getUserRole());

		return dto;

	}

	// ===========DTO to Entity==========
	// for SignUp
	public User toUserEntity(UserSignUpDto signUpDto) {
		User entity = new User();
		entity.setUserId(signUpDto.getUserId());
		entity.setFirstName(signUpDto.getFirstName());
		entity.setEmail(signUpDto.getEmail());
		entity.setLastName(signUpDto.getLastName());
		entity.setMobileNo(signUpDto.getMobileNo());
		entity.setUserRole(signUpDto.getUserRole());
		entity.setPassword(signUpDto.getPassword());
		return entity;

	}
	
	// =========Entity to DTO of GetAllUser===========
	
	public UserSignUpDto toUserSignupDto(User user) {
		UserSignUpDto dto = new UserSignUpDto();
		dto.setUserId(user.getUserId());
		dto.setFirstName(user.getFirstName());
		dto.setEmail(user.getEmail());
		dto.setLastName(user.getLastName());
		dto.setMobileNo(user.getMobileNo());
		dto.setUserRole(user.getUserRole());
		return dto;

	}
	
	// ===========DTO to Entity==========
	//for farecard
	
	public FareCard toFareCard (FareCardDto farecardDto) {
		FareCard entity = new FareCard();
		entity.setFare(farecardDto.getFare());
		entity.setItemId(farecardDto.getItemId());
		entity.setVehicleType(farecardDto.getVehicleType());
		return entity;
		
	}
	
	//==================Entity to DTO====================
	// for farecard dto
	
	public FareCardDto toFareCardDto (FareCard farecard) {
		FareCardDto dto = new FareCardDto();
		dto.setFare(farecard.getFare());
		dto.setItemId(farecard.getItemId());
		dto.setVehicleType(farecard.getVehicleType());
		return dto;
		
	}
	// ===========DTO to Entity==========
	//for add new parking
	public Parking toParking (AddParkingDto park) {
		Parking entity = new Parking();
		entity.setParkingId(park.getParkingId());
		entity.setParkingId(park.getParkingId());
		entity.setCity(park.getCity());
		entity.setLocation(park.getLocation());
		entity.setSlots(park.getSlots());
		entity.setParkingName(park.getParkingName());
		return entity;
	}
	
	//==================Entity to DTO====================
	// for add new parking
	public AddParkingDto toParkingDto (Parking park) {
		AddParkingDto dto = new AddParkingDto();
		dto.setParkingId(park.getParkingId());
		dto.setCity(park.getCity());
		dto.setLocation(park.getLocation());
		dto.setSlots(park.getSlots());
		dto.setParkingName(park.getParkingName());
		return dto;
	}
	//=======================================================================================
	
	
	// Now onwards booking DTO will get convert into various entities
	//BOOKING DTO => BOOKING ENTITY
	public Booking toBookingEntity (SlotBookingDto slotbook) {
		Booking entity = new Booking();
		entity.setBookingId(slotbook.getBookingId());
	   //entity.setParking(new Parking(slotbook.getPid()));
	   entity.setSlotNo(slotbook.getSlots());
	   entity.setStartTime(slotbook.getStartTime());
	   entity.setExitTime(slotbook.getExitTime());
	   entity.setParikingDuration(slotbook.getParikingDuration());
	  // entity.setUser(new User(slotbook.getUserId()));
	   //entity.setVehicle (new Vehicle (slotbook.getVehicleId()));
		return entity;
	}
	
	//BOOKING DTO => VEHICLE ENTITY
	public Vehicle fromBookingDtoToVehicleEntity (SlotBookingDto slotbook) {
		Vehicle entity = new Vehicle ();
		entity.setUser(new User (slotbook.getUserId()));
		entity.setVehicleId(slotbook.getVehicleId());
		entity.setVehicleNo(slotbook.getVehicleNo());
		entity.setVehicleType(slotbook.getVehicleType());
		
		return entity;
	}
	
	
	// entity to dto converter
	
	public PaymentDto fromPayment(Payment pay)
	{
		PaymentDto dto=new PaymentDto();
		dto.setAmount(pay.getAmount());
		dto.setBookingid(pay.getBooking().getBookingId());
		dto.setPaymentDate(pay.getPaymentDate());
		return dto;
		
	}
	
	public SlotBookingDto fromBookingEntity(Booking book)
	{
		SlotBookingDto entity=new SlotBookingDto();
		entity.setBookingId(book.getBookingId());
		entity.setExitTime(book.getExitTime());
		entity.setStartTime(book.getStartTime());
		//entity.setUser(book.getUser().getUserId());
		//entity.setSlotNo(book.getSlotNo());
		entity.setPid(book.getParking().getParkingId());
		entity.setSlots(book.getSlotNo());
		entity.setUserId(book.getUser().getUserId());
		return entity; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
