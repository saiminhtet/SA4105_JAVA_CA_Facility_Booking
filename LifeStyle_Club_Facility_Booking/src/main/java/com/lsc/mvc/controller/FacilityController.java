package com.lsc.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
//			Facility f = new Facility();
//			model.addAttribute("facility", f);
			return "home/admin_home";
		}
		else return authResult;
	}
	
	@GetMapping("/add_facility")	
	public String Addfacility(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
//			Facility facility = new Facility();
//			model.put("facilityType", fService.getFacilityTypes());//Retrieve type list to populate dropdownlist in addfacility form
//	        model.put("facility", facility);
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
						e.printStackTrace();
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
//						System.out.println(results.toString());
					}					
					else {
						try {
							fService.addFacility(f);
							System.out.println(f.toString());
							model.addAttribute("message", "Facility added successfully");
						} catch (FacilityNotFound e) {
//							System.out.println(e.toString());
						}
					}
				}	
			}	
			return "facility/add_facility";
		}
		else return "user/login";
	}

//	@PostMapping("/add_facility")
//	public String addNewFacility(HttpServletRequest req, Facility facility) {//throws ResourceDefinitionInvalid, FacilityNotFound{
//				
//		//Setting the appropriate facilityNumber for the facility object
//		try {
//			facilityService.setNewFacNum(facility);
//
//		} catch (FacilityNotFound e) {
//			// This means that the "facility" object passed into this method is null, thus redirect back to addfacility page to restart
//			return "facility/add_facility";
//		}
//	
//
//			try {
//				facilityService.addFacility(facility); // throws UserNotFound : this is to catch is the user object passed to the addUser method is null
//				System.out.println(facility.toString());
//				return "home/admin_home"; // This means that add facility success
//			} catch (FacilityNotFound e) {
//				System.out.println(e.getMessage());
//			}
//
//	  return "facility/add_facility"; // default redirection to retry
//	}
//	
//	
//	@GetMapping("/manage_facility")
//	public String Managefacility(ModelMap model) {
//		Facility facility = new Facility();
//		//String facilityNumber = "F029";
//		model.put("facilityType", facilityService.getFacilityTypes());//Retrieve type list to populate dropdownlist in addfacility form
//        model.put("facility", facility);
//
//		return "facility/manage_facility";
//	}
//
//	@PostMapping("/manage_facility")	
//	public String updateFacility(HttpServletRequest req, Facility facility) throws FacilityNotFound {// throws ResourceDefinitionInvalid {
//		
//		// Retrieves facilityNumber from session
//		String facilityNumber = "F020";//this.getFacilityNumber(req);
////		if(facilityNumber == null) {
////			return "facility/search_facility";
////		}
//		Facility existingFac= new Facility();
//		existingFac=facilityService.getFacility("F020");
//		existingFac.setFacilityType(facility.getFacilityType());
//		existingFac.setFacilityName(facility.getFacilityName());
//		existingFac.setCapacity(facility.getCapacity());
//		existingFac.setFacilityDescription(facility.getFacilityDescription());
//
//		try {
//			facilityService.updateFacility(existingFac);
//			System.out.println(existingFac.toString());
//			return "facility/manage_facility"; // This means that updating facility success
//		} catch (FacilityNotFound e) {
//			System.out.println(e.getMessage());
//		}
//		return "facility/manage_facility"; // default redirection to retry
//		}
		
	public boolean tryParseInt(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}
}



