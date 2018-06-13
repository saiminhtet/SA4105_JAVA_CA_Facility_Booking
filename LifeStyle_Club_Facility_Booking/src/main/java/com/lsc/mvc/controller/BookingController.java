package com.lsc.mvc.controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lsc.mvc.exception.BookingNotFound;
import com.lsc.mvc.exception.FacilityNotFound;
import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.javabeans.AuthenticateUser;
import com.lsc.mvc.model.Booking;
import com.lsc.mvc.model.Facility;
import com.lsc.mvc.model.User;
import com.lsc.mvc.service.BookingService;
import com.lsc.mvc.service.FacilityService;
import com.lsc.mvc.service.UserService;

@Controller
@RequestMapping("/booking")

public class BookingController {

	@Autowired
	private BookingService bService;

	@Autowired
	private FacilityService fService;
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private AuthenticateUser util; 
	
	@GetMapping("search-facility")
	public String getSearchFacility(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateMember(req, model);
		if (authResult.equals("OK")) {
			try {
				model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				model.addAttribute("fTypeList", fService.getFacilityTypes());
				return "booking/search_facility";
			} catch (UserNotFound e) {
				return "user/login";
			}
		}
		else return "user/login";
	}
	
	@PostMapping("search-facility-by-type")
	public String postSearchFacilityByType(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateMember(req, model);
		if (authResult.equals("OK")) {
			// Get Search Term
			String fType = req.getParameter("facilityType");
			
			try {
				// Load Data into Model
				model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				model.addAttribute("fTypeList", fService.getFacilityTypes());
				model.put("fList", fService.getFacilityListByType(fType));
				return "booking/search_facility";
			} catch (UserNotFound e) {
				return "user/login";
			}
		}
		else return "user/login";
	}
	
	@PostMapping("search-facility-by-number")
	public String postSearchFacilityByNumber(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			// Get Search Term
			String fNum = req.getParameter("facilityNumber");
			
			// Load Data into Model
			try {
				model.addAttribute("fTypeList", fService.getFacilityTypes());
				model.put("fList", fService.getFacilityListByNumber(fNum));
				return "booking/search_facility";
			} catch (FacilityNotFound e) {
				return "booking/search_facility";
			}
		}
		else return "user/login";
	}
	
	@PostMapping("search-facility-by-name")
	public String postSearchFacilityByNuame(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			// Get Search Term
			String fName = req.getParameter("facilityName");
			
			// Load Data into Model
			model.addAttribute("fTypeList", fService.getFacilityTypes());
			model.put("fList", fService.getFacilityListByName(fName));
			try {
				model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
			} catch (UserNotFound e) {
				return "user/login";
			}
			return "booking/search_facility";
		}
		else return "user/login";
	}
	
	@RequestMapping(value = "/book_facility/{facilityNum}", method = RequestMethod.GET)
	public String bookFacility(@PathVariable("facilityNum") String facilityNum, HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			Facility f;
			try { f = fService.getFacility(facilityNum);
			} catch (FacilityNotFound e) { return "booking/search_facility"; }
			
			// Load Data into Model
			model.addAttribute("fShortList", fService.getFacilityListByType(f.getFacilityType()));
			return "booking/book_facility";
		}
		else return "user/login";
	}
	
	@PostMapping("search-slots")
	public String postSearchSlots(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			// Get Search Term
			String fNum = req.getParameter("facilityList");
			Facility f;
			try {
				f = fService.getFacility(fNum);
			} catch (FacilityNotFound e0) {
				try {
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				} catch (UserNotFound e1) {
					return "user/login";
				}
				model.addAttribute("fTypeList", fService.getFacilityTypes());
				return "booking/search_facility";
			}
			String tDate = req.getParameter("targetDate");
			LocalDate targetDate = LocalDate.of(Integer.parseInt(tDate.substring(0, 4)), Integer.parseInt(tDate.substring(5, 7)), Integer.parseInt(tDate.substring(8, 10)));
			
			Booking b = new Booking();
	        b.setFacilityNumber(f.getFacilityNumber());
	        b.setUserNumber(util.getUNum(req));
	        b.setSlotDate(targetDate);
	        
	        HttpSession session = req.getSession();
	        session.setAttribute("bCurrent", b);
	        
			// Load Data into Model
			try {
				model.addAttribute("bCurrent", b);
				model.addAttribute("fName", f.getFacilityName());
				model.addAttribute("fShortList", fService.getFacilityListByType(f.getFacilityType()));
				model.addAttribute("slotList", bService.getAvailableSlots(targetDate, fNum));
				return "booking/book_facility";
			} catch (FacilityNotFound e0) {
				try {
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				} catch (UserNotFound e1) {
					return "user/login";
				}
				model.addAttribute("fTypeList", fService.getFacilityTypes());
				return "booking/search_facility";
			}
		}
		else return "user/login";
	}
	
	@PostMapping("booking-summary")
	public String postBookingSummary(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			// Get Slot Date
			String slotStart = req.getParameter("targetSlot");
			String slotTimeStart = slotStart;
			DecimalFormat fmt = new DecimalFormat("0000");
			String slotTimeEnd = fmt.format(Integer.parseInt(slotStart) + 100);
			
			// Get Current Booking
			HttpSession session = req.getSession();
	        Booking b = (Booking) session.getAttribute("bCurrent");
	        b.setSlotTimeEnd(slotTimeEnd);
	        b.setSlotTimeStart(slotTimeStart);
	        b.setTransDateTime(LocalDateTime.now());
	        try {
				bService.setNewBookingNum(b);
			} catch (BookingNotFound e1) {
				try {
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				} catch (UserNotFound e2) {
					return "user/login";
				}
				model.addAttribute("fTypeList", fService.getFacilityTypes());
				return "booking/search_facility";
			}
	        System.out.println(b.toString());
	        session.setAttribute("bCurrent", b);
	        
	        User u = new User();
			try {
				u = uService.getUser(util.getUNum(req));
			} catch (UserNotFound e3) {
				System.out.println(e3.getMessage());
			}
	        String fullName = u.getTitle() + " " + u.getFirstName() + " " + u.getLastName() + ", " + u.getMiddleName();
	        Facility f = new Facility();
			try {
				f = fService.getFacility(b.getFacilityNumber());
			} catch (FacilityNotFound e) {
				System.out.println(e.getMessage());
			}
			
			model.addAttribute("timeSlot", slotTimeStart + " to " + slotTimeEnd);
	        model.addAttribute("fName", f.getFacilityName());
	        model.addAttribute("fullName", fullName);
	        model.addAttribute("booking", b);
	        try {
				bService.addBooking(b);
			} catch (BookingNotFound e1) {
				try {
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				} catch (UserNotFound e2) {
					return "user/login";
				}
				model.addAttribute("fTypeList", fService.getFacilityTypes());
				return "booking/search_facility";
			}
			return "booking/booking_summary";
		}
		else return "user/login";
	}

