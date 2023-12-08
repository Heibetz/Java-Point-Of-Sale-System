package PD;

import java.util.*;

/**
 * @Author Hank Heiselbetz
 * Cashier
 * This Object is used to create a cashier
 * This cashier knows about, person
 */
public class Cashier {

	/**
	 * number of the cashier
	 */
	private String number;
	/**
	 * password for cashier to sign in
	 */
	private String password;
	private Person person;
	private ArrayList<Session> sessions;

	
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Cashier() {
		sessions = new ArrayList<Session>();
		number = "";
		
	}

	/**
	 * 
	 * @param number
	 * @param person
	 * @param password
	 */
	public Cashier(String number, Person person, String password) {
		this();
		this.number = number;
		this.person = person;
		this.password = password;
	}

	/**
	 * 
	 * @param session
	 */
	public void addSession(Session session) {
		sessions.add(session);
	}

	private ArrayList<Session> getSessions() {
		return this.sessions;
	}

	/**
	 * 
	 * @param password
	 */
	public Boolean isAuthorized(String password) {
		// TODO - implement Cashier.isAuthorized
		throw new UnsupportedOperationException();
	}

	public String toString() {
		return number + " " + person.getName();
	}

	public String getName() {
		return person.getName();
	}

	public void addPerson(Person person2) {
		this.person = person2;
	}
	
	public Boolean canDeleteCashier() {
		if(sessions.isEmpty()) {
			return true;
		}
		else return false;
	}

}