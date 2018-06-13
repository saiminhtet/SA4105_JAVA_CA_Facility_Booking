package com.lsc.mvc.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsc.mvc.model.Booking;
import com.lsc.mvc.model.Facility;
import com.lsc.mvc.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Resource
	private BookingRepository bRepo;
	
	@Override
	public Booking setNewBookingNum(Booking b) {
		// Getting Current Max userId
		Integer newId = bRepo.getBookingIdMax() + 1;
		
		// Assigning New userNumber
		DecimalFormat fmt = new DecimalFormat("000000");
		String prefix = "B";
		b.setBookingNumber(prefix + fmt.format(newId));
		return b;
	}
	
	@Override
	@Transactional
	public Booking addBooking(Booking b) {
		return bRepo.saveAndFlush(b);
	}
	
	@Override
	@Transactional
	public Booking getBooking(String bNum) {
		return bRepo.getBookingByBookingNumber(bNum);
	}

	@Override
	@Transactional
	public Booking updateBooking(Booking b) {
		return bRepo.saveAndFlush(b);
	}

	@Override
	@Transactional
	public Void removeBooking(String bNum) {
		bRepo.delete(getBooking(bNum));
		return null;
	}
	
	@Override
	public ArrayList<Booking> getBookingListSelected(ArrayList<Facility> facilityListSelected){
		// Create New ArrayList of Bookings
		ArrayList<Booking> bList = new ArrayList<Booking>();
		
		// Append Search Results to ArrayList
		for (Facility f:facilityListSelected) {
			bList.addAll(bRepo.getBookingListByFacilityNumber(f.getFacilityNumber()));
		}
		return bList;
	}
	@Override
	public ArrayList<Booking> getBookingListSingle(ArrayList<Booking> bookingListSelected, String fNum) {
		// Create New ArrayList of Bookings
		ArrayList<Booking> bList = new ArrayList<Booking>();
		
		// Filter Bookings By Criteria and Add to ArrayList
		for (Booking b: bookingListSelected) {
			if (b.getFacilityNumber().equals(fNum)) bList.add(b);
		}
		return bList;
	}
	
	@Override
	public List<String> getAvailableSlots(LocalDate slotDate, String fNum) {
		// Retrieve ArrayList of Bookings
		ArrayList<Booking> bList = bRepo.getBookingListByFacilityNumberAndDate(fNum, slotDate);
		
		// Create ArrayList of Possible Slots
		ArrayList<String> aList = new ArrayList<String>();
		aList.add("0900"); aList.add("1000"); aList.add("1100"); aList.add("1200"); aList.add("1300"); aList.add("1400");
		aList.add("1500"); aList.add("1600"); aList.add("1700"); aList.add("1800"); aList.add("1900"); aList.add("2000");
		
		// Convert Booking Blocks into Hourly Slots
		ArrayList<String> bStrList = new ArrayList();
		for(String str:aList)
			for(Booking b:bList) 
				if (str.compareTo(b.getSlotTimeStart()) >= 0 && str.compareTo(b.getSlotTimeEnd()) < 0) bStrList.add(str);
		
		// Add Any Possible Slot Not in Booked Slots into Output List 
		List<String> outList = new ArrayList();
		for(String str:aList)
			if (!bStrList.contains(str)) outList.add(str);
		
		// Return Output List
		return outList;
	}
	
	@Override
	public ArrayList<Booking> getBookingListByUserNumAndDate(String uNum, LocalDate dateStart, LocalDate dateEnd) {
		return bRepo.getBookingListByUserNumAndDate(uNum, dateStart, dateEnd);
	}
	
	@Override
	public ArrayList<Booking> getBookingListByUserNum(String uNum) {
		return bRepo.getBookingListByUserNum(uNum);
	}
}
