package PD;

/**
 * @author Hank Heiselbetz
 * Person
 * This Object creates a person that works for the store
 */
public class Person {

	/**
	 * names the person
	 */
	private String name;
	/**
	 * gives address to person
	 */
	private String address;
	/**
	 * says the city of person
	 */
	private String city;
	/**
	 * tells us the state
	 */
	private String state;
	/**
	 * zip code of person
	 */
	private String zip;
	/**
	 * phone number of person
	 */
	private String phone;
	/**
	 * Social Security number
	 */
	private String sSN;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSSN() {
		return this.sSN;
	}

	public void setSSN(String sSN) {
		this.sSN = sSN;
	}

	public Person() {
	}

	/**
	 * 
	 * @param name
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @param phone
	 * @param sSN
	 */
	public Person(String name, String address, String city, String state, String zip, String phone, String sSN) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.sSN = sSN;
	}

	public String toString() {
		return name + ": number: " + phone + " from: " + city;
	}

}