package com.lsc.mvc.repository;

import com.lsc.mvc.model.Facility;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FacilityRepository extends JpaRepository<Facility, Integer>{
	@Query("SELECT max(facilityId) FROM Facility f")
	Integer getFacIdMax();
	
	@Query("SELECT f FROM Facility f WHERE lower(f.facilityNumber)=lower(:fNum)")
	Facility getFacilityByFacilityNumber(@Param("fNum") String fNum);
	
	@Query("SELECT DISTINCT(f.facilityType) FROM Facility f")
	ArrayList<String> getFacilityTypes();
	
	@Query("SELECT f FROM Facility f WHERE "
			+ "lower(f.facilityType) LIKE lower(concat('%', :fType,'%'))")
	ArrayList<Facility> getFacilityListByType(@Param("fType") String fType);
	
	@Query("SELECT f FROM Facility f WHERE "
			+ "lower(f.facilityName) LIKE lower(concat('%', :fName,'%'))")
	ArrayList<Facility> getFacilityListByName(@Param("fName") String fName);
}
