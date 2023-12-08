package PD;

import java.util.*;

/**
 * @Author Hank Heiselbetz
 * Register
 * Register is used to determine where at in the store orders are being taken
 * Register knows about cashDrawer and sessions
 */
public class Register {

	/**
	 * number of register
	 */
	private String number;
	
	private CashDrawer cashDrawer;

	private ArrayList<Session> sessions;

	public Boolean canDelete() {
		if(sessions.isEmpty()) {
			return true;
		}
		else return false;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Register() {
		sessions = new ArrayList<Session>();
		setCashDrawer(new CashDrawer());
		number = "";
	}

	/**
	 * 
	 * @param number
	 */
	public Register(String number) {
		this();
		this.number = number;
	}

	public String toString() {
		return number;
	}

	public CashDrawer getCashDrawer() {
		return cashDrawer;
	}

	public void setCashDrawer(CashDrawer cashDrawer) {
		this.cashDrawer = cashDrawer;
	}

	public void addSession(Session session) {
		sessions.add(session);
		
	}

}