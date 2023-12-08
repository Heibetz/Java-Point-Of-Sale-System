package DM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import PD.*;

/**
 * @author Hank Heiselbetz
 * posDM
 * Adds data to the store.
 */

public class posDM {

		public static void loadStore(Store store) {
			
			String fileName = "StoreData_v2023FA.csv";
			String buffer = null;
			String dataType = null;
			Session session = new Session();
			Sale sale = new Sale();
			
			try {
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				while((buffer = bufferedReader.readLine()) != null) {
					String[] token = buffer.split(",");
					dataType = token[0];
					
					if(dataType.contentEquals("Store")) {
						store.setName(token[1]);
					}
					
					if(dataType.contentEquals("TaxCategory")) {
						TaxCategory taxCategory = new TaxCategory(token[1]);
						TaxRate taxRate = new TaxRate(LocalDate.parse(token[3], DateTimeFormatter.ofPattern("M/d/yyyy")), 
								new BigDecimal(token[2]));
						taxCategory.addTaxRate(taxRate);
						store.addTaxCategory(taxCategory);
					}
					
					if(dataType.contentEquals("Cashier")) {
						//name address city state zip phone ssn
						Person person = new Person(token[2],token[4],token[5],token[6],token[7],token[8],token[3]);
						Cashier cashier = new Cashier(token[1],person,token[9]);
						store.addCashier(cashier);
					}
					
					if(dataType.contentEquals("Item")) {
						//number and description			
						Item item = new Item(token[1],token[3],store.findTaxCategoryByName(token[4]));
						UPC upc = new UPC(token[2]);						
						
						Price price = new Price(new BigDecimal(token[5]),
								LocalDate.parse(token[6], DateTimeFormatter.ofPattern("M/d/yyyy")));
						
						
						if(token.length > 7) {
						PromoPrice promoprice = new PromoPrice(new BigDecimal(token[7]), 
								LocalDate.parse(token[8], DateTimeFormatter.ofPattern("M/d/yyyy")),
								 			LocalDate.parse(token[9], DateTimeFormatter.ofPattern("M/d/yyyy")));
							if(promoprice.isEffective(LocalDate.parse(token[8], DateTimeFormatter.ofPattern("M/d/yyyy")),
						 			LocalDate.parse(token[9], DateTimeFormatter.ofPattern("M/d/yyyy")))) {
									item.addPrice(promoprice);
							}
						}
						
						
						
						item.addUPC(upc);
						item.addPrice(price);
						store.addItem(item);
					}
					
					if(dataType.contentEquals("Register")) {
						//number
						Register register = new Register(token[1]);
						store.addRegister(register);
						
					}
					
					
					/*
					 * if(dataType.contentEquals("Sale")) { sale = new Sale();
					 * sale.setDateTime(LocalDateTime.now()); if(token[1].equals("Y")) {
					 * sale.setTaxFree(true); } else{ sale.setTaxFree(false); };
					 * session.addSales(sale); }
					 * 
					 * if(dataType.contentEquals("SaleLineItem")) { //sale, item, quantity
					 * SaleLineItem sli = new
					 * SaleLineItem(sale,store.findItemForNumber(token[1]),Integer.parseInt(token[2]
					 * )); sale.addSaleLineItem(sli); }
					 */
					
					if(dataType.contentEquals("Session")) {
						session = new Session();
						//cashierNumber, registerNumber
						session.setStartDateAndTime(LocalDateTime.now());			
						session.setEndDateTIme(LocalDateTime.now());
						session.setCashier(store.findCashierForNumber(token[1]));
						store.findCashierForNumber(token[1]).addSession(session);
						session.setRegister(store.findRegisterByNumber(token[2]));
						store.findRegisterByNumber(token[2]).addSession(session);
						
						store.addSession(session);
					}
					
					if(dataType.contentEquals("Payment")) {
						if(token[1].contentEquals("Cash")) {
							Cash cash = new Cash(new BigDecimal(token[2]), new BigDecimal(token[3]));
							sale.addPayment(cash);
						}
						else if(token[1].contentEquals("Credit")) {
							//amount actNumber expireDate
							Credit credit = new Credit(new BigDecimal(token[2]), token[5], token[6]);	
							credit.setAmtTendered(new BigDecimal(token[3]));
							sale.addPayment(credit);
						}
						else if(token[1].contentEquals("Check")) {
							//String amount, String accountNumber, String routingNumber, String checkNumber, BigDec amtTendered
							Check check = new Check(new BigDecimal(token[2]),token[5],token[4],token[6], new BigDecimal(token[3]));
							sale.addPayment(check);
						}
					}
					
				}
				
				bufferedReader.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
}
