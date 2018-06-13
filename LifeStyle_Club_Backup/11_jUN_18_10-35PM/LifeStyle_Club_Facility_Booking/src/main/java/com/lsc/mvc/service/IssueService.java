package com.lsc.mvc.service;

import java.util.ArrayList;

import com.lsc.mvc.model.Issue;

public interface IssueService {
	
	Issue setNewIssueNum(Issue i);
	
	Issue addIssue(Issue i);
	
	Issue getIssue(String iNum);

	Issue updateIssue(Issue i);

	Void removeIssue(String iNum);
	
	ArrayList<Issue> getIssueListByIssueNumber(String iNum);
	
	ArrayList<Issue> getIssueListByFacilityNumber(String fNum);
}
