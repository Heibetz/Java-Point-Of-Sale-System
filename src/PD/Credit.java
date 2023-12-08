package PD;

import java.math.BigDecimal;

/**
 * @Author Hank Heiselbetz
 * Credit
 * Authorized payment object subclass for credit payments
 */
public class Credit extends AuthorizedPayment {

	/**
	 * String attribute for the card type of the credit card
	 */
	private String cardType;
	/**
	 * String attribute for the account number of the credit card
	 */
	private String acctNumber;
	/**
	 * String attribute for the expiration date of the credit card
	 */
	private String expireDate;

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getAcctNumber() {
		return this.acctNumber;
	}

	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}

	public String getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * Default constructor for credit card
	 */
	public Credit() {}

	/**
	 * Constructor for credit card
	 * @param amount
	 * @param acctNumber
	 * @param expireDate
	 */
	public Credit(BigDecimal amount, String acctNumber, String expireDate) {
		this.amount = amount;
		this.acctNumber = acctNumber;
		this.expireDate = expireDate;
	}

	/**
	 * Operation to see if the customer is is authorized to use the credit card
	 */
	public Boolean isAuthorized() {
		return true;
	}

	/**
	 * Operation to generate a string of information from the credit card object
	 */
	public String toString() {
		String result = "" + getAmount();
		return result;
		
	}

}