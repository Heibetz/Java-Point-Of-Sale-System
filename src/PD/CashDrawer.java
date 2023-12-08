package PD;

import java.math.BigDecimal;

/**
 * @Author Hank Heiselbetz
 * CashDrawer
 * This Object is used to determine how much 
 * money is inside the cash drawer
 */
public class CashDrawer {

	/**
	 * amount of cash in drawer
	 */
	private BigDecimal cashAmount;
	/**
	 * gives us what position
	 */
	private int position;
	
	public BigDecimal getCashAmount() {
		return this.cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public CashDrawer() {
		cashAmount = new BigDecimal(0);
		position = 0;
	}

	
	public CashDrawer(BigDecimal cashAmount1, int position)
	{
		this.cashAmount = cashAmount1;
		this.position = position;
	}
	/**
	 * 
	 * @param cash
	 */
	public void removeCash(BigDecimal cash) {
		cashAmount = cashAmount.subtract(cash);
	}

	/**
	 * 
	 * @param cash
	 */
	public void addCash(BigDecimal cash) {
		cashAmount = cashAmount.add(cash);
	}

	public String toString() {
		return null;
	}

}