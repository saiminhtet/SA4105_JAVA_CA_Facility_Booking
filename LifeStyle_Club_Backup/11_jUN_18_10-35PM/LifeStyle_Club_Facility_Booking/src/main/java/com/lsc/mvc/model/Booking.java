package com.lsc.mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="booking")
public class Booking {
	
	// Entity Mapping
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Integer bookingId;
	
	@NotBlank
	@Column(name = "booking_number")
	protected String bookingNumber;
	
	@NotBlank
	@Column(name = "user_number")
	protected String userNumber;
	
	@NotBlank
	@Column(name = "facility_number")
	protected String facilityNumber;
	
	@Column(name = "trans_datetime")
	protected LocalDateTime transDateTime;
	
	@Column(name = "slot_date")
	protected LocalDate slotDate;
	
	@NotBlank
	@Column(name = "slot_timestart")
	protected String slotTimeStart;
	
	@NotBlank
	@Column(name = "slot_timeend")
	protected String slotTimeEnd;
	
	// Getters and Setters
	public Integer getBookingId() { return bookingId; }
	public void setBookingId(Integer bookingId) { this.bookingId = bookingId; }
	
	public String getBookingNumber() { return bookingNumber; }
	public void setBookingNumber(String bookingNumber) { this.bookingNumber = bookingNumber; }
	
	public String getUserNumber() { return userNumber; }
	public void setUserNumber(String userNumber) { this.userNumber = userNumber; }
	
	public String getFacilityNumber() { return facilityNumber; }
	public void setFacilityNumber(String facilityNumber) { this.facilityNumber = facilityNumber; }
	
	public LocalDateTime getTransDateTime() { return transDateTime; }
	public void setTransDateTime(LocalDateTime transDateTime) { this.transDateTime = transDateTime; }
	
	public LocalDate getSlotDate() { return slotDate; }
	public void setSlotDate(LocalDate slotDate) { this.slotDate = slotDate; }
	
	public String getSlotTimeStart() { return slotTimeStart; }
	public void setSlotTimeStart(String slotTimeStart) { this.slotTimeStart = slotTimeStart; }
	
	public String getSlotTimeEnd() { return slotTimeEnd; }
	public void setSlotTimeEnd(String slotTimeEnd) { this.slotTimeEnd = slotTimeEnd; }	
		
	// Constructors
	public Booking() { }
	public Booking(@NotBlank String userNumber, @NotBlank String facilityNumber, 
			LocalDate slotDate, @NotBlank String slotTimeStart, @NotBlank String slotTimeEnd) {
		super();
		this.userNumber = userNumber;
		this.facilityNumber = facilityNumber;
		this.transDateTime = LocalDateTime.now();
		this.slotDate = slotDate;
		this.slotTimeStart = slotTimeStart;
		this.slotTimeEnd = slotTimeEnd;
	}
		
	// Methods	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingId == null) ? 0 : bookingId.hashCode());
		result = prime * result + ((bookingNumber == null) ? 0 : bookingNumber.hashCode());
		result = prime * result + ((facilityNumber == null) ? 0 : facilityNumber.hashCode());
		result = prime * result + ((slotDate == null) ? 0 : slotDate.hashCode());
		result = prime * result + ((slotTimeEnd == null) ? 0 : slotTimeEnd.hashCode());
		result = prime * result + ((slotTimeStart == null) ? 0 : slotTimeStart.hashCode());
		result = prime * result + ((transDateTime == null) ? 0 : transDateTime.hashCode());
		result = prime * result + ((userNumber == null) ? 0 : userNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (bookingId == null) {
			if (other.bookingId != null)
				return false;
		} else if (!bookingId.equals(other.bookingId))
			return false;
		if (bookingNumber == null) {
			if (other.bookingNumber != null)
				return false;
		} else if (!bookingNumber.equals(other.bookingNumber))
			return false;
		return true;
	}	
		
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", "
				+ "bookingNumber=" + bookingNumber + ", "
				+ "userNumber=" + userNumber + ", "
				+ "facilityNumber=" + facilityNumber + ", "
				+ "transDateTime=" + transDateTime + ", "
				+ "slotDate=" + slotDate + ", "
				+ "slotTimeStart=" + slotTimeStart + ", "
				+ "slotTimeEnd=" + slotTimeEnd + "]";
	}
	
