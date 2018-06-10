package com.lsc.mvc;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lsc.mvc.model.Issue;
import com.lsc.mvc.repository.IssueRepository;
import com.lsc.mvc.service.IssueService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueModelAndRepoTests {

	@Autowired
	private IssueService iService;
	@Autowired
	private IssueRepository iRepo;
	
//	@Test
//	public void whenGetIssueIdMax_thenReturnInteger() {
//		Integer maxId = iRepo.getIssueIdMax();
//		outputStringToConsole(maxId.toString());
//	}
	
//	@Test
//	public void whenGetIssueByIssueNumber_thenReturnIssue() {
//		Issue i = iRepo.getIssueByIssueNumber("I0020");
//		outputStringToConsole(i.toString());
//	}
	
//	@Test
//	public void whenGetIssueListByIssueNumber_thenReturnArrayList() {
//		ArrayList<Issue> iList = iService.getIssueListByIssueNumber("I0020");
//		for (Issue i:iList) {
//			System.out.println(i.toString());
//		}
//	}
	
//	@Test
//	public void whenGetIssueListByFacilityNumber_thenReturnArrayList() {
//		ArrayList<Issue> iList = iService.getIssueListByFacilityNumber("F002");
//		for (Issue i:iList) {
//			System.out.println(i.toString());
//		}
//	}
	
	// Utility Methods
	public void outputStringToConsole(String msg) {
		System.out.println("\n*********************************************************\n");
		System.out.println(msg.toString());
		System.out.println("\n*********************************************************\n");
	}
}
