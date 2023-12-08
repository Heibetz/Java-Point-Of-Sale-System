package PD;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Author Hank Heiselbetz
 * PromoPrice
 * Price object subclass promoPrice, 
 * which are items that may be sold for cheaper depending on the date
 */
public class PromoPrice extends Price {

	/**
	 * LocalDate attribute to store the end date of the promotional price
	 */
	private LocalDate endDate;

	public LocalDate getEndDate() {
		return this.endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * Default constructor for PromoPrice object
	 */
	public PromoPrice() {
		
	}

	/**
	 * Constructor for PromoPrice
	 * @param price
	 * @param effectiveDate
	 * @param endDate
	 * @param price 
	 * @param effectiveDate 
	 */
	public PromoPrice(BigDecimal price, LocalDate effectiveDate, LocalDate endDate) 
	{
		this.endDate = endDate;
		this.price = price;
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Operation to see if the promotional price is effective on a certain date
	 * @param date
	 */
	public Boolean isEffective(LocalDate date, LocalDate date1) {
		if(LocalDate.now().isAfter(date) && LocalDate.now().isBefore(date1)) {
			return true;
		}
		return false;
	}

	/**
	 * Operation to generate a string of information from the PromoPrice object
	 */
	public String toString() {
		String result = " " + getPrice() + " " + effectiveDate + " " + endDate;
		return result;
	}

	public void addPrice(BigDecimal price) {
		this.price = price;
		
	}

}