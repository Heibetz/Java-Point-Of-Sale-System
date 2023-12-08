package PD;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author Hank Heiselbetz
 * Session
 * Object Class for a session which keeps track of transactions a cashier makes
 * Session knows about sales, cashier, and register
 */
public class Session {

	/**
	 * LocalDateTime attribute to track the start of the session
	 */
	private LocalDateTime startDateAndTime;
	/**
	 * LocalDateTime attribute to record the end time of the session
	 */
	private LocalDateTime endDateTIme;
	/**
	 * sale attribute in session to store sale data
	 */
	private ArrayList<Sale> sales;
	/**
	 * cashier attribute in session to store cashier data
	 */
	private Cashier cashier;
	/**
	 * Register attribute in session to store register data
	 */
	private Register register;
	
	private int totalSales;
	
	private BigDecimal diff;

	public LocalDateTime getStartDateAndTime() {
		return this.startDateAndTime;
	}

	public void setStartDateAndTime(LocalDateTime startDateAndTime) {
		this.startDateAndTime = startDateAndTime;
	}

	public LocalDateTime getEndDateTIme() {
		return this.endDateTIme;
	}

	public void setEndDateTIme(LocalDateTime endDateTIme) {
		this.endDateTIme = endDateTIme;
	}

	public ArrayList<Sale> getSales() {
		return this.sales;
	}

	public Cashier getCashier() {
		return this.cashier;
	}

	public Register getRegister() {
		return this.register;
	}
	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}
	public void setRegister(Register register) {
		this.register = register;
	}

	/**
	 * Default constructor for session
	 */
	public Session() {
		sales = new ArrayList<Sale>();
		register = new Register();
		cashier = new Cashier();
		totalSales = 0;
		startDateAndTime = LocalDateTime.now();
		diff = new BigDecimal(0);
	}

	/**
	 * Constructor for session
	 * @param cashier
	 * @param register
	 */
	public Session(Cashier cashier, Register register, LocalDateTime startDateAndTime, LocalDateTime endDateTime) {
		this();
		this.cashier = cashier;
		this.register = register;
		this.startDateAndTime = startDateAndTime;
		this.endDateTIme = endDateTime;
	}

	/**
	 * Operation to add a sale object to the session
	 * @param sale
	 */
	public void addSales(Sale sale) {
		if(sale != null) {
			getSales().add(sale);
		}
	}

	/**
	 * Operation to remove a sale from the session object
	 * @param sale
	 */
	public void removeSales(Sale sale) {
			getSales().remove(sale);
	}
	/**
	 * Operation to calculate the total sale amount during the session
	 */
	public BigDecimal calcTotal() {
		BigDecimal result =  new BigDecimal("0");
		for(Sale sale: sales) {
			result = result.add(sale.calcTotal());
		}
		return result;
	}

	/**
	 * Operation to generate a string of information from the session object
	 */
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M-d-yyyy");

		String result = "\nSession: " + "Cashier: " + getCashier() + " Register: " 
		+ getRegister() + " " + startDateAndTime.format(dtf) +  " Total= $" + calcTotal().setScale(2, RoundingMode.HALF_UP);
		
		for(Sale sle : getSales()) {
			result += "\n " + sle.toString();
		}
		
		return result;
	}

	public int getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}
	
	public void addSale(int sale) {
		totalSales += sale;
	}

	
	public void setDiff(BigDecimal diff) {
		this.diff = diff;
	}
	public BigDecimal getDiff() {
		return diff;
	}

}