package com.parking.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.parking.DTOs.PaymentDto;
import com.parking.Entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	// List<Payment> findByPaymentDate(LocalDateTime paymentdate);
//	//SELECT CAST(booking_date AS DATE), COUNT(*) as Number_of_Booking,
//    SUM(CAST(booking_date AS DATE) = CAST(checkin_date AS DATE)) as count_with_same_date,
//    SUM(booking_value) as booking_value
//FROM t
//WHERE booking_date >= '2016-04-01' AND
//   booking_date < '2016-05-01'
//GROUP BY CAST(booking_date AS DATE);
	@Query(value = "select sum(amount)as Amount from payments where payment_Date>=? and payment_Date<=?", nativeQuery = true)
	double findByPaymentDate(LocalDateTime T1, LocalDateTime T2);

	@Modifying
	@Query(value = "insert into payments (amount,payment_date,booking_id) value (?,?,?)", nativeQuery = true)
	int persistPaymentRecord(double amount, LocalDateTime paymentDate, long bookingid);

	@Query(value = "select * from payments where booking_id=?", nativeQuery = true)
	Payment getPaymentDetailsWithBookingId(long bookingid);

	// (select userId from usertbl where userid=?)
	// (select fare from fare_card where vehicle_id=?)
	// (select duration from booking where userId=? and vehicle )

}
