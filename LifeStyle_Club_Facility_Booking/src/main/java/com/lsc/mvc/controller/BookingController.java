package com.lsc.mvc.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lsc.mvc.exception.BookingNotFound;
import com.lsc.mvc.exception.FacilityNotFound;
import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.model.Booking;
import com.lsc.mvc.model.Facility;
import com.lsc.mvc.model.User;
import com.lsc.mvc.service.BookingService;
import com.lsc.mvc.service.FacilityService;

@Controller
@RequestMapping("/booking")

public class BookingController {

	@Autowired
	private BookingService bservice;

	@Autowired
	private FacilityService fservice;

	@GetMapping("/managebooking")
	public String managebooking(ModelMap model) throws FacilityNotFound, ResourceDefinitionInvalid {

		Booking booking = new Booking();
		model.put("facilityname", fservice.getFacilityListByType("Meeting Room"));
		model.put("slotstarttime", bservice.getAvailableSlots(LocalDate.of(2018, 6, 07), "F001"));
		model.put("booking", booking);
		return "booking/manage_booking";

	}

	@PostMapping("/managebooking")
	public String updateBooking(HttpServletRequest req, HttpServletResponse res, Booking booking)
			throws ResourceDefinitionInvalid, BookingNotFound {

		String bookingNumber = "B000001";

		Booking existingbooking = new Booking();

		existingbooking = bservice.getBooking(bookingNumber);
		existingbooking.setFacilityNumber(booking.getFacilityNumber());
		existingbooking.setSlotDate(booking.getSlotDate());
		existingbooking.setSlotTimeStart(booking.getSlotTimeStart());
		existingbooking.setSlotTimeEnd(booking.getSlotTimeEnd());

		bservice.updateBooking(existingbooking);
		return "booking/manage_booking";
	}

	@GetMapping("/bookfacility")
	public String Bookingfacility(ModelMap model) throws FacilityNotFound {

		Booking booking = new Booking();
		model.put("facilityname", fservice.getFacilityListByType("Meeting Room"));// Retrieve type list to populate
																					// dropdownlist in addfacility form
		model.put("bookingslot", bservice.getAvailableSlots(LocalDate.of(2018, 6, 07), "F001"));
		model.put("booking", booking);

		return "booking/book_facility";
	}

	@PostMapping("/bookfacility")
	public String addNewFacility(HttpServletRequest req, Booking booking) throws FacilityNotFound, BookingNotFound {// throws																					// ResourceDefinitionInvalid,
																													// FacilityNotFound{
		booking.setUserNumber("M0056");
		booking.setSlotTimeEnd("1100");
		//booking.setTransDateTime(LocalDateTime.now());
		bservice.setNewBookingNum(booking);		
		bservice.addBooking(booking);
		System.out.println(booking);
		return "booking/book_facility"; // default redirection to retry
	}


	@GetMapping("/bookingsummary")
	public String Bookingsummary(ModelMap model) throws FacilityNotFound, BookingNotFound {
		
		Booking bookingsummary = new Booking();
		String bookingNumber = "B000001";
		bookingsummary = bservice.getBooking(bookingNumber);
		model.put("booking", bookingsummary);
		
		return "booking/book_summary";
	}
}
