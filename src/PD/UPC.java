package PD;

/**
 * @Author Hank Heiselbetz
 * UPC
 * This object knows about UPC and its information
 */
public class UPC {

	/**
	 * creates a UPC
	 */
	private String uPC;

	public String getUPC() {
		return this.uPC;
	}

	public void setUPC(String uPC) {
		this.uPC = uPC;
	}

	public UPC() {
		this.uPC = "";
	}

	/**
	 * 
	 * @param upc
	 */
	public UPC(String upc) {
		this.uPC = upc;
	}

	public String toString() {
		return uPC;
	}

}