package com.lsc.mvc.repository;

import com.lsc.mvc.model.User;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT max(userId) FROM User u")
	Integer getUserIdMax();
	
	@Query("SELECT u FROM User u WHERE lower(u.userNumber)=lower(:uNum)")
	User getUserByUserNumber(@Param("uNum") String uNum);
	
	@Query("SELECT u FROM User u WHERE lower(u.emailAddress)=lower(:eAdd)")
	User getUserByEmailAddress(@Param("eAdd") String eAdd);
	
	@Query("SELECT u FROM User u WHERE lower(u.userNumber) LIKE 'm%' AND ("
			+ "lower(u.firstName) LIKE lower(concat('%', :mName,'%')) OR "
			+ "lower(u.lastName) LIKE lower(concat('%', :mName,'%')) OR "
			+ "lower(u.middleName) LIKE lower(concat('%', :mName,'%')))")
	ArrayList<User> getMemberListByName(@Param("mName") String mName);
	
	@Query("SELECT u FROM User u WHERE lower(u.userNumber) LIKE 'a%' AND ("
			+ "lower(u.firstName) LIKE lower(concat('%', :aName,'%')) OR "
			+ "lower(u.lastName) LIKE lower(concat('%', :aName,'%')) OR "
			+ "lower(u.middleName) LIKE lower(concat('%', :aName,'%')))")
	ArrayList<User> getAdminListByName(@Param("aName") String aName);
}
