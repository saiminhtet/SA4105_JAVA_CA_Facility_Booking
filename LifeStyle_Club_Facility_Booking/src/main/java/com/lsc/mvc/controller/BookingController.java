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

	@GetMapping
	public String getBookingPage(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateUser(req, model);
		String loginUserName = util.getUserName(req);// to display user session name in view
		if (authResult.equals("NG"))
			return "user/login";
		else
			model.put("loginUserName", loginUserName);// to view user session name in home page
			return authResult;
	}

	@PostMapping
	public String postBookingPage(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateUser(req, model);
		String loginUserName = util.getUserName(req);// to display user session name in view

		if (authResult.equals("NG"))
			return "user/login";
		else
			model.put("loginUserName", loginUserName);// to view user session name in home page
			return authResult;
	}

	@GetMapping("/search_facility")
	public String getSearchFacility(HttpServletRequest req, ModelMap model) {
		// Authenticate User

		String authResult = util.authenticateAdminOrMember(req, model);

		if (authResult.equals("OK")) {

			try {
				String loginUserName = util.getUserName(req);// to display user session name in view
				String AccountType = uService.getUserType(util.getUNum(req));
				model.addAttribute("acctType", AccountType);
				model.addAttribute("fTypeList", fService.getFacilityTypes());
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/search_facility";
			} catch (UserNotFound e) {
				return "user/login";
			}
		} else
			return "user/login";
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
				String loginUserName = util.getUserName(req);// to display user session name in view
				String AccountType = uService.getUserType(util.getUNum(req));
				model.addAttribute("acctType", AccountType);
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				model.addAttribute("fTypeList", fService.getFacilityTypes());
				model.put("fList", fService.getFacilityListByType(fType));
				return "booking/search_facility";
			} catch (UserNotFound e) {
				return "user/login";
			}
		} else
			return "user/login";
	}

	@PostMapping("search-facility-by-number")
	public String postSearchFacilityByNumber(HttpServletRequest req, ModelMap model) {
		// Authenticate User

		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			String loginUserName = util.getUserName(req);// to display user session name in view
			String AccountType;
			
			try {
				AccountType = uService.getUserType(util.getUNum(req));
			} catch (UserNotFound e2) {
				// TODO Auto-generated catch block
				return "user/login";
			}
			// Get Search Term
			String fNum = req.getParameter("facilityNumber");

			// Load Data into Model
			try {
				
			    
				model.addAttribute("fTypeList", fService.getFacilityTypes());
				model.put("fList", fService.getFacilityListByNumber(fNum));
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				
				return "booking/search_facility";
			} catch (FacilityNotFound e) {
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/search_facility";
			} 
		} else
			return "user/login";
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
				String loginUserName = util.getUserName(req);// to display user session name in view
				String AccountType = uService.getUserType(util.getUNum(req));
				model.addAttribute("acctType", AccountType);
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/search_facility";
			} catch (UserNotFound e) {
				return "user/login";
			}

		} else
			return "user/login";
	}

	@PostMapping("search-slots")
	public String postSearchSlots(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			String AccountType;
			String loginUserName = util.getUserName(req);// to display user session name in view
			// Get Search Term
			String fNum = req.getParameter("facilityList");
			Facility f;
			try {
				f = fService.getFacility(fNum);
			} catch (FacilityNotFound e0) {
				try {
					AccountType = uService.getUserType(util.getUNum(req));
					model.put("loginUserName", loginUserName);// to view user session name in home page
					model.put("MenuType", AccountType); // Set menu type by user type
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				} catch (UserNotFound e1) {
					return "user/login";
				}
				model.addAttribute("fTypeList", fService.getFacilityTypes());
				return "booking/search_facility";
			}
			String tDate = req.getParameter("targetDate");
			LocalDate targetDate = LocalDate.of(Integer.parseInt(tDate.substring(0, 4)),
					Integer.parseInt(tDate.substring(5, 7)), Integer.parseInt(tDate.substring(8, 10)));

			Booking b = new Booking();
			b.setFacilityNumber(f.getFacilityNumber());
			b.setUserNumber(util.getUNum(req));
			b.setSlotDate(targetDate);

			HttpSession session = req.getSession();
			session.setAttribute("bCurrent", b);
			
			// Load Data into Model
			try {
				AccountType = uService.getUserType(util.getUNum(req));
				model.addAttribute("bCurrent", b);
				model.addAttribute("fName", f.getFacilityName());
				model.addAttribute("fShortList", fService.getFacilityListByType(f.getFacilityType()));
				model.addAttribute("slotList", bService.getAvailableSlots(targetDate, fNum));
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/book_facility";
			} catch (FacilityNotFound e0) {
				try {
					AccountType = uService.getUserType(util.getUNum(req));
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
					model.put("loginUserName", loginUserName);// to view user session name in home page
					model.put("MenuType", AccountType); // Set menu type by user type
				} catch (UserNotFound e1) {
					return "user/login";
				}
				model.addAttribute("fTypeList", fService.getFacilityTypes());
				return "booking/search_facility";
			}
			catch (UserNotFound e0) {
				return "user/login";
			}
		} else
			return "user/login";
	}

	@PostMapping("/booking-summary")
	public String postBookingSummary(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			String loginUserName = util.getUserName(req);// to display user session name in view
			String AccountType;
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
					AccountType = uService.getUserType(util.getUNum(req));
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				} catch (UserNotFound e2) {
					return "user/login";
				}
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				model.addAttribute("fTypeList", fService.getFacilityTypes());
				return "booking/search_facility";
			}
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
				AccountType = uService.getUserType(util.getUNum(req));
				bService.addBooking(b);
				try {
					eService.notifyBookingSummary(b);
				} catch (UserNotFound e) {

				} catch (FacilityNotFound e) {
					System.out.println(e.getMessage());
				}
			} catch (BookingNotFound e1) {
				try {
					AccountType = uService.getUserType(util.getUNum(req));
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				} catch (UserNotFound e2) {
					return "user/login";
				}
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				model.addAttribute("fTypeList", fService.getFacilityTypes());
				return "booking/search_facility";
			} catch ( UserNotFound e2) {
				return "user/login";
			}
			model.put("loginUserName", loginUserName);// to view user session name in home page
			model.put("MenuType", AccountType); // Set menu type by user type
			return "booking/booking_summary";
		} else
			return "user/login";
	}

	@RequestMapping(value = "/book_facility_by_num/{facilityNum}", method = RequestMethod.GET)
	public String bookFacility(@PathVariable("facilityNum") String facilityNum, HttpServletRequest req,
			ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			String loginUserName = util.getUserName(req);// to display user session name in view
			String AccountType;
			Facility f;
			try {
				f = fService.getFacility(facilityNum);
				AccountType = uService.getUserType(util.getUNum(req));
				model.addAttribute("acctType", AccountType);
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
			} catch (FacilityNotFound e) {
				return "booking/search_facility";
			} catch (UserNotFound e) {
				return "booking/search_facility";
			}

			Booking b = new Booking();
			b.setSlotDate(LocalDate.now());
			model.addAttribute("bCurrent", b);//Set default date to system current date

			// Load Data into Model
			model.addAttribute("fShortList", fService.getFacilityListByType(f.getFacilityType()));
			return "booking/book_facility";
		} else
			return "user/login";

	}

	@RequestMapping(value = "/book_facility/{fType}", method = RequestMethod.GET)
	public String bookFacilityWithType(@PathVariable("fType") String fType, HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			String AccountType;
			String loginUserName = util.getUserName(req);// to display user session name in view
			try {
				 AccountType = uService.getUserType(util.getUNum(req));
			} catch (UserNotFound e) {
				return "user/login";
			}
			// Load Data into Model
			model.put("loginUserName", loginUserName);// to view user session name in home page
			model.put("MenuType", AccountType); // Set menu type by user type
			model.addAttribute("fShortList", fService.getFacilityListByType(fType));
			return "booking/book_facility";
		} else
			return "user/login";
	}

	@GetMapping("/search_booking")
	public String getSearchBooking(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			String loginUserName = util.getUserName(req);// to display user session name in view
			String AccountType;
			model.addAttribute("uNum", util.getUNum(req));
			try {
				AccountType = uService.getUserType(util.getUNum(req));
				model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/search_booking";
			} catch (UserNotFound e) {
				return "user/login";
			}
		} else
			return "user/login";
	}

	@PostMapping("/search-booking-all")
	public String postSearchBookingAll(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			// Get Search Term
			String uNum = util.getUNum(req);
			String loginUserName = util.getUserName(req);// to display user session name in view
			String AccountType;
			try {
				model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				AccountType = uService.getUserType(util.getUNum(req));
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
			} catch (UserNotFound e) {
				return "user/login";
			}
			try {
				model.addAttribute("bList", bService.getBookingListByUserNum(uNum));
				model.addAttribute("uNum", uNum);
				return "booking/search_booking";
			} catch (UserNotFound e) {
				model.addAttribute("uNum", uNum);
				return "booking/search_booking";
			}
		}

		else
			return "user/login";
	}

	@PostMapping("/search-booking-by-name-and-date")
	public String postSearchBookingByNameAndDate(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			String loginUserName = util.getUserName(req);// to display user session name in view
			String AccountType;
			String acctType;
			try {
				AccountType = uService.getUserType(util.getUNum(req));
				acctType = uService.getUserType(util.getUNum(req));
			} catch (UserNotFound e1) {
				return "user/login";
			}
			model.addAttribute("acctType", acctType);
			// Get Search Term
			String uNum;
			if (acctType == "Member")
				uNum = util.getUNum(req);
			else
				uNum = req.getParameter("tbx_uNum");
			model.addAttribute("uNum", uNum);

			String StartDate = req.getParameter("startDate");
			String EndDate = req.getParameter("endDate");
			if (StartDate=="" && EndDate=="" || StartDate=="" || EndDate=="") {
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				model.put("warning", "Please Select Start Date and End Date!");
				return "booking/search_booking";
			}
			LocalDate dateStart = LocalDate.parse(StartDate);
			LocalDate dateEnd = LocalDate.parse(EndDate);
			
			// Load Data into Model
			try {
				model.addAttribute("bList", bService.getBookingListByUserNumAndDate(uNum, dateStart, dateEnd));
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/search_booking";
			} catch (UserNotFound e) {
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/search_booking";
			}
		} else
			return "booking/search_booking";
	}

	@RequestMapping(value = "/update_booking/{bNum}", method = RequestMethod.GET)
	public String getUpdateBooking(@PathVariable("bNum") String bNum, HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			String loginUserName = util.getUserName(req);// to display user session name in view
			String AccountType;
			Booking b;
			try {
				b = bService.getBooking(bNum);
			} catch (BookingNotFound e) {
				return "booking/search_booking";
			}

			Facility f;
			try {
				f = fService.getFacility(b);
			} catch (BookingNotFound e) {
				try {
					AccountType = uService.getUserType(util.getUNum(req));
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				} catch (UserNotFound e1) {
					return "user/login";
				}
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/search_booking";
			} catch (FacilityNotFound e) {
				try {
					AccountType = uService.getUserType(util.getUNum(req));
					model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				} catch (UserNotFound e1) {
					return "user/login";
				}
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/search_booking";
			}

			HttpSession session = req.getSession();
			session.setAttribute("facilityNumber", f.getFacilityNumber());
			session.setAttribute("bUpdate", b);

			// Load Data into Model
			model.addAttribute("booking", b);
			model.addAttribute("fName", f.getFacilityName());
			try {
				AccountType = uService.getUserType(util.getUNum(req));
				model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
			} catch (UserNotFound e1) {
				return "user/login";
			}
			return "booking/manage_booking";
		} else
			return "user/login";
	}

	@PostMapping("search-slots-update")
	public String postSearchSlotsUpdate(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			String loginUserName = util.getUserName(req);// to display user session name in view
			String AccountType;
			// Get Search Term
			try {
				AccountType = uService.getUserType(util.getUNum(req));
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
			} catch (UserNotFound e1) {
				return "user/login";
			}

			HttpSession session = req.getSession();
			Booking b = (Booking) session.getAttribute("bUpdate");
			String fNum = b.getFacilityNumber();
			Facility f;
			try {
				f = fService.getFacility(fNum);
			} catch (FacilityNotFound e0) {
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/search_booking";
			}
			String tDate = req.getParameter("targetDate");
			LocalDate targetDate = LocalDate.of(Integer.parseInt(tDate.substring(0, 4)),
					Integer.parseInt(tDate.substring(5, 7)), Integer.parseInt(tDate.substring(8, 10)));
			b.setSlotDate(targetDate);

			// Load Data into Model
			try {
				model.addAttribute("bUpdate", b);
				model.addAttribute("fName", f.getFacilityName());
				model.addAttribute("slotList", bService.getAvailableSlots(targetDate, fNum));
				model.addAttribute("booking", b);
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/manage_booking";
			} catch (FacilityNotFound e0) {
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/search_booking";
			}
		} else
			return "user/login";
	}

	@PostMapping("/booking-summary-update")
	public String postBookingSummaryUpdate(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			String loginUserName = util.getUserName(req);// to display user session name in view
			String AccountType;
			
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

			try {
				AccountType = uService.getUserType(util.getUNum(req));
			} catch (UserNotFound e3) {
				return "user/login";
			}
			model.put("loginUserName", loginUserName);// to view user session name in home page
			model.put("MenuType", AccountType); // Set menu type by user type
			model.addAttribute("timeSlot", slotTimeStart + " to " + slotTimeEnd);
			model.addAttribute("fName", f.getFacilityName());
			model.addAttribute("fullName", fullName);
			model.addAttribute("booking", b);
			try {
				bService.addBooking(b);
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
		} else
			return "user/login";
	}

	@RequestMapping(value = "/expire_booking/{bNum}", method = RequestMethod.GET)
	public String getExpireBooking(@PathVariable("bNum") String bNum, HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			String loginUserName = util.getUserName(req);// to display user session name in view
			String AccountType;
			Booking b = new Booking();
			try {
				AccountType = uService.getUserType(util.getUNum(req));
				model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
			} catch (UserNotFound e1) {
				return "user/login";
			}
			try {
				b = bService.getBooking(bNum);
			} catch (BookingNotFound e) {
				return "booking/search_booking";
			}
			User u = new User();
			try {
				u = uService.getUser(b);
			} catch (UserNotFound e3) {
				System.out.println(e3.getMessage());
			} catch (BookingNotFound e) {
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
			model.put("loginUserName", loginUserName);// to view user session name in home page
			model.put("MenuType", AccountType); // Set menu type by user type
			model.addAttribute("timeSlot", b.getSlotTimeStart() + " to " + b.getSlotTimeEnd());
			model.addAttribute("fName", f.getFacilityName());
			model.addAttribute("fullName", fullName);
			model.addAttribute("booking", b);
			HttpSession session = req.getSession();
			session.setAttribute("bRemove", b);
			return "booking/expire_booking";
		} else
			return "user/login";
	}

	@RequestMapping(value = "/expire_facility/{facilityNum}", method = RequestMethod.GET)
	public String expireFacility(@PathVariable("facilityNum") String facilityNum, HttpServletRequest req,
			ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			String loginUserName = util.getUserName(req);// to display user session name in view
			String AccountType;
			try {
				AccountType = uService.getUserType(util.getUNum(req));
			} catch (UserNotFound e1) {
				return "user/login";
			}
			Facility f;
			try {
				f = fService.getFacility(facilityNum);
			} catch (FacilityNotFound e) {
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/search_facility";
			}

			// Load Data into Model
			model.addAttribute("facility", f);
			HttpSession session = req.getSession();
			session.setAttribute("fRemove", f);
			model.put("loginUserName", loginUserName);// to view user session name in home page
			model.put("MenuType", AccountType); // Set menu type by user type
			return "booking/expire_facility";
		} else
			return "user/login";
	}

	@PostMapping("/remove-facility")
	public String postRemoveFacility(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdminOrMember(req, model);
		if (authResult.equals("OK")) {
			String loginUserName = util.getUserName(req);// to display user session name in view
			String AccountType;
			// Get Search Term
			HttpSession session = req.getSession();
			Facility f = (Facility) session.getAttribute("fRemove");
			try {
				AccountType = uService.getUserType(util.getUNum(req));
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
			} catch (UserNotFound e1) {
				return "user/login";
			}
			model.addAttribute("uNum", util.getUNum(req));
			model.addAttribute("fTypeList", fService.getFacilityTypes());
			try {
				fService.removeFacility(f.getFacilityNumber());
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/search_facility";
			} catch (FacilityNotFound e) {
				return "booking/search_facility";
			}
		} else
			return "user/login";
	}

	@PostMapping("/remove-booking")
	public String postRemoveBooking(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			String loginUserName = util.getUserName(req);// to display user session name in view
			String AccountType;
			// Get Search Term
			HttpSession session = req.getSession();
			Booking b = (Booking) session.getAttribute("bRemove");
			try {
				AccountType = uService.getUserType(util.getUNum(req));
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				model.addAttribute("acctType", uService.getUserType(util.getUNum(req)));
			} catch (UserNotFound e1) {
				return "user/login";
			}
			model.addAttribute("uNum", util.getUNum(req));
			try {
				bService.removeBooking(b.getBookingNumber());
				eService.notifyRemoveBooking(b);
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/search_booking";
			} catch (BookingNotFound e) {
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/search_booking";
			} catch (UserNotFound e) {
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/search_booking";
			} catch (FacilityNotFound e) {
				model.put("loginUserName", loginUserName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type
				return "booking/search_booking";
			}
		} else
			return "user/login";
	}

}
