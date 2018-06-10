package com.lsc.mvc.repository;

import com.lsc.mvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT u FROM User u WHERE u.userNumber=:uNum")
	User getUserByUserNumber(@Param("uNum") String uNum);
	
	@Query("SELECT max(userId) FROM User u")
	Integer getUserIdMax();
}
