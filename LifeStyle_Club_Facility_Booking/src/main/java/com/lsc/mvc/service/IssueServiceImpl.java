package com.lsc.mvc.service;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsc.mvc.model.Issue;
import com.lsc.mvc.repository.IssueRepository;

@Service
public class IssueServiceImpl implements IssueService {

	@Resource
	private IssueRepository iRepo;
	
	public Issue setNewIssueNum(Issue i) {
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
	public Issue addIssue(Issue i) {
		return iRepo.saveAndFlush(i);
	}
	
	@Override
	@Transactional
	public Issue getIssue(String iNum) {
		return iRepo.getIssueByIssueNumber(iNum);
	}

	@Override
	@Transactional
	public Issue updateIssue(Issue i) {
		return iRepo.saveAndFlush(i);
	}

	@Override
	@Transactional
	public Void removeIssue(String iNum) {
		iRepo.delete(getIssue(iNum));
		return null;
	}
	
	@Override
	public ArrayList<Issue> getIssueListByIssueNumber(String iNum) {
		Issue i = getIssue(iNum);
		ArrayList<Issue> iList = new ArrayList<Issue>();
		iList.add(i);
		return iList;
	}
	
	@Override
	public ArrayList<Issue> getIssueListByFacilityNumber(String fNum) {
		return iRepo.getIssueListByFacilityNumber(fNum);
	}
}
