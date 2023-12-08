package UI;

import PD.Store;

/**
 * @Author Hank Heiselbetz
 * POSStart
 * This allows the UI to start
 */
public class POSStart {
	public static void main(String[] args) {
		Store myStore = new Store();
		myStore.loadStore();
		POSjFrame.open (myStore);
	}
}