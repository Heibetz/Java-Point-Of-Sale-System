package PD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * @Author Hank Heiselbetz
 * Item
 * Object class for an Item, which is what is sold in the store
 * Item knows about a price, tax category, and a UPC
 */
public class Item {

	/**
	 * String data type for the items identification number
	 */
	private String number;
	/**
	 * String attribute for the description of the item
	 */
	private String description;
	/**
	 * TaxCategory attribute in Item object to store TaxCategory data
	 */
	private TaxCategory taxCategory;
	/**
	 * UPC attribute in Item object to store UPC data
	 */
	private TreeMap<String, UPC> upcs;
	/**
	 * saleLineItem attribute in Item object to store SaleLineItem data
	 */
	private ArrayList<SaleLineItem> saleLineItems;
	/**
	 * Price attribute in Item object to store price data
	 */
	private TreeSet<Price> prices;
	
	private int timesSold;

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaxCategory getTaxCategory() {
		return this.taxCategory;
	}
	public void setTaxCategory(TaxCategory taxCategory) {
		this.taxCategory = taxCategory;
	}

	public TreeMap<String, UPC> getUpc() {
		return this.upcs;
	}

	public ArrayList<SaleLineItem> getSaleLineItems() {
		return this.saleLineItems;
	}

	public TreeSet<Price> getPrices() {
		return this.prices;
	}

	/**
	 * Default constructor for the item object
	 */
	public Item() {
		prices = new TreeSet<Price>();
		saleLineItems = new ArrayList<SaleLineItem>();
		upcs = new TreeMap<String, UPC>();
		timesSold = 0;
	}

	/**
	 * Constructor for the item object
	 * @param number
	 * @param description
	 * @param taxCategory
	 */
	public Item(String number, String description, TaxCategory taxCategory) {
		this();
		this.number = number;
		this.description = description;
		this.taxCategory = taxCategory;

	}
	/**
	 * Operation to add a price to an item
	 * @param price
	 */
	public void addPrice(Price price) {
		if(price != null) {
			getPrices().add(price);
		}
	}

	/**
	 * Operation to remove a price from an item
	 * @param price
	 */
	public void removePrice(Price price) {
		prices.remove(price);
	}

	/**
	 * Operation to add a UPC to an item
	 * @param upc
	 */
	public void addUPC(UPC upc) {
		if(upc != null) {
				upcs.put(upc.getUPC(),upc);
		}
	}

	/**
	 * Operation to remove a UPC from an item
	 * @param upc
	 */
	public void removeUPC(UPC upc) {
		upcs.remove(upc.getUPC(), upc);
	}

	/**
	 * Operation to get the price of an item on a certain date
	 * @param localDate
	 */
	public Price getPriceForDate(LocalDate localDate) {
		Price effectivePrice = new Price();
		for(Price price : prices) {
				effectivePrice = price;
			}
		return effectivePrice;
	}

	/**
	 * Operation to calculate the total amount of the item based on price for date and quantity
	 * @param localDate
	 * @param quantity
	 */
	public BigDecimal calcAmountForDateQty(int quantity) {	
		return this.getPriceForDate(LocalDate.now()).getPrice().multiply(new BigDecimal(quantity));
		
	}

	/**
	 * Operation to get the tax rate for the item on a certain date
	 * @param date
	 */
	public BigDecimal getTaxRateForDate() {
		return this.getTaxCategory().getTaxRateForDate(LocalDate.now()).getTaxRate();
	}



	/**
	 * Operation to add a saleLineItem to the item
	 * @param saleLineItem
	 */
	public void addSaleLineItem(SaleLineItem saleLineItem) {
			if(saleLineItem != null) {
				addSaleLineItem(saleLineItem);
			}
	}

	/**
	 * Operation to remove a saleLineItem from the item
	 * @param saleLineItem
	 */
	public void removeSaleLineItem(SaleLineItem saleLineItem) {
		if(saleLineItem != null) {
			removeSaleLineItem(saleLineItem);
		}

	}
	/**
	 * Operation to generate a string of information for the item object
	 */
	public String toString() {
		
		Price price = new Price();
		for(Price p : getPrices()) {
			price = p;
		}
		String result = "" + getNumber() + " " + getDescription() + " " + price.getPrice() + 
				" " + taxCategory.getTaxRates().first().getTaxRate();
		return result;
	}

	public int getTimesSold() {
		return timesSold;
	}

	public void setTimesSold(int timesSold) {
		this.timesSold = timesSold;
	}
	
	public void addTimesSold(int t) {
		timesSold+= t;
	}
}