//	@GetMapping("/managebooking")
//	public String managebooking(ModelMap model) throws FacilityNotFound, ResourceDefinitionInvalid {
//
//		Booking booking = new Booking();
//		model.put("facilityname", fservice.getFacilityListByType("Meeting Room"));
//		model.put("slotstarttime", bservice.getAvailableSlots(LocalDate.of(2018, 6, 07), "F001"));
//		model.put("booking", booking);
//		return "booking/manage_booking";
//
//	}
//
//	@PostMapping("/managebooking")
//	public String updateBooking(HttpServletRequest req, HttpServletResponse res, Booking booking)
//			throws ResourceDefinitionInvalid, BookingNotFound {
//
//		String bookingNumber = "B000001";
//
//		Booking existingbooking = new Booking();
//
//		existingbooking = bservice.getBooking(bookingNumber);
//		existingbooking.setFacilityNumber(booking.getFacilityNumber());
//		existingbooking.setSlotDate(booking.getSlotDate());
//		existingbooking.setSlotTimeStart(booking.getSlotTimeStart());
//		existingbooking.setSlotTimeEnd(booking.getSlotTimeEnd());
//
//		bservice.updateBooking(existingbooking);
//		return "booking/manage_booking";
//	}
//
//	@GetMapping("/bookfacility")
//	public String Bookingfacility(ModelMap model) throws FacilityNotFound {
//
//		Booking booking = new Booking();
//		model.put("facilityname", fservice.getFacilityListByType("Meeting Room"));// Retrieve type list to populate
//																					// dropdownlist in addfacility form
//		model.put("bookingslot", bservice.getAvailableSlots(LocalDate.of(2018, 6, 07), "F001"));
//		model.put("booking", booking);
//
//		return "booking/book_facility";
//	}
//
//	@PostMapping("/bookfacility")
//	public String addNewFacility(HttpServletRequest req, Booking booking) throws FacilityNotFound, BookingNotFound {// throws																					// ResourceDefinitionInvalid,
//																													// FacilityNotFound{
//		booking.setUserNumber("M0056");
//		booking.setSlotTimeEnd("1100");
//		//booking.setTransDateTime(LocalDateTime.now());
//		bservice.setNewBookingNum(booking);		
//		bservice.addBooking(booking);
//		System.out.println(booking);
//		return "booking/book_facility"; // default redirection to retry
//	}
//
//
//	@GetMapping("/bookingsummary")
//	public String Bookingsummary(ModelMap model) throws BookingNotFound{
//		
//		Booking bookingsummary = new Booking();
//		String bookingNumber = "B000001";
//		bookingsummary = bservice.getBooking(bookingNumber);
//		model.put("booking", bookingsummary);
//		
//		return "booking/booking_summary";
//	}
}
