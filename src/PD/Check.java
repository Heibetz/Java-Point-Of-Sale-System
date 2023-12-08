package PD;

import java.math.BigDecimal;

/**
 * @Author Hank Heiselbetz
 * Check
 * Authorized payment subclass for check payments
 */
public class Check extends AuthorizedPayment {

	/**
	 * String attribute for the checks routing number
	 */
	private String routingNumber;
	/**
	 * String attribute for the checks accountNumber
	 */
	private String accountNumber;
	/**
	 * String attribute for the check number
	 */
	private String checkNumber;

	public String getRoutingNumber() {
		return this.routingNumber;
	}

	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCheckNumber() {
		return this.checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	/**
	 * Default constructor for check
	 */
	public Check() {}

	/**
	 * Constructor for check
	 * @param amount
	 * @param accountNumber
	 */
	public Check(BigDecimal amount, String accountNumber, String routingNumber, String checkNumber, BigDecimal amtTendered) {
		 this.amount = amount;
		 this.accountNumber = accountNumber;
		 this.routingNumber = routingNumber;
		 this.checkNumber = checkNumber;
		 this.amtTendered = amtTendered;
	}

	/**
	 * Operation to see if the customer is authorized to use the check
	 */
	public Boolean isAuthorized() {return true;}

	/**
	 * Operation to generate a string of information from the check object
	 */
	public String toString() {
	 String result = "" + getAmount();
	 return result;
	}

}