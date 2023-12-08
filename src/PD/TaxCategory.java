package PD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * @Author Hank Heiselbetz
 * TaxCategory
 * Object class for tax category and all of its information
 * Knows about taxRates
 */
public class TaxCategory {

	/**
	 * String Attribute for the name of the tax category
	 */
	private String category;
	/**
	 * taxRate attribute in the TaxCategory class to store taxRate data
	 */
	private TreeSet<TaxRate> taxRates;

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public TreeSet<TaxRate> getTaxRates() {
		return this.taxRates;
	}

	/**
	 * Default constructor for TaxCategory
	 */
	public TaxCategory() {
		
		taxRates = new TreeSet<TaxRate>();
	}

	/**
	 * Constructor for TaxCateogory
	 * @param category
	 */
	public TaxCategory(String category) {
		this();
		this.category = category;
	}

	/**
	 * Operation for getting the tax rate based on the date
	 * @param date
	 */
	public TaxRate getTaxRateForDate(LocalDate date) {
		TaxRate effectiveRate = new TaxRate();
		for(TaxRate rate: taxRates) {
			if(rate.isEffectiveDate(date)){
				effectiveRate = rate;
			}
		}
		return effectiveRate;
	}

	/**
	 * Operation for adding a TaxRate object to the TaxCategory
	 * @param taxRate
	 */
	public void addTaxRate(TaxRate taxRate) {
		if(taxRate != null) {
		getTaxRates().add(taxRate);
		}
	}
	
	public Boolean canDeleteTaxCategory(Store store) {
		if(store.getTaxCategories().isEmpty()) {
			return false;
		}
		else return true;
	}

	/**
	 * Operation for removing a TaxRate object from the TaxCategory Object
	 * @param treeSet
	 */
	public void removeTaxRate(TaxRate taxRate) {
		taxRates.remove(taxRate);
	}
	
	/**
	 * Operation to generate a String of information from the TaxCategory object
	 */
	public String toString() {

		return category;
	}
}