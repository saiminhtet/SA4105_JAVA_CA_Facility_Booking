package com.lsc.mvc.javabeans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.model.User;
import com.lsc.mvc.service.UserService;

@Component
public class AuthenticateUser {
	
	@Autowired
	private UserService uService;
	
	// Utility Methods
	public String authenticateAdmin(HttpServletRequest req, ModelMap model) {
		// This method checks if user is Admin
		// If not Admin, return to respective home page
		// If not valid user, return to login page
		// If Admin, return "OK"
		// Default, return "NG"
		
		HttpSession session = req.getSession();
		String uNum = (String) session.getAttribute("userNumber");
		
		
		
		// For Testing Purposes : PLEASE DELETE BEFPORE RELEASE FOR PRODUCTION
		// -------------------------------------------------- START SECTION --------------------------------------------------
		uNum = "A0002"; session.setAttribute("userNumber", uNum);
		// -------------------------------------------------- END SECTION --------------------------------------------------
		
		
		
		// Check if logged in
		if (uNum == null) return "user/login";
		else {
			try {
				// Check if uNum valid
				User u = uService.getUser(uNum); // throws UserNotFound
				
				// At this point, if no exception, 
				// uNum is valid and user object is retrieved
				
				// Add User object to model
				model.addAttribute("user", uService.getUser(uNum));
				
				// This section of pages should only be accessible by Admin, 
				// all others are redirected to respective home pages
				
				String uType = uService.getUserType(uNum); // throws UserNotFound
				getMakeAcctType(uType, model);
				switch (uType) {
					case "Member": 		return "home/member_home";
					case "Admin" : 		return "OK";
					case "SuperAdmin": 	return "home/super_admin_home";
					default: 			return "NG";
				}
			} catch (UserNotFound e) {
				// This means that userNumber is invalid, 
				// thus return to login page
				return "user/login";
			}
		}
	}
	
	public String authenticateSuperAdmin(HttpServletRequest req, ModelMap model) {
		// This method checks if user is SuperAdmin
		// If not SuperAdmin, return to respective home page
		// If not valid user, return to login page
		// If SuperAdmin, return "OK"
		// Default, return "NG"
		
		HttpSession session = req.getSession();
		String uNum = (String) session.getAttribute("userNumber");
		
		
		
		// For Testing Purposes : PLEASE DELETE BEFPORE RELEASE FOR PRODUCTION
		// -------------------------------------------------- START SECTION --------------------------------------------------
		uNum = "A0001"; session.setAttribute("userNumber", uNum);
		// -------------------------------------------------- END SECTION --------------------------------------------------
		
		
		
		// Check if logged in
		if (uNum == null) return "user/login";
		else {
			try {
				// Check if uNum valid
				User u = uService.getUser(uNum); // throws UserNotFound
				
				// At this point, if no exception, 
				// uNum is valid and user object is retrieved
				
				// Add User object to model
				model.addAttribute("user", uService.getUser(uNum));
				
				// This section of pages should only be accessible by Admin, 
				// all others are redirected to respective home pages
				
				String uType = uService.getUserType(uNum); // throws UserNotFound
				getMakeAcctType(uType, model);
				switch (uType) {
					case "Member": 		return "home/member_home";
					case "Admin" : 		return "home/admin_home";
					case "SuperAdmin": 	return "OK";
					default: 			return "NG";
				}
			} catch (UserNotFound e) {
				// This means that userNumber is invalid, 
				// thus return to login page
				return "user/login";
			}
		}
	}
	
	public String authenticateMember(HttpServletRequest req, ModelMap model) {
		// This method checks if user is Member
		// If not Member, return to respective home page
		// If not valid user, return to login page
		// If Member, return "OK"
		// Default, return "NG"
		
		HttpSession session = req.getSession();
		String uNum = (String) session.getAttribute("userNumber");
		
		
		
		// For Testing Purposes : PLEASE DELETE BEFPORE RELEASE FOR PRODUCTION
		// -------------------------------------------------- START SECTION --------------------------------------------------
		uNum = "M0006"; session.setAttribute("userNumber", uNum);
		// -------------------------------------------------- END SECTION --------------------------------------------------
		
		
		
		// Check if logged in
		if (uNum == null) return "user/login";
		else {
			try {
				// Check if uNum valid
				User u = uService.getUser(uNum); // throws UserNotFound
				
				// At this point, if no exception, 
				// uNum is valid and user object is retrieved
				
				// Add User object to model
				model.addAttribute("user", uService.getUser(uNum));
				
				// This section of pages should only be accessible by Admin, 
				// all others are redirected to respective home pages
				
				String uType = uService.getUserType(uNum); // throws UserNotFound
				getMakeAcctType(uType, model);
				switch (uType) {
					case "Member": 		return "OK";
					case "Admin" : 		return "home/admin_home";
					case "SuperAdmin": 	return "home/super_admin_home";
					default: 			return "NG";
				}
			} catch (UserNotFound e) {
				// This means that userNumber is invalid, 
				// thus return to login page
				return "user/login";
			}
		}
	}
	
	public String authenticateUser(HttpServletRequest req, ModelMap model) {
		// This method checks if user is User
		// If not User, return to login page
		// If User, return respective welcome page
		// Default, return "NG"
		
		HttpSession session = req.getSession();
		String uNum = (String) session.getAttribute("userNumber");
		
		
		
		// For Testing Purposes : PLEASE DELETE BEFPORE RELEASE FOR PRODUCTION
		// -------------------------------------------------- START SECTION --------------------------------------------------
		uNum = "A0001"; session.setAttribute("userNumber", uNum);
//		uNum = "A0002"; session.setAttribute("userNumber", uNum);
//		uNum = "M0006"; session.setAttribute("userNumber", uNum);
		// -------------------------------------------------- END SECTION --------------------------------------------------
		
		
		
		// Check if logged in
		if (uNum == null) { return "user/login"; }
		else {
			try {
				// Check if uNum valid
				User u = uService.getUser(uNum); // throws UserNotFound
				
				// At this point, if no exception, 
				// uNum is valid and user object is retrieved
				
				// Add User object to model
				model.addAttribute("user", uService.getUser(uNum));
				
				// This section of pages should only be accessible by Admin, 
				// all others are redirected to respective home pages
				
				String uType = uService.getUserType(uNum); // throws UserNotFound
				getMakeAcctType(uType, model);
				switch (uType) {
					case "Member": 		return "home/member_home";
					case "Admin" : 		return "home/admin_home";
					case "SuperAdmin": 	return "home/super_admin_home";
					default: 			return "NG";
				}
			} catch (UserNotFound e) {
				// This means that userNumber is invalid, 
				// thus return to login page
				return "user/login";
			}
		}
	}
	
	public String getUNum(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String uNum = (String) session.getAttribute("userNumber");
		return uNum;
	}
	
	public void checkMakeAcctType(HttpServletRequest req, ModelMap model) {
		HttpSession session = req.getSession();
		String uNum = (String) session.getAttribute("userNumber");
		if (uNum == null) model.addAttribute("makeAcctType", "Member"); 
	}
	
	public void getMakeAcctType(String uType, ModelMap model) {
		String makeAcctType = "";
		switch (uType) {
			case "Member": 		makeAcctType = "";
			case "Admin" : 		makeAcctType = "Member";
			case "SuperAdmin": 	makeAcctType = "Admin";
			default: 			makeAcctType = "Member";
		}
		model.addAttribute("makeAcctType", makeAcctType);
	}
	
	public void storeSessionVar(HttpServletRequest req, ModelMap model, String key, String value) {
		HttpSession session = req.getSession();
		session.setAttribute(key, value);
	}
}
