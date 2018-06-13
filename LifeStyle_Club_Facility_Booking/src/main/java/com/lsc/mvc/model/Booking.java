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
}
