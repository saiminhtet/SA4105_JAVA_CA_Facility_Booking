package com.lsc.mvc.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsc.mvc.exception.BookingNotFound;
import com.lsc.mvc.exception.FacilityNotFound;
import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.javabeans.FacilityUsage;
import com.lsc.mvc.model.Booking;
import com.lsc.mvc.model.Facility;
import com.lsc.mvc.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Resource
	private BookingRepository bRepo;
	@Resource
	private FacilityService fService;
	@Resource
	private UserService uService;
	
	@Override
	public Booking setNewBookingNum(Booking b) throws BookingNotFound {
		if (b == null) throw new BookingNotFound("Booking object provided cannot be null");
		
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
	public Booking addBooking(Booking b) throws BookingNotFound {
		if (b == null) throw new BookingNotFound("Booking object provided cannot be null");
		return bRepo.saveAndFlush(b);
	}
	
	@Override
	@Transactional
	public Booking getBooking(String bNum) throws BookingNotFound {
		Booking b = bRepo.getBookingByBookingNumber(bNum);
		if (b == null) throw new BookingNotFound("Booking number provided is invalid");
		else return b;
	}

	@Override
	@Transactional
	public Booking updateBooking(Booking b) throws BookingNotFound {
		if (b == null) throw new BookingNotFound("Booking object provided cannot be null");
		return bRepo.saveAndFlush(b);
	}

	@Override
	@Transactional
	public Void removeBooking(String bNum) throws BookingNotFound {
		if (getBooking(bNum)==null) throw new BookingNotFound("Booking number provided is invalid");
		bRepo.delete(getBooking(bNum));
		return null;
	}
	
	@Override
	public ArrayList<Booking> getBookingListSelected(ArrayList<Facility> facilityListSelected) throws ResourceDefinitionInvalid {
		if (facilityListSelected.size() == 0) throw new ResourceDefinitionInvalid("Facility List provided is empty");
		
		// Create New ArrayList of Bookings
		ArrayList<Booking> bList = new ArrayList<Booking>();
		
		// Append Search Results to ArrayList
		for (Facility f:facilityListSelected) {
			bList.addAll(bRepo.getBookingListByFacilityNumber(f.getFacilityNumber()));
		}
	
		return bList;
	}
	@Override
	public ArrayList<Booking> getBookingListSingle(ArrayList<Booking> bookingListSelected, String fNum) throws ResourceDefinitionInvalid, FacilityNotFound {
		if (bookingListSelected.size() == 0) throw new ResourceDefinitionInvalid("Booking List provided is empty");
		if (fService.getFacility(fNum)==null) throw new FacilityNotFound("Facility number provided is invalid");
		
		// Create New ArrayList of Bookings
		ArrayList<Booking> bList = new ArrayList<Booking>();
		
		// Filter Bookings By Criteria and Add to ArrayList
		for (Booking b: bookingListSelected) {
			if (b.getFacilityNumber().equals(fNum)) bList.add(b);
		}
		
		return bList;
	}
	
	@Override
	public List<String> getAvailableSlots(LocalDate slotDate, String fNum) throws FacilityNotFound {
		if (fService.getFacility(fNum)==null) throw new FacilityNotFound("Facility number provided is invalid");
		
		// Retrieve ArrayList of Bookings
		ArrayList<Booking> bList = bRepo.getBookingListByFacilityNumberAndDate(fNum, slotDate);
		
		// Create ArrayList of Possible Slots
		ArrayList<String> aList = new ArrayList<String>();
		aList.add("0900"); aList.add("1000"); aList.add("1100"); aList.add("1200"); aList.add("1300"); aList.add("1400");
		aList.add("1500"); aList.add("1600"); aList.add("1700"); aList.add("1800"); aList.add("1900"); aList.add("2000");
		
		// Create ArrayList for Output
		List<String> outList = new ArrayList();
		
		if (bList.size() > 0) {
			// Convert Booking Blocks into Hourly Slots
			ArrayList<String> bStrList = new ArrayList();
			for(String str:aList)
				for(Booking b:bList) 
					if (str.compareTo(b.getSlotTimeStart()) >= 0 && str.compareTo(b.getSlotTimeEnd()) < 0) bStrList.add(str);
			
			// Add Any Possible Slot Not in Booked Slots into Output List 
			for(String str:aList)
				if (!bStrList.contains(str)) outList.add(str);
		} else {
			outList = aList;
		}
		
		// Return Output List
		return outList;
	}
	
	@Override
	public ArrayList<Booking> getBookingListByUserNumAndDate(String uNum, LocalDate dateStart, LocalDate dateEnd) throws UserNotFound {
		if (uService.getUser(uNum)==null) throw new UserNotFound("User number provided is invalid");
		return bRepo.getBookingListByUserNumAndDate(uNum, dateStart, dateEnd);
	}
	
	@Override
	public ArrayList<Booking> getBookingListByUserNum(String uNum) throws UserNotFound {
		if (uService.getUser(uNum)==null) throw new UserNotFound("User number provided is invalid");
		return bRepo.getBookingListByUserNum(uNum);
	}
	
	@Override
	public FacilityUsage getFacilityUsageByFNumAndDate(String fNum, LocalDate dStart, LocalDate dEnd) throws FacilityNotFound, BookingNotFound {
		Facility f = fService.getFacility(fNum);
		if (f == null) throw new FacilityNotFound("Facility number provided is invalid");
		
		double numSlots = getSlotNumBetweenDates(dStart, dEnd);
		ArrayList<Booking> bList = bRepo.getBookingListByFNumAndDate(fNum, dStart, dEnd);
		
		double numHr = 0;
		for (Booking b: bList) numHr += getHourNumForBooking(b);
		
		FacilityUsage fUsage = new FacilityUsage(f.getFacilityNumber(), f.getFacilityType(), f.getFacilityName(), (numHr / numSlots));
		return fUsage;
	}
	
	public List<FacilityUsage> getFacilityUsageListByFacilityListAndDate(List<Facility> fList, LocalDate dStart, LocalDate dEnd) throws FacilityNotFound, BookingNotFound {
		List<FacilityUsage> fUsageList = new ArrayList<FacilityUsage>();
		for (Facility f: fList) {
			fUsageList.add(getFacilityUsageByFNumAndDate(f.getFacilityNumber(), dStart, dEnd));
		}
		return fUsageList;
	}
	
	// Utility Methods
	public double getSlotNumBetweenDates(LocalDate dStart, LocalDate dEnd) {
		double numDays = (double) (dStart.until(dEnd, ChronoUnit.DAYS));
		return (numDays + 1) * 12;
	}
	
	public double getHourNumForBooking(Booking b) throws BookingNotFound {
		if (b == null) throw new BookingNotFound("Booking object provided cannot be null");
		String strStart = b.getSlotTimeStart();
		String strEnd = b.getSlotTimeEnd();
		double dblStart = Double.parseDouble(strStart);
		double dblEnd = Double.parseDouble(strEnd);
		return (dblEnd-dblStart)/100;
	}	
}
