package com.lsc.mvc.repository;

import com.lsc.mvc.model.Booking;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	@Query("SELECT max(bookingId) FROM Booking b")
	Integer getBookingIdMax();
	
	@Query("SELECT b FROM Booking b WHERE b.bookingNumber=:bNum")
	Booking getBookingByBookingNumber(@Param("bNum") String bNum);
	
	@Query("SELECT b FROM Booking b WHERE b.facilityNumber=:fNum")
	ArrayList<Booking> getBookingListByFacilityNumber(@Param("fNum") String fNum);
	
	@Query("SELECT b FROM Booking b WHERE b.userNumber=:uNum")
	ArrayList<Booking> getBookingListByUserNum(@Param("uNum") String uNum);
	
	@Query("SELECT b FROM Booking b WHERE b.userNumber=:uNum AND b.slotDate>=:dStart AND b.slotDate<=:dEnd")
	ArrayList<Booking> getBookingListByUserNumAndDate(@Param("uNum") String uNum, @Param("dStart") LocalDate dStart, @Param("dEnd") LocalDate dEnd);
}
