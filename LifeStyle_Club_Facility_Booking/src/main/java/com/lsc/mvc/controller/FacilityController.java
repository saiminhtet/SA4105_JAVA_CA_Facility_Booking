package com.lsc.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lsc.mvc.exception.FacilityNotFound;
import com.lsc.mvc.javabeans.AuthenticateUser;
import com.lsc.mvc.model.Facility;
import com.lsc.mvc.service.FacilityService;
import com.lsc.mvc.validator.FacilityValidator;

@Controller
@RequestMapping("/facility")
public class FacilityController {

	@Autowired
	private FacilityService fService;
	
	@Autowired
	private AuthenticateUser util; 

	@GetMapping
	public String get(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			String loginUserName = util.getUserName(req);// to display user session name in view
			model.put("loginUserName", loginUserName);// to view user session name in home page
			return "home/admin_home";
		}
		else return authResult;
	}
	
	@GetMapping("/add_facility")	
	public String Addfacility(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			String loginUserName = util.getUserName(req);// to display user session name in view
			model.put("loginUserName", loginUserName);// to view user session name in home page
			return "facility/add_facility";
		}
		else return "user/login";
	}
	
	@GetMapping("/facility-add")
	public String FacilityAdding(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			String t = req.getParameter("fType");
			if (t.equals("") || t == null)
			{
				model.addAttribute("message", "Facility adding failed");
			}
			else {
				String fName = req.getParameter("fName");
				String c = req.getParameter("capacity");
				if (c.equals("") || c == null || !tryParseInt(c)) {
					model.addAttribute("message", "Facility adding failed");
				}
				else {
					int capacity = Integer.parseInt(c);
					String fDesc = req.getParameter("fDesc");
					Facility f = new Facility();
					try {
						f = fService.setNewFacNum(f);
						String fType;
						if (t.equals("1")) fType = "Conference Room";
						else if (t.equals("2")) fType = "Meeting Room";
						else if (t.equals("3")) fType = "Karaoke Room";
						else if (t.equals("4")) fType = "Function Room";
						else if (t.equals("5")) fType = "Badminton Court";
						else if (t.equals("6")) fType = "Squash Court";
						else if (t.equals("7")) fType = "Tennis Court";
						else fType = "Basketball Court";				
						f.setFacilityType(fType);
						f.setFacilityName(fName);
						f.setCapacity(capacity);
						f.setFacilityDescription(fDesc);
					} catch (FacilityNotFound e) {
						model.addAttribute("message", "No facility to add");
					}
					FacilityValidator fv = new FacilityValidator();
					// Create DataBinder to Bind FacilityValidator
					DataBinder binder = new DataBinder(f);
					binder.setValidator(fv);
					// Validate the Data
					binder.validate();
					// Check Results
					BindingResult results = binder.getBindingResult();
					if (results.hasErrors()) {
						model.addAttribute("message", "Facility adding failed");
					}					
					else {
						try {
							fService.addFacility(f);
							System.out.println(f.toString());
							model.addAttribute("message", "Facility added successfully");
						} catch (FacilityNotFound e) {
							model.addAttribute("message", "No facility to add");
						}
					}
				}	
			}	
			return "facility/add_facility";
		}
		else return "user/login";
	}

	@RequestMapping(value = "/manage_facility/{facilityNumber}", method = RequestMethod.GET)
	public String RemoveIssue(HttpServletRequest req, ModelMap model, @PathVariable(required = true, name = "facilityNumber") String facilityNumber) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			try {
				Facility facility = fService.getFacility(facilityNumber);
				model.addAttribute("facilityNumber", facility.getFacilityNumber());
				model.addAttribute("facilityType", facility.getFacilityType());
				model.addAttribute("facilityName", facility.getFacilityName());
				model.addAttribute("facilityCapacity", facility.getCapacity().toString());
				model.addAttribute("facilityDescription", facility.getFacilityDescription());
			} catch (FacilityNotFound e) {
				System.out.println(e.toString());
			}
			return "facility/manage_facility";	
		}
		else return "user/login";
	}
	
	@GetMapping("/facility-update")
	public String FacilityUpdating(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			String fNumber = req.getParameter("fNumber");
			if (fNumber.equals("") || fNumber == null) {
				model.addAttribute("message", "Please choose a facility");
			} else {
				try {
					Facility f = fService.getFacility(fNumber);
					String fName = req.getParameter("fName");
					f.setFacilityName(fName);
					String c = req.getParameter("capacity");
					if (!tryParseInt(c)) {
						model.addAttribute("message", "Facility updating failed");
					}
					else {
						int capacity = Integer.parseInt(c);
						f.setCapacity(capacity);
						String fDesc = req.getParameter("fDesc");
						f.setFacilityDescription(fDesc);
						
						FacilityValidator fv = new FacilityValidator();
						// Create DataBinder to Bind FacilityValidator
						DataBinder binder = new DataBinder(f);
						binder.setValidator(fv);
						// Validate the Data
						binder.validate();
						// Check Results
						BindingResult results = binder.getBindingResult();
						if (results.hasErrors()) {
							model.addAttribute("message", "Facility updating failed");
						}					
						else {
							try {
								fService.updateFacility(f);
								System.out.println(f.toString());
								model.addAttribute("message", "Facility updated successfully");
							} catch (FacilityNotFound e) {
								model.addAttribute("message", "exception");
							}
						}
					}
				} catch (FacilityNotFound e1) {
					System.out.println(e1.toString());
				}
			}
			return "facility/manage_facility";
		}
		else return "user/login";
	}
		
	public boolean tryParseInt(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}
}



