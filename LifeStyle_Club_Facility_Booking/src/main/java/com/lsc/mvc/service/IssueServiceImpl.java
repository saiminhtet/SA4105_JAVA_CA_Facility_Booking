package com.lsc.mvc.service;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsc.mvc.exception.BookingNotFound;
import com.lsc.mvc.exception.FacilityNotFound;
import com.lsc.mvc.exception.IssueNotFound;
import com.lsc.mvc.model.Issue;
import com.lsc.mvc.repository.IssueRepository;

@Service
public class IssueServiceImpl implements IssueService {

	@Resource
	private IssueRepository iRepo;
	@Resource
	private FacilityService fService;
	
	public Issue setNewIssueNum(Issue i) throws IssueNotFound {
		if (i == null) throw new IssueNotFound("Issue object provided cannot be null");
		
		// Getting Current Max issueId
		Integer newId = iRepo.getIssueIdMax() + 1;
		
		// Assigning New userNumber
		DecimalFormat fmt = new DecimalFormat("0000");
		String prefix = "I";
		i.setIssueNumber(prefix + fmt.format(newId));
		return i;
	}
	
	@Override
	@Transactional
	public Issue addIssue(Issue i) throws IssueNotFound {
		if (i == null) throw new IssueNotFound("Issue object provided cannot be null");
		return iRepo.saveAndFlush(i);
	}
	
	@Override
	@Transactional
	public Issue getIssue(String iNum) throws IssueNotFound {
		Issue i = iRepo.getIssueByIssueNumber(iNum);
		if (i == null) throw new IssueNotFound("Issue number provided is invalid");
		return i;
	}

	@Override
	@Transactional
	public Issue updateIssue(Issue i) throws IssueNotFound {
		if (i == null) throw new IssueNotFound("Issue object provided cannot be null");
		return iRepo.saveAndFlush(i);
	}

	@Override
	@Transactional
	public Void removeIssue(String iNum) throws IssueNotFound {
		iRepo.delete(getIssue(iNum));
		return null;
	}
	
	@Override
	public ArrayList<Issue> getIssueListByIssueNumber(String iNum) throws IssueNotFound {
		if (getIssue(iNum) == null) throw new IssueNotFound("Issue number provided is invalid");
		// Retrieve Existing Issue Object
		Issue i = getIssue(iNum);
		
		// Create New ArrayList and Add Issue Object
		ArrayList<Issue> iList = new ArrayList<Issue>();
		iList.add(i);
		return iList;
	}
	
	@Override
	public ArrayList<Issue> getIssueListByFacilityNumber(String fNum) throws FacilityNotFound {
		if (fService.getFacility(fNum)==null) throw new FacilityNotFound("Facility number provided is invalid");
		return iRepo.getIssueListByFacilityNumber(fNum);
	}
}
