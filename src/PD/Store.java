package PD;

import java.time.LocalDate;
import java.util.*;

import DM.posDM;

/**
 * @Author Hank Heiselbetz
 * Store
 * Object class for the store and all of its information
 * Store is the main class that knows about cashiers, taxCategories,
 * UPCs, sessions, registers, and items
 */
public class Store {

	/**
	 * Attribute containing the store's identification number in string format
	 */
	private String number;
	/**
	 * The name of the store in string format
	 */
	private String name;
	/**
	 * cashier attribute in the store object to store cashier data
	 */
	private TreeMap<String, Cashier> cashiers;
	/**
	 * tax category attribute in the store object to store tax category data
	 */
	private TreeMap<String, TaxCategory> taxCategories;
	/**
	 * UPC attribute in the store object to store UPC data
	 */
	private TreeMap<String, UPC> upcs;
	/**
	 * Session attribute to store session data inside the store object
	 */
	private ArrayList<Session> sessions;
	/**
	 * Register attribute in the store object to store register data
	 */
	private TreeMap<String, Register> registers;
	/**
	 * Item attribute in the store object to store item data
	 */
	private TreeMap<String, Item> items;

	public TreeMap<String, Cashier> getCashiers() {
		return this.cashiers;
	}

	public TreeMap<String, TaxCategory> getTaxCategories() {
		return this.taxCategories;
	}

	public TreeMap<String, UPC> getUpc() {
		return this.upcs;
	}

	public ArrayList<Session> getSessions() {
		return this.sessions;
	}

	public TreeMap<String, Register> getRegisters() {
		return this.registers;
	}

	public TreeMap<String, Item> getItems() {
		return this.items;
	}

	/**
	 * The default constructor for the store object
	 */
	public Store() {
		cashiers = new TreeMap<String, Cashier>();
		taxCategories = new TreeMap<String, TaxCategory>();
		registers = new TreeMap<String, Register>();
		items = new TreeMap<String, Item>();
		upcs = new TreeMap<String, UPC>();
		sessions = new ArrayList<Session>();
		//set collections
	}

	/**
	 * Constructor for the store object
	 * @param number
	 * @param name
	 */
	public Store(String number, String name) {
		this();
		this.number = number;
		this.name = name;
	}

	/**
	 * Operation to add another item object to the store
	 * @param item
	 */
	public void addItem(Item item) {
		if(item != null) {
		getItems().put(item.getNumber(), item);
		}
		
	}

	/**
	 * Operation to remove an item object from the store
	 * @param item
	 */
	public void removeItem(Item item) {
		if(item != null) {
			items.remove(item.getNumber(), item);
		}
	}

	/**
	 * Operation to add another UPC object to the store
	 * @param upc
	 */
	public void addUPC(UPC upc) {
		if(upc != null) {
			getUpc().put(upc.getUPC(), upc);
		}
	}

	/**
	 * Operation to remove a UPC object from the store
	 * @param upc
	 */
	public void removeUPC(UPC upc) {
		upcs.remove(upc.getUPC(), upc);
	}

	/**
	 * Operation to add another register object to the store
	 * @param register
	 */
	public void addRegister(Register register) {
		if(register != null) {
			getRegisters().put(register.getNumber(), register);
		}
	}

	/**
	 * Operation to remove a register object from the store
	 * @param register
	 */
	public void removeRegister(Register register) {
		registers.remove(register.getNumber(), register);
	}

	/**
	 * Operation to add another cashier object to the store
	 * @param cashier
	 */
	public void addCashier(Cashier cashier) {
		if(cashier != null) {
			getCashiers().put(cashier.getNumber(), cashier);
		}
	}

	public Boolean canDelete() {
		if(sessions.isEmpty()) {
			return true;
		}
		else return false;
	}
	
	/**
	 * Operation to remove a cashier object from the store
	 * @param cashier
	 */
	public void removeCashier(Cashier cashier) {
		cashiers.remove(cashier.getNumber(), cashier);
	}

	/**
	 * Operation to add a TaxCategory object to the store
	 * @param taxCategory
	 */
	public void addTaxCategory(TaxCategory taxCategory) {
		taxCategories.put(taxCategory.getCategory(), taxCategory);
	}

	/**
	 * Operation to remove a TaxCategory object from the store
	 * @param taxCategory
	 */
	public void removeTaxCategory(TaxCategory taxCategory) {
		taxCategories.remove(taxCategory.getCategory(), taxCategory);
	}

	/**
	 * Operation to add a Session object to the store
	 * @param session
	 */
	public void addSession(Session session) {
			if(session != null) {
				getSessions().add(session);
			}
	}

	/**
	 * Operation to remove a session object from the store
	 * @param session
	 */
	public void removeSession(Session session) {
		if(session != null) {
			getSessions().remove(session);
		}
	}
	
	public Session getSessionForDateAndCashier(LocalDate localDate, Cashier cashier) {
		Session session = null;
		for(Session s : getSessions()) {
			if(s.getEndDateTIme().toLocalDate().equals(localDate) && s.getCashier().equals(cashier)) {
				session = s;
			}
		}
		if(session == null)
			return new Session();
		return session;
	}
	

	/**
	 * Operation to search for a register object by its identification number
	 * @param number
	 */
	public Register findRegisterByNumber(String number) {
		Register result = null;
		for(Register register : registers.values()) {
			if(register.getNumber().equals(number)) {
				result = register;
			}
		}
		return result;

	}

	/**
	 * Operation to search for an item by its UPC association
	 * @param upc
	 */
	public Item findItemForUPC(String UPC) {
		Item result = null;
		for(Item item : items.values()) {
			if(item.getUpc().firstKey().equals(UPC)) {
				result = item;
			}
		}
		return result;
	}

	/**
	 * Operation to find an item from its identification number
	 * @param number
	 */
	public Item findItemForNumber(String number) {
		Item result = null;
		for( Item item : items.values()) {
			if(item.getNumber().equals(number)) {
				result = item;
			}
		}
		
		if(result == null) {
			return null;
		}
		
		return result;
	}

	/**
	 * Operation to find a cashier from its identification number
	 * @param number
	 */
	public Cashier findCashierForNumber(String number) {
		Cashier result = null;
		for(Cashier cashier : cashiers.values()) {
			if(cashier.getNumber().equals(number)) {
				result = cashier;
			}
		}
		return result;

	}

	/**
	 * Operation to find a TaxCategory by its name
	 * @param category
	 */
	public TaxCategory findTaxCategoryByName(String category) {
			TaxCategory result = null;
			for( TaxCategory taxCategory : taxCategories.values()) {
				if(taxCategory.getCategory().equals(category)) {
					result = taxCategory;
				}
			}
		
		return result;
	}
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Operation to generate a string of information from the store object
	 */
	public String toString() {
		String result = name;
		return result;
	}


	public void loadStore() {
		posDM.loadStore(this);
	}

}