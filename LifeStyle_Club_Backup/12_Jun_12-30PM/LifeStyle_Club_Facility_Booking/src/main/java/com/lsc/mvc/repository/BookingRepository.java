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
	
	@Query("SELECT b FROM Booking b WHERE lower(b.bookingNumber)=lower(:bNum)")
	Booking getBookingByBookingNumber(@Param("bNum") String bNum);
	
	@Query("SELECT b FROM Booking b WHERE lower(b.facilityNumber)=lower(:fNum)")
	ArrayList<Booking> getBookingListByFacilityNumber(@Param("fNum") String fNum);
	
	@Query("SELECT b FROM Booking b WHERE lower(b.facilityNumber)=lower(:fNum) AND b.slotDate=:d")
	ArrayList<Booking> getBookingListByFacilityNumberAndDate(@Param("fNum") String fNum, @Param("d") LocalDate d);
	
	@Query("SELECT b FROM Booking b WHERE lower(b.userNumber)=lower(:uNum)")
	ArrayList<Booking> getBookingListByUserNum(@Param("uNum") String uNum);
	
	@Query("SELECT b FROM Booking b WHERE lower(b.userNumber)=lower(:uNum) AND b.slotDate>=:dStart AND b.slotDate<=:dEnd")
	ArrayList<Booking> getBookingListByUserNumAndDate(@Param("uNum") String uNum, @Param("dStart") LocalDate dStart, @Param("dEnd") LocalDate dEnd);
}
