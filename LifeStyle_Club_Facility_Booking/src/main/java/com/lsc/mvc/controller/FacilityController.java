package com.lsc.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.boot.model.relational.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lsc.mvc.exception.FacilityNotFound;
import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.javabeans.AuthenticateUser;
import com.lsc.mvc.model.Facility;
import com.lsc.mvc.model.User;
import com.lsc.mvc.service.FacilityService;
import com.lsc.mvc.service.UserService;
import com.lsc.mvc.validator.FacilityValidator;
import com.lsc.mvc.validator.UserValidator;

@Controller
@RequestMapping("/facility")
public class FacilityController {

	@Autowired
	private FacilityService facilityService;
	
	@Autowired
	private AuthenticateUser util; 

	@GetMapping
	public String get(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			Facility f = new Facility();
			model.addAttribute("facility", f);
			return "facility/manage_facility";
		}
		else return authResult;
	}
	
	@GetMapping("/add_facility")	
	public String Addfacility(ModelMap model) {
		Facility facility = new Facility();
		model.put("facilityType", facilityService.getFacilityTypes());//Retrieve type list to populate dropdownlist in addfacility form
        model.put("facility", facility);

		return "facility/add_facility";
	}

	@PostMapping("/add_facility")
	public String addNewFacility(HttpServletRequest req, Facility facility) {//throws ResourceDefinitionInvalid, FacilityNotFound{
				
		//Setting the appropriate facilityNumber for the facility object
		try {
			facilityService.setNewFacNum(facility);

		} catch (FacilityNotFound e) {
			// This means that the "facility" object passed into this method is null, thus redirect back to addfacility page to restart
			return "facility/add_facility";
		}
	

			try {
				facilityService.addFacility(facility); // throws UserNotFound : this is to catch is the user object passed to the addUser method is null
				System.out.println(facility.toString());
				return "home/admin_home"; // This means that add facility success
			} catch (FacilityNotFound e) {
				System.out.println(e.getMessage());
			}

	  return "facility/add_facility"; // default redirection to retry
	}
	
	
	@GetMapping("/manage_facility")
	public String Managefacility(ModelMap model) {
		Facility facility = new Facility();
		//String facilityNumber = "F029";
		model.put("facilityType", facilityService.getFacilityTypes());//Retrieve type list to populate dropdownlist in addfacility form
        model.put("facility", facility);

		return "facility/manage_facility";
	}

	@PostMapping("/manage_facility")	
	public String updateFacility(HttpServletRequest req, Facility facility) throws FacilityNotFound {// throws ResourceDefinitionInvalid {
		
		// Retrieves facilityNumber from session
		String facilityNumber = "F020";//this.getFacilityNumber(req);
//		if(facilityNumber == null) {
//			return "facility/search_facility";
//		}
		Facility existingFac= new Facility();
		existingFac=facilityService.getFacility("F020");
		existingFac.setFacilityType(facility.getFacilityType());
		existingFac.setFacilityName(facility.getFacilityName());
		existingFac.setCapacity(facility.getCapacity());
		existingFac.setFacilityDescription(facility.getFacilityDescription());

		try {
			facilityService.updateFacility(existingFac);
			System.out.println(existingFac.toString());
			return "facility/manage_facility"; // This means that updating facility success
		} catch (FacilityNotFound e) {
			System.out.println(e.getMessage());
		}
		return "facility/manage_facility"; // default redirection to retry
		}
		
		

}