//	public static ArrayList<Booking> getBookingListSelected
//	    (ArrayList<Facility> facilityListSelected) {
//		// Dummy code: 4 Booking objects hard-coded and added to 1 ArrayList<Booking>,
//		// then returned
//		
//		// Example Implementation: 
//		// ArrayList<Booking> bList = getBookingListSelected(facilityListSelected);
//		
//		// Initialize ArrayList<Booking>
//		ArrayList<Booking> bList = new ArrayList<Booking>();
//				
//		// For each facility in facilityListSelected
//		// Gets Booking objects based on facNum
//		// and add Booking objects to ArrayList<Booking>
//		
//		Booking b1 = new Booking();
//		b1.bookingNumber = "B000001";
//		b1.userNumber = "M0002";
//		b1.facilityNumber = "F03";
//		b1.transDateTime = LocalDateTime.of(2018, 6, 6, 14, 00);
//		b1.slotDate = LocalDate.of(2018, 6, 8);
//		b1.slotTimeStart = "1200";
//		b1.slotTimeEnd = "1400";
//		bList.add(b1);
//		
//		Booking b2 = new Booking();
//		b2.bookingNumber = "B000002";
//		b2.userNumber = "M0005";
//		b2.facilityNumber = "F01";
//		b2.transDateTime = LocalDateTime.of(2018, 6, 7, 16, 00);
//		b2.slotDate = LocalDate.of(2018, 6, 10);
//		b2.slotTimeStart = "1600";
//		b2.slotTimeEnd = "1800";
//		bList.add(b2);
//		
//		Booking b3 = new Booking();
//		b3.bookingNumber = "B000003";
//		b3.userNumber = "M0002";
//		b3.facilityNumber = "F01";
//		b3.transDateTime = LocalDateTime.of(2018, 6, 8, 18, 00);
//		b3.slotDate = LocalDate.of(2018, 6, 11);
//		b3.slotTimeStart = "2000";
//		b3.slotTimeEnd = "2100";
//		bList.add(b3);
//		
//		Booking b4 = new Booking();
//		b4.bookingNumber = "B000004";
//		b4.userNumber = "M0003";
//		b4.facilityNumber = "F02";
//		b4.transDateTime = LocalDateTime.of(2018, 6, 9, 20, 00);
//		b4.slotDate = LocalDate.of(2018, 6, 11);
//		b4.slotTimeStart = "1200";
//		b4.slotTimeEnd = "1400";
//		bList.add(b4);
//		
//		return bList;
//	}
//	
//	public static ArrayList<Booking> getBookingListSingle
//	    (ArrayList<Booking> bookingListSelected,
//	     String facNum) {
//		// Dummy code: 4 Booking objects hard-coded and added to 1 ArrayList<Booking>,
//		// then returned
//		
//		// Example Implementation: 
//		// ArrayList<Booking> bList = getBookingListSingle(bookingListSelected, facNum);
//		
//		// Initialize ArrayList<Booking>
//		ArrayList<Booking> bList = new ArrayList<Booking>();
//				
//		// Gets Booking objects based on facNum from bookingListSelected
//		// and add Booking objects to ArrayList<Booking>
//		
//		Booking b1 = new Booking();
//		b1.bookingNumber = "B000001";
//		b1.userNumber = "M0002";
//		b1.facilityNumber = "F03";
//		b1.transDateTime = LocalDateTime.of(2018, 6, 6, 14, 00);
//		b1.slotDate = LocalDate.of(2018, 6, 8);
//		b1.slotTimeStart = "1200";
//		b1.slotTimeEnd = "1400";
//		bList.add(b1);
//		
//		Booking b2 = new Booking();
//		b2.bookingNumber = "B000002";
//		b2.userNumber = "M0005";
//		b2.facilityNumber = "F01";
//		b2.transDateTime = LocalDateTime.of(2018, 6, 7, 16, 00);
//		b2.slotDate = LocalDate.of(2018, 6, 10);
//		b2.slotTimeStart = "1600";
//		b2.slotTimeEnd = "1800";
//		bList.add(b2);
//		
//		Booking b3 = new Booking();
//		b3.bookingNumber = "B000003";
//		b3.userNumber = "M0002";
//		b3.facilityNumber = "F01";
//		b3.transDateTime = LocalDateTime.of(2018, 6, 8, 18, 00);
//		b3.slotDate = LocalDate.of(2018, 6, 11);
//		b3.slotTimeStart = "2000";
//		b3.slotTimeEnd = "2100";
//		bList.add(b3);
//		
//		Booking b4 = new Booking();
//		b4.bookingNumber = "B000004";
//		b4.userNumber = "M0003";
//		b4.facilityNumber = "F02";
//		b4.transDateTime = LocalDateTime.of(2018, 6, 9, 20, 00);
//		b4.slotDate = LocalDate.of(2018, 6, 11);
//		b4.slotTimeStart = "1200";
//		b4.slotTimeEnd = "1400";
//		bList.add(b4);
//		
//		return bList;	
//	}
//	public static Booking getBooking(String bookingNum) {
//		// Dummy code: 1 Booking object hard-coded and returned
//		
//		// Example Implementation: 
//		// Booking b = getBooking(bookingNum);
//				
//		// Gets Booking object based on bookingNum
//		
//		Booking b4 = new Booking();
//		b4.bookingNumber = "B000004";
//		b4.userNumber = "M0003";
//		b4.facilityNumber = "F02";
//		b4.transDateTime = LocalDateTime.of(2018, 6, 9, 20, 00);
//		b4.slotDate = LocalDate.of(2018, 6, 11);
//		b4.slotTimeStart = "1200";
//		b4.slotTimeEnd = "1400";
//		
//		return b4;	
//	}
//	public static String updateBooking
//	    (String bookingNum, String userNum, String facNum, 
//	     LocalDate slotDate, String timeStart, String timeEnd) {
//		// Dummy code: return success
//		// If there is any error at any point, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(updateBooking
//	    //		(bookingNum, userNum, facNum, 
//	   	//		slotDate, timeStart, timeEnd) != "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Checks that all required fields provided
//		
//		// Validate bookingNum
//		
//		// Validate userNum
//		
//		// Validate facNum
//		
//		// Validate slotDate 
//		
//		return err;
//	}
//	public static String addBooking
//	    (String userNum, String facNum, 
//	     LocalDate slotDate, String timeStart, String timeEnd) {
//		// Dummy code: return success
//		// If there is any error at any point, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(addBooking
//	    //		(userNum, facNum, 
//	   	//		slotDate, timeStart, timeEnd) != "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Checks that all required fields provided
//		
//		// Validate userNum
//		
//		// Validate facNum
//		
//		// Validate slotDate 
//		
//		return err;	
//	}
//	public static String removeBooking
//	    (String bookingNum) {
//		// Dummy code: return success
//		// If there is any error at any point, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(removeBooking(bookingNum) != "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Validate bookingNum
//		
//		// Checks that bookingNum exists in database
//		
//		// Find Booking object
//		
//		// Remove Booking object
//		
//		return err;	
//	}
//	public static ArrayList<Booking> getBookingListByUserNum
//	    (String userNum, LocalDate dateStart, LocalDate dateEnd) {
//		// Dummy code: 4 Booking objects hard-coded and added to 1 ArrayList<Booking>,
//		// then returned
//		
//		// Example Implementation: 
//		// ArrayList<Booking> bList = getBookingListByUserNum(userNum, dateStart, dateEnd);
//		
//		// Initialize ArrayList<Booking>
//		ArrayList<Booking> bList = new ArrayList<Booking>();
//				
//		// Gets Booking objects based on userNum
//		// and add Booking objects to ArrayList<Booking>
//		
//		Booking b1 = new Booking();
//		b1.bookingNumber = "B000001";
//		b1.userNumber = "M0002";
//		b1.facilityNumber = "F03";
//		b1.transDateTime = LocalDateTime.of(2018, 6, 6, 14, 00);
//		b1.slotDate = LocalDate.of(2018, 6, 8);
//		b1.slotTimeStart = "1200";
//		b1.slotTimeEnd = "1400";
//		bList.add(b1);
//		
//		Booking b2 = new Booking();
//		b2.bookingNumber = "B000002";
//		b2.userNumber = "M0002";
//		b2.facilityNumber = "F01";
//		b2.transDateTime = LocalDateTime.of(2018, 6, 7, 16, 00);
//		b2.slotDate = LocalDate.of(2018, 6, 10);
//		b2.slotTimeStart = "1600";
//		b2.slotTimeEnd = "1800";
//		bList.add(b2);
//		
//		Booking b3 = new Booking();
//		b3.bookingNumber = "B000003";
//		b3.userNumber = "M0002";
//		b3.facilityNumber = "F01";
//		b3.transDateTime = LocalDateTime.of(2018, 6, 8, 18, 00);
//		b3.slotDate = LocalDate.of(2018, 6, 11);
//		b3.slotTimeStart = "2000";
//		b3.slotTimeEnd = "2100";
//		bList.add(b3);
//		
//		Booking b4 = new Booking();
//		b4.bookingNumber = "B000004";
//		b4.userNumber = "M0002";
//		b4.facilityNumber = "F02";
//		b4.transDateTime = LocalDateTime.of(2018, 6, 9, 20, 00);
//		b4.slotDate = LocalDate.of(2018, 6, 11);
//		b4.slotTimeStart = "1200";
//		b4.slotTimeEnd = "1400";
//		bList.add(b4);
//		
//		return bList;		
//	}
}
