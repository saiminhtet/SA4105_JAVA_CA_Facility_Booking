package com.lsc.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lsc.mvc.model.Issue;
import com.lsc.mvc.repository.IssueRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueServiceTests {

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
	
	// Utility Methods
	public void outputStringToConsole(String msg) {
		System.out.println("\n*********************************************************\n");
		System.out.println(msg.toString());
		System.out.println("\n*********************************************************\n");
	}
}
