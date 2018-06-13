package com.lsc.mvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lsc.mvc.model.Booking;
import com.lsc.mvc.model.Facility;
import com.lsc.mvc.repository.BookingRepository;
import com.lsc.mvc.service.BookingService;
import com.lsc.mvc.service.FacilityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTests {

	@Autowired
	private BookingService bService;
	@Autowired
	private BookingRepository bRepo;
	@Autowired
	private FacilityService fService;
	
//	@Test
//	public void whenGetBooking_thenReturnBooking() {
//		Booking b = bService.getBooking("B001000");
//		outputStringToConsole(b.toString());
//	}
	
//	@Test
//	public void whenAddBooking_thenReturnBooking() {
//		Booking b = new Booking("M0055", "F023", 
//				LocalDate.of(2018,6,10), "1000", "1200");
//		bService.setNewBookingNum(b);
//		
//		outputStringToConsole(b.toString());
//		
//		// Adding To Database
//		bService.addBooking(b);
//		
//		for (Booking bk:bRepo.findAll()) {
//			System.out.println(bk.toString());
//		}
//	}
	
//	@Test
//	public void whenUpdateBooking_thenReturnBooking() {
//		// Retrieving Existing Booking Object
//		Booking b = bService.getBooking("B001002");
//		
//		// Modifying Details
//		b.setUserNumber("M0054");
//		b.setFacilityNumber("F022");
//		b.setSlotDate(LocalDate.of(2018, 6, 11));
//		b.setSlotTimeStart("1200");
//		b.setSlotTimeEnd("1500");
//		
//		outputStringToConsole(b.toString());
//		
//		// Updating To Database
//		bService.updateBooking(b);
//		
//		for (Booking bk:bRepo.findAll()) {
//			System.out.println(bk.toString());
//		}
//	}
	
//	@Test
//	public void whenRemoveBooking_thenReturnVoid() {
//		// Removing From Database
//		bService.removeBooking("B001001");
//		
//		for (Booking bk:bRepo.findAll()) {
//			System.out.println(bk.toString());
//		}
//	}
	
//	@Test
//	public void whenGetBookingListByUserNum_thenReturnArrayList() {
//		// Retrieving ArrayList
//		ArrayList<Booking> bList = bService.getBookingListByUserNum("M0035");
//		
//		for (Booking bk:bList) {
//			System.out.println(bk.toString());
//		}
//	}
//	
//	@Test
//	public void whenGetBookingListByUserNumAndDate_thenReturnArrayList() {
//		// Retrieving ArrayList
//		LocalDate d1 = LocalDate.of(2018, 6, 4);
//		LocalDate d2 = LocalDate.of(2018, 6, 13);
//		ArrayList<Booking> bList = bService.getBookingListByUserNumAndDate("M0035", d1, d2);
//		
//		outputStringToConsole(String.valueOf(bList.size()));
//		
//		for (Booking bk:bList) {
//			System.out.println(bk.toString());
//		}
//	}
	
//	@Test
//	public void whenGetBookingListSelected_thenReturnArrayList() {
//		ArrayList<Facility> fList = new ArrayList<Facility>();
//		fList.add(fService.getFacility("F023"));
//		fList.add(fService.getFacility("F018"));
//		// Retrieving ArrayList
//		ArrayList<Booking> bList1 = bService.getBookingListSelected(fList);
//		
//		outputStringToConsole(String.valueOf(bList1.size()));
//		
//		for (Booking bk:bList1) {
//			System.out.println(bk.toString());
//		}
//		
//		outputStringToConsole("Getting subList");
//		// Retrieving ArrayList
//		ArrayList<Booking> bList2 = bService.getBookingListSingle(bList1, "F023");
//		
//		outputStringToConsole(String.valueOf(bList2.size()));
//		
//		for (Booking bk:bList2) {
//			System.out.println(bk.toString());
//		}
//	}
	
//	@Test
//	public void whenGetAvailableSlots_thenReturnList() {
//		LocalDate d = LocalDate.of(2018, 6, 13);
//		String fNum = "F023";
//		List<String> availList = bService.getAvailableSlots(d, fNum);
//		
//		outputStringToConsole(String.valueOf(availList.size()));
//		
//		for (String s:availList) {
//			System.out.println(s.toString());
//		}
//	}
	
	// Utility Methods
	public void outputStringToConsole(String msg) {
		System.out.println("\n*********************************************************\n");
		System.out.println(msg.toString());
		System.out.println("\n*********************************************************\n");
	}
}
