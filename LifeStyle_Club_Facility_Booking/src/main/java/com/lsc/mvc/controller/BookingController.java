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
import com.lsc.mvc.service.EmailService;
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
	
	@Autowired
	private EmailService eService;
	
	@GetMapping("search-facility")
	public String getSearchFacility(HttpServletRequest req, ModelMap model) {
		// Authenticate User

		String authResult = util.authenticateAdminOrMember(req, model);
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
	
	@GetMapping("search-booking")
	public String getSearchBooking(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			model.addAttribute("uNum", util.getUNum(req));
			return "booking/search_booking";
		}
		else return "user/login";
	}
	
	@PostMapping("return-home")
	public String sendUserHome(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateUser(req, model);
		if (authResult.equals("NG")) {
			return "user/login";
		}
		else return authResult;
	}
	
	@PostMapping("search-facility-by-type")
	public String postSearchFacilityByType(HttpServletRequest req, ModelMap model) {
		// Authenticate User

		String authResult = util.authenticateAdminOrMember(req, model);
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

		String authResult = util.authenticateAdminOrMember(req, model);
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

		String authResult = util.authenticateAdminOrMember(req, model);
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
	
	@PostMapping("search-booking-all")
	public String postSearchBookingAll(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			// Get Search Term
			String uNum = req.getParameter("userNumber");
			
			// Load Data into Model
			try {
				model.addAttribute("bList", bService.getBookingListByUserNum(uNum));
				return "booking/search_booking";
			} catch (UserNotFound e) {
				return "user/login";
			}
		}
		else return "user/login";
	}
	
	@PostMapping("search-booking-by-name-and-date")
	public String postSearchBookingByNameAndDate(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			// Get Search Term
			String uNum = req.getParameter("userNumber");
			LocalDate dateStart = LocalDate.parse(req.getParameter("startDate"));
			LocalDate dateEnd = LocalDate.parse(req.getParameter("endDate"));
			
			// Load Data into Model
			try {
				model.addAttribute("bList", bService.getBookingListByUserNumAndDate(uNum, dateStart, dateEnd));
				return "booking/search_booking";
			} catch (UserNotFound e) {
				return "user/login";
			}
		}
		else return "user/login";
	}
	
	@RequestMapping(value = "/update_booking/{bNum}", method = RequestMethod.GET)
	public String getUpdateBooking(@PathVariable("bNum") String bNum, HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			Booking b;
			try { b = bService.getBooking(bNum);
			} catch (BookingNotFound e) { return "booking/search_booking"; }
			
			Facility f;
			try {
				f = fService.getFacility(b);
			} catch (BookingNotFound e) {
				try {
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				} catch (UserNotFound e1) {
					return "user/login";
				}
				return "booking/search_booking";
			} catch (FacilityNotFound e) {
				try {
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				} catch (UserNotFound e1) {
					return "user/login";
				}
				return "booking/search_booking";
			}
			
			HttpSession session = req.getSession();
			session.setAttribute("facilityNumber", f.getFacilityNumber());
			session.setAttribute("bUpdate", b);
			
			// Load Data into Model
			model.addAttribute("booking", b);
			model.addAttribute("fName", f.getFacilityName());
			try {
				model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
			} catch (UserNotFound e1) {
				return "user/login";
			}
			return "booking/manage_booking";
		}
		else return "user/login";
	}
	
	@RequestMapping(value = "/expire_booking/{bNum}", method = RequestMethod.GET)
	public String getExpireBooking(@PathVariable("bNum") String bNum, HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			Booking b = new Booking();
			try {
				b = bService.getBooking(bNum);
			} catch (BookingNotFound e) {
				try {
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				} catch (UserNotFound e1) {
					return "user/login";
				}
				return "booking/search_booking";
			}
			User u = new User();
			try {
				u = uService.getUser(b);
			} catch (UserNotFound e3) {
				System.out.println(e3.getMessage());
			} catch (BookingNotFound e) {
				try {
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				} catch (UserNotFound e1) {
					return "user/login";
				}
				return "booking/search_booking";
			}
	        String fullName = u.getTitle() + " " + u.getFirstName() + " " + u.getLastName() + ", " + u.getMiddleName();
	        Facility f = new Facility();
			try {
				f = fService.getFacility(b.getFacilityNumber());
			} catch (FacilityNotFound e) {
				System.out.println(e.getMessage());
			}
			
			// Load Data into Model
			model.addAttribute("timeSlot", b.getSlotTimeStart() + " to " + b.getSlotTimeEnd());
	        model.addAttribute("fName", f.getFacilityName());
	        model.addAttribute("fullName", fullName);
	        model.addAttribute("booking", b);
			return "booking/expire_booking";
		}
		else return "user/login";
	}
	
	@RequestMapping(value = "/book_facility/{facilityNum}", method = RequestMethod.GET)
	public String bookFacility(@PathVariable("facilityNum") String facilityNum, HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
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
	
	@RequestMapping(value = "/book_facility/{fType}", method = RequestMethod.GET)
	public String bookFacilityWithType(@PathVariable("fType") String fType, HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {			
			// Load Data into Model
			model.addAttribute("fShortList", fService.getFacilityListByType(fType));
			return "booking/book_facility";
		}
		else return "user/login";
	}
	
	@PostMapping("search-slots")
	public String postSearchSlots(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
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
	
	@PostMapping("search-slots-update")
	public String postSearchSlotsUpdate(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			// Get Search Term
			HttpSession session = req.getSession();
			String fNum = (String) session.getAttribute("facilityNumber");
			Facility f;
			try {
				f = fService.getFacility(fNum);
			} catch (FacilityNotFound e0) {
				try {
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				} catch (UserNotFound e1) {
					return "user/login";
				}
				return "booking/search_booking";
			}
			String tDate = req.getParameter("targetDate");
			LocalDate targetDate = LocalDate.of(Integer.parseInt(tDate.substring(0, 4)), Integer.parseInt(tDate.substring(5, 7)), Integer.parseInt(tDate.substring(8, 10)));
			
			Booking b = (Booking) session.getAttribute("bUpdate");
	        b.setSlotDate(targetDate);
	        
			// Load Data into Model
			try {
				model.addAttribute("bUpdate", b);
				model.addAttribute("fName", f.getFacilityName());
				model.addAttribute("slotList", bService.getAvailableSlots(targetDate, fNum));
				return "booking/manage_booking";
			} catch (FacilityNotFound e0) {
				try {
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				} catch (UserNotFound e1) {
					return "user/login";
				}
				return "booking/search_booking";
			}
		}
		else return "user/login";
	}
	
	@PostMapping("booking-summary")
	public String postBookingSummary(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			// Get Slot Date
			String slotStart = req.getParameter("targetSlot");
			String slotTimeStart = slotStart;
			DecimalFormat fmt = new DecimalFormat("0000");
			String slotTimeEnd = fmt.format(Integer.parseInt(slotStart) + 100);
			
			// Get Current Booking
			HttpSession session = req.getSession();
	        Booking b = (Booking) session.getAttribute("bUpdate");
	        b.setSlotTimeEnd(slotTimeEnd);
	        b.setSlotTimeStart(slotTimeStart);
	        b.setTransDateTime(LocalDateTime.now());
	        if (b.getBookingNumber() == null) {
	        	try {
					bService.setNewBookingNum(b);
				} catch (BookingNotFound e1) {
					try {
						model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
					} catch (UserNotFound e2) {
						return "user/login";
					}
					return "booking/search_booking";
				}
	        }
	        session.setAttribute("bUpdate", b);
	        
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
				
				// For Testing Purposes : PLEASE DELETE BEFPORE RELEASE FOR PRODUCTION
				// -------------------------------------------------- START SECTION --------------------------------------------------
				u.setEmailAddress("lifestyleclub.singapore@gmail.com");
				// -------------------------------------------------- END SECTION --------------------------------------------------
				
				try {
					eService.notifyBookingSummary(b);
				} catch (UserNotFound e) {
					
				} catch (FacilityNotFound e) {
					System.out.println(e.getMessage());
				}
			} catch (BookingNotFound e1) {
				try {
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				} catch (UserNotFound e2) {
					return "user/login";
				}
				return "booking/search_booking";
			}
			return "booking/booking_summary";
		}
		else return "user/login";
	}
}
