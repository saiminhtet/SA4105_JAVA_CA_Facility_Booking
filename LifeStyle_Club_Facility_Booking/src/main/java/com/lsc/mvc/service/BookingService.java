package com.lsc.mvc.service;

import java.time.LocalDate;
import java.util.ArrayList;

import com.lsc.mvc.model.Booking;
import com.lsc.mvc.model.Facility;

public interface BookingService {

	Booking setNewBookingNum(Booking b);
	
	Booking addBooking(Booking b);
	
	Booking getBooking(String bNum);

	Booking updateBooking(Booking b);

	Void removeBooking(String bNum);
	
	ArrayList<Booking> getBookingListSelected(ArrayList<Facility> facilityListSelected);
	
	ArrayList<Booking> getBookingListSingle(ArrayList<Booking> bookingListSelected, String fNum);
	
	ArrayList<Booking> getBookingListByUserNumAndDate(String uNum, LocalDate dateStart, LocalDate dateEnd);
	
	ArrayList<Booking> getBookingListByUserNum(String uNum);
}
