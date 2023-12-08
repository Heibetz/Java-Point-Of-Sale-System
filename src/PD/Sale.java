package PD;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author Hank Heiselbetz
 * Sale
 * Object class for a sale, which is what happens when an item or items are sold
 * Sale knows about payments, and saleLineItems
 */
public class Sale {

	/**
	 * LocalDateTime attribute to record the time of sale
	 */
	private LocalDateTime dateTime;
	/**
	 * Boolean attribute for whether or not the sale is tax free
	 */
	private Boolean taxFree;
	/**
	 * Payment attribute in sale object to store payment data
	 */
	private ArrayList<Payment> payments;
	/**
	 * saleLineItem attribute in sale object to store saleLineItem data
	 */
	private ArrayList<SaleLineItem> saleLineItems;

	public LocalDateTime getDateTime() {
		return this.dateTime;			
	}

	public void setDateTime(LocalDateTime dateTime) {			
		this.dateTime = dateTime;						
	}

	public Boolean getTaxFree() {
		return this.taxFree;
	}

	public void setTaxFree(Boolean taxFree) {
		this.taxFree = taxFree;
	}

	public ArrayList<Payment> getPayments() {
		return this.payments;
	}

	public ArrayList<SaleLineItem> getSaleLineItems() {
		return this.saleLineItems;
	}

	/**
	 * Default constructor for sale object
	 */
	public Sale() {
		payments = new ArrayList<Payment>();
		saleLineItems = new ArrayList<SaleLineItem>();
		taxFree = false;
		dateTime = LocalDateTime.now();
	}

	/**
	 * Constructor for the sale object
	 * @param taxFree
	 */
	public Sale(Boolean taxFree, LocalDateTime dateTime) {
		this();
		this.taxFree = taxFree;
		this.dateTime = dateTime; 
	}

	/**
	 * Operation to add a type of payment to a sale
	 * @param payment
	 */
	public void addPayment(Payment payment) {
		if(payment != null) {
			getPayments().add(payment);
		}
	}

	/**
	 * Operation to remove a form of payment from the sale
	 * @param payment
	 */
	public void removePayment(Payment payment) {
		if(payment != null) {
			getPayments().remove(payment);
		}
	}

	/**
	 * Operation to add a SaleLineItem to the sale
	 * @param saleLineItem
	 */
	public void addSaleLineItem(SaleLineItem saleLineItem) {
		if(saleLineItem != null) {
			getSaleLineItems().add(saleLineItem);
		}
	}	

	/**
	 * Operation to remove a SaleLineItem from the sale
	 * @param saleLineItem
	 */
	public void removeSaleLineItem(SaleLineItem saleLineItem) {
		if(saleLineItem != null) {
			getSaleLineItems().remove(saleLineItem);
		}
	}

	/**
	 * Operation to calculate the total dollar amount of the sale
	 */
	public BigDecimal calcTotal() {
		BigDecimal result = new BigDecimal("0");
		result = this.calcSubTotal().add(this.calcTax());
		result = result.setScale(2, RoundingMode.HALF_UP);
		return result;
	}

	/**
	 * Operation to calculate the subtotal after each item is entered
	 */
	public BigDecimal calcSubTotal() {
		BigDecimal result =  new BigDecimal("0");
		for(SaleLineItem sli: saleLineItems) {
			result = result.add(sli.calcSubTotal());
		}
		return result;
	}

	/**
	 * Operation to calculate the total amount of sales tax
	 */
	public BigDecimal calcTax() {
		BigDecimal result = new BigDecimal("0");
		if(getTaxFree().equals(true)) {
			return result;
		}
		for(SaleLineItem sli: saleLineItems) {
			result = result.add(sli.calcTax());
		}
		return result;
	}

	/**
	 * Operation to get the amount payed
	 */
	public BigDecimal getTotalPayments() {
		BigDecimal result = new BigDecimal(0);
		for(Payment payment : getPayments()) {
			result = result.add(payment.getAmtTendered());
		}
		return result;
	}

	/**
	 * Operation to see if the payment is equal or greater to the total
	 */
	public Boolean isPaymentEnough() {
		
		if(getTotalPayments().equals(calcTotal()) || getTotalPayments().compareTo(calcTotal()) > 0) {
			return true;
		}
		else
			return false;
			
	}

	/**
	 * Operation to calculate the total amount of the payments
	 * @param amtTendered
	 */
	public void calcAmountForPayment(BigDecimal amtTendered) {
		
	}

	/**
	 * Operation to calculate the amount of change the payment produced
	 */
	public BigDecimal calcChange() {
		BigDecimal result = new BigDecimal(0);
		for(int i = 0; i < getPayments().size(); i++) {
			result = (getPayments().get(i).getAmtTendered()).subtract(calcTotal());
		}
		return result;
	}
	

	public BigDecimal calcAmtTendered() {
		BigDecimal result = new BigDecimal(0);
		for(int i = 0; i < getPayments().size(); i++) {
			result =  getPayments().get(i).getAmtTendered();
		}
		return result;
	}

	/**
	 * Operation to generate  a string of information from the sale object
	 */
	public String toString() {
		
		String result = " Sale: " +  "Subtotal= $" + calcSubTotal() + " Tax= $" + calcTax().setScale(2, RoundingMode.HALF_UP) +
						" Total: $" + calcTotal().setScale(2, RoundingMode.HALF_UP) ;
		
		for(int i = 0; i < getPayments().size(); i++) {
			result += " Payment: $" +getPayments().get(i).getAmtTendered() + " Change: $" + calcChange();
		}
		for(SaleLineItem saleLineItem : getSaleLineItems()) {
			result += " \n     " + saleLineItem.toString();
		}
		
		return result;
	}

}