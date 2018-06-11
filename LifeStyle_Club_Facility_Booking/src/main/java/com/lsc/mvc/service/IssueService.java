package com.lsc.mvc.service;

import java.util.ArrayList;

import com.lsc.mvc.exception.FacilityNotFound;
import com.lsc.mvc.exception.IssueNotFound;
import com.lsc.mvc.model.Issue;

public interface IssueService {
	
	Issue setNewIssueNum(Issue i) throws IssueNotFound;
	
	Issue addIssue(Issue i) throws IssueNotFound;
	
	Issue getIssue(String iNum) throws IssueNotFound;

	Issue updateIssue(Issue i) throws IssueNotFound;

	Void removeIssue(String iNum) throws IssueNotFound;
	
	ArrayList<Issue> getIssueListByIssueNumber(String iNum) throws IssueNotFound;
	
	ArrayList<Issue> getIssueListByFacilityNumber(String fNum) throws FacilityNotFound;
}
