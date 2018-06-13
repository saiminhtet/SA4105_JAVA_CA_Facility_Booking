package com.lsc.mvc.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lsc.mvc.model.Booking;
import com.lsc.mvc.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
	@Query("SELECT max(issueId) FROM Issue i")
	Integer getIssueIdMax();
	
	@Query("SELECT i FROM Issue i WHERE lower(i.issueNumber)=lower(:iNum)")
	Issue getIssueByIssueNumber(@Param("iNum") String iNum);
	
	@Query("SELECT i FROM Issue i WHERE lower(i.facilityNumber)=lower(:fNum)")
	ArrayList<Issue> getIssueListByFacilityNumber(@Param("fNum") String fNum);
}
