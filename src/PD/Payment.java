package PD;

import java.math.BigDecimal;

/**
 * @author Hank Heiselbetz
 * Payment
 * Object class for payment, which is what customers use to pay for their items
 * Knows about cash, credit, check
 */
public class Payment {

	/**
	 * BigDecimal attribute for the numeric value of the payment amount
	 */
	protected BigDecimal amount;
	/**
	 * BigDecimal attribute for the amount tendered
	 */
	protected BigDecimal amtTendered;
	
	private BigDecimal checkTotal;
	
	private BigDecimal cashTotal;
	
	private BigDecimal creditTotal;

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmtTendered() {
		return this.amtTendered;
	}

	public void setAmtTendered(BigDecimal amtTendered) {
		this.amtTendered = amtTendered;
	}

	public BigDecimal getCheckTotal() {
		return checkTotal;
	}

	public void setCheckTotal(BigDecimal checkTotal) {
		this.checkTotal = checkTotal;
	}

	public BigDecimal getCashTotal() {
		return cashTotal;
	}

	public void setCashTotal(BigDecimal cashTotal) {
		this.cashTotal = cashTotal;
	}

	public BigDecimal getCreditTotal() {
		return creditTotal;
	}

	public void setCreditTotal(BigDecimal creditTotal) {
		this.creditTotal = creditTotal;
	}
	
	public void addCreditTotal(BigDecimal total) {
		this.creditTotal.add(total);
	}
	
	public void addCashTotal(BigDecimal total) {
		this.cashTotal.add(total);
	}
	
	public void addCheckTotal(BigDecimal total) {
		this.checkTotal.add(total);
	}
	
	public Payment() {
		amount = new BigDecimal(0);
		amtTendered = new BigDecimal(0);
		cashTotal = new BigDecimal(0);
		creditTotal = new BigDecimal(0);
		checkTotal = new BigDecimal(0);
	}

}