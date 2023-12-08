package PD;

import java.math.BigDecimal;

/**
 * @Author Hank Heiselbetz
 * Payment object cash for payment
 */
public class Cash extends Payment {
	
	public Cash() {}

	/**
	 * Constructor for cash
	 * @param amount
	 * @param amtTendered
	 */
	public Cash(BigDecimal amount, BigDecimal amtTendered) {
		this.amount = amount;
		this.amtTendered = amtTendered;
	}

	/**
	 * Operation to see if the payment counts as cash
	 */
	public Boolean countsAsCash() {
		return true;
	}

	/**
	 * Operation to generate a string of information from the cash object
	 */
	public String toString() {
		String result = "" + getAmount();
		return result;
	}

}