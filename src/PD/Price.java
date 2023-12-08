package PD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * @Author Hank Heiselbetz
 * Price
 * Object class for price which is the amount items are sold for and more information
 */
public class Price implements Comparable<Price>{

	/**
	 * BigDecimal attribute for the numeric value of the price
	 */
	protected BigDecimal price;
	/**
	 * LocalDate attribute to store the date the price is effective
	 */
	protected LocalDate effectiveDate;
	/**
	 * Item attribute in Price object to store Item data
	 */
	
	private TreeMap<String, Item> Item;

	public BigDecimal getPrice() {
		return this.price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDate getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public TreeMap<String, Item> getItem() {
		return this.Item;
	}


	/**
	 * Constructor for price
	 * 
	 */
	public Price() {
		Item = new TreeMap<String, Item>();
		effectiveDate = LocalDate.of(1111, 1, 1);
		price = BigDecimal.valueOf(0);
	}
	
	/**
	 * constructor for price
	 * @param Price 
	 * @param currentDate 
	 */
	public Price(BigDecimal price, LocalDate currentDate) {
		this();
		this.price = price;
		this.effectiveDate = currentDate;
	}
	
	/**
	 * Operation to see if the price is effective on a certain date
	 * @param localDate
	 */
	public Boolean isEffective(LocalDate localDate) {
		return LocalDate.now().isAfter(localDate);
	}

	/**
	 * Operation to generate a string of information from the price object
	 */
	public String toString() {
		String result = "" + getPrice() + " " + effectiveDate;
		return result;
	}

	@Override
	public int compareTo(Price o) {
		return this.effectiveDate.compareTo(o.getEffectiveDate());
	}
}