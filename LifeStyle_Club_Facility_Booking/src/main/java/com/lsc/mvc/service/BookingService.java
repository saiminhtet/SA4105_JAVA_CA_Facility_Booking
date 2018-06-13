package com.lsc.mvc.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.lsc.mvc.exception.BookingNotFound;
import com.lsc.mvc.exception.FacilityNotFound;
import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.javabeans.FacilityUsage;
import com.lsc.mvc.model.Booking;
import com.lsc.mvc.model.Facility;

public interface BookingService {

	Booking setNewBookingNum(Booking b) throws BookingNotFound;
	
	Booking addBooking(Booking b) throws BookingNotFound;
	
	Booking getBooking(String bNum) throws BookingNotFound;

	Booking updateBooking(Booking b) throws BookingNotFound;

	Void removeBooking(String bNum) throws BookingNotFound;
	
	ArrayList<Booking> getBookingListSelected(ArrayList<Facility> facilityListSelected) throws ResourceDefinitionInvalid;
	
	ArrayList<Booking> getBookingListSingle(ArrayList<Booking> bookingListSelected, String fNum) throws ResourceDefinitionInvalid, FacilityNotFound;
	
	ArrayList<Booking> getBookingListByUserNumAndDate(String uNum, LocalDate dateStart, LocalDate dateEnd) throws UserNotFound;
	
	ArrayList<Booking> getBookingListByUserNum(String uNum) throws UserNotFound;
	
	List<String> getAvailableSlots(LocalDate slotDate, String fNum) throws FacilityNotFound;
	
	FacilityUsage getFacilityUsageByFNumAndDate(String fNum, LocalDate dStart, LocalDate dEnd) throws FacilityNotFound, BookingNotFound;
	
	List<FacilityUsage> getFacilityUsageListByFacilityListAndDate(List<Facility> fList, LocalDate dStart, LocalDate dEnd) throws FacilityNotFound, BookingNotFound;

}
