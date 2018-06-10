package com.lsc.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lsc.mvc.model.Booking;
import com.lsc.mvc.repository.BookingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingModelAndRepoTests {

	@Autowired
	private BookingRepository bRepo;
	
//	@Test
//	public void whenGetBookingIdMax_thenReturnInteger() {
//		Integer maxId = bRepo.getBookingIdMax();
//		outputStringToConsole(maxId.toString());
//	}
	
//	@Test
//	public void whenGetBookingByBookingNumber_thenReturnBooking() {
//		Booking b = bRepo.getBookingByBookingNumber("B001000");
//		outputStringToConsole(b.toString());
//	}
	
	// Utility Methods
	public void outputStringToConsole(String msg) {
		System.out.println("\n*********************************************************\n");
		System.out.println(msg.toString());
		System.out.println("\n*********************************************************\n");
	}
}
