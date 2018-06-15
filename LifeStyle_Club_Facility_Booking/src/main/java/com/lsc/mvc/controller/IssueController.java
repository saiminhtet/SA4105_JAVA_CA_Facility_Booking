package com.lsc.mvc.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.lsc.mvc.exception.IssueNotFound;
import com.lsc.mvc.model.Issue;
import com.lsc.mvc.service.IssueService;
import com.lsc.mvc.validator.IssueValidator;
import com.lsc.mvc.javabeans.AuthenticateUser;

@Controller
@RequestMapping("/issue")
public class IssueController {

	@Autowired
	private AuthenticateUser util; 

	@Autowired
    private IssueService iService;

	@GetMapping
	public String get(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) return "issue/search_issue";
		else return authResult;
	}

	@GetMapping("/manage-issue")
	public String ManageIssue(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) return "issue/manage_issue";
		else return "user/login";
	}
	
	@GetMapping("/search-issue")
	public String IssueSearch(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) return "issue/search_issue";
		else return "user/login";
	}
	
	@GetMapping("/search-by-facilitynumber")
	public String SearchIssueByFacilityNumber(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			String fNumber = req.getParameter("facilitynumber");
			try {
				model.addAttribute("issueListing", iService.getIssueListByFacilityNumber(fNumber));
			} catch (FacilityNotFound e) {
				e.printStackTrace();
			}
			return "issue/search_issue";
		}
		else return "user/login";
	}
	
	@GetMapping("/search-by-issuenumber")
	public String SearchIssueByIssueNumber(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			String iNumber = req.getParameter("issuenumber");
			try {
				model.addAttribute("issueListing", iService.getIssueListByIssueNumber(iNumber));
			} catch (IssueNotFound e) {
				e.printStackTrace();
			}
			return "issue/search_issue";
		}
		else return "user/login";
	}
	
	@RequestMapping(value = "/remove-issue/{issueNumber}", method = RequestMethod.GET)
	public String RemoveIssue(HttpServletRequest req, ModelMap model, @PathVariable(required = true, name = "issueNumber") String issueNumber) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			try {
				iService.removeIssue(issueNumber);
			} catch (IssueNotFound e) {
				model.addAttribute("message", "Issue not found");
			}
			try {
				model.addAttribute("issueListing", iService.getIssueListByIssueNumber(issueNumber));
			} catch (IssueNotFound e) {
				model.addAttribute("message", "Issue not found");
			}
			model.addAttribute("message", "Issue deleted successfully");
			return "issue/search_issue";	
		}
		else return "user/login";
	}
	
	@RequestMapping(value = "/update-issue/{issueNumber}", method = RequestMethod.GET)
	public String UpdateIssue(HttpServletRequest req, ModelMap model, @PathVariable(required = true, name = "issueNumber") String issueNumber) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			try {
				Issue issue = iService.getIssue(issueNumber);
				model.addAttribute("iNumber", issue.getIssueNumber());
				model.addAttribute("fNumber", issue.getFacilityNumber());
				model.addAttribute("iDesc", issue.getIssueDescription());
				model.addAttribute("iStatus", issue.getIssueStatus());
			} catch (IssueNotFound e) {
				e.printStackTrace();
			}
			return "issue/manage_issue";
		}	
		else return "user/login";
	}

	@GetMapping("/add-issue")
	public String AddIssue(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			String issueNumber = req.getParameter("issueNumber");
			System.out.println(issueNumber);
			if (issueNumber.equals("") || issueNumber == null) {
				Issue issue = new Issue();
				try {
					issue = iService.setNewIssueNum(issue);
				} catch (IssueNotFound e) {
					e.printStackTrace();
				}
				
				HttpSession session = req.getSession();
				String uNum = (String) session.getAttribute("userNumber");
				issue.setAdminNumber(uNum);
				
				String facilityNumber = req.getParameter("fnumber");
				issue.setFacilityNumber(facilityNumber);
				
				String description = req.getParameter("idescription");
				issue.setIssueDescription(description);
				
				String s = req.getParameter("iStatus");
				String status;
				if (s.equals("1")) status = "Open";
				else if (s.equals("2")) status = "Closed";
				else if (s.equals("3")) status = "Ongoing";
				else status = "Cancelled";				
				issue.setIssueStatus(status);
				
				issue.setReportDateTime(LocalDateTime.now());
				
				IssueValidator issuevalidator = new IssueValidator();

				// Create DataBinder to Bind UserValidator
				DataBinder binder = new DataBinder(issue);
				binder.setValidator(issuevalidator);

				// Validate the Data
				binder.validate();

				// Check Results
				BindingResult results = binder.getBindingResult();
				if (results.hasErrors()) {
					model.addAttribute("message", "Issue adding failed");
					outputStringToConsole(results.toString());
					return "issue/manage_issue";
				}					
				else {
					try {
						iService.addIssue(issue);
						model.addAttribute("message", "Issue added successfully");
					} catch (IssueNotFound e) {
						System.out.println(e.toString());
					}
				}
			}
			
			else {
				try {
					Issue issue = iService.getIssue(issueNumber);
					
					String description = req.getParameter("idescription");
					issue.setIssueDescription(description);
					
					String s = req.getParameter("iStatus");
					String status;
					if (s.equals("1")) status = "Open";
					else if (s.equals("2")) status = "Closed";
					else if (s.equals("3")) status = "Ongoing";
					else status = "Cancelled";				
					issue.setIssueStatus(status);
					
					IssueValidator issuevalidator = new IssueValidator();

					// Create DataBinder to Bind UserValidator
					DataBinder binder = new DataBinder(issue);
					binder.setValidator(issuevalidator);

					// Validate the Data
					binder.validate();

					// Check Results
					BindingResult results = binder.getBindingResult();
					if (results.hasErrors()) {
						model.addAttribute("message", "Issue updating failed");
						outputStringToConsole(results.toString());
						return "issue/manage_issue";
					}					
					else {
						try {
							iService.updateIssue(issue);
							model.addAttribute("message", "Issue updated successfully");
						} catch (IssueNotFound e) {
							System.out.println(e.toString());
						}
					}
				} catch (IssueNotFound e) {
					e.printStackTrace();
				}
			}
			return "issue/manage_issue";
			
		};
		return "user/login";
	}
	
	public void outputStringToConsole(String msg) {
		System.out.println("\n*********************************************************\n");
		System.out.println(msg);
		System.out.println("\n*********************************************************\n");
	}
}
