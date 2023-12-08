package PD;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Author Hank Heiselbetz
 * TaxRate
 * Object class for tax rates and all of its information
 */
public class TaxRate implements Comparable<TaxRate> {

	/**
	 * BigDecimal attribute for the numeric value of the tax rate
	 */
	private BigDecimal taxRate;
	/**
	 * LocalDate attribute for the dates that the tax rate is effective
	 */
	private LocalDate effectiveDate;

	public BigDecimal getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public LocalDate getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Default constructor for TaxRate object
	 */
	public TaxRate() {
		this.taxRate = BigDecimal.valueOf(0.0);
		this.effectiveDate = LocalDate.of(1011, 1, 1);
	}

	/**
	 * Constructor for TaxRate object
	 * @param effectiveDate
	 * @param rate
	 */
	public TaxRate(LocalDate effectiveDate, BigDecimal rate) {
		this.effectiveDate = effectiveDate;
		this.taxRate = rate;
	}

	/**
	 * Operation that returns a boolean value on whether the effective date is today
	 * @param date
	 */
	public boolean isEffectiveDate(LocalDate date) {
		if(effectiveDate.isBefore(date) || effectiveDate.isEqual(date)) {
			return true;
		}
		return false;
	}

	/**
	 * Operation to compare the tax rate
	 * @param taxRate
	 */
	public int compareTo(TaxRate taxRate) {
		
		return this.effectiveDate.compareTo(taxRate.getEffectiveDate());

	}

	/**
	 * Operation to generate a string of information from the TaxRate object
	 */
	public String toString() {
		String result = effectiveDate + " " + getTaxRate();
		return result;
	}



}