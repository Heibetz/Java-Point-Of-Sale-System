package PD;

/**
 * @Author Hank Heiselbetz
 * AuthorizedPayment
 * This Object extends payment, and makes sure that 
 * the user is paying with authorized money.
 */
public class AuthorizedPayment extends Payment {

	private String authorizationCode;

	public String getAuthorizationCode() {
		return this.authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public Boolean isAuthorized() {
		if(authorizationCode.equals("Y")) {
			return true;
		}
		else return false;
	}

	public Boolean countAsCash() {
		if(countAsCash()) {
			return true;
		}
		else return false;
	}

}