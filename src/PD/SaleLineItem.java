package PD;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author Hank Heiselbetz
 * SaleLineItem
 * Object class for SaleLineItem which is an individual item on a sale
 * SaleLineItem knows about items, and sales
 */
public class SaleLineItem {

	/**
	 * Integer attribute for the quantity of the saleLineItem
	 */
	private int quantity;
	/**
	 * Item attribute in SaleLineItem object to store Item data
	 */
	private Item item;
	/**
	 * sale attribute in SaleLineItem object to store sale data
	 */
	private Sale sale;

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	public Item getItem() {
		return this.item;
	}
	
	public void setItem(Item item1) {
		this.item = item1;
	}

	public Sale getSale() {
		return this.sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}

	/**
	 * Default constructor for SaleLineItem
	 */
	public SaleLineItem() {
		quantity = 0;
		sale = null;
		item = null;
	}

	/**
	 * Constructor for SaleLineItem
	 * @param sale
	 * @param item
	 * @param quantity
	 */
	public SaleLineItem(Sale sale, Item item, int quantity) {
		this();
		this.quantity = quantity;
		this.item = item;
		this.sale = sale;
		

	}

	/**
	 * Operation to calculate the sub total after an item is entered
	 */
	public BigDecimal calcSubTotal() {
		return	item.calcAmountForDateQty(quantity);
	}

	/**
	 * Operation to calculate the tax on the item scanned
	 */
	public BigDecimal calcTax() {
		return calcSubTotal().multiply(item.getTaxRateForDate());
	}

	/**
	 * Operation to generate a string of information from the SaleLineItem object
	 */
	public String toString() {
		
		String result = item.getNumber() + " " + item.getDescription() + " " + getQuantity() + " @ $" + 
		item.getPriceForDate(LocalDate.now()) + " $" + calcSubTotal().setScale(2, RoundingMode.HALF_UP);
		return result;
		
	}

}