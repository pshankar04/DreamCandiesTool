package com.test.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

public class Customer {
	
	public static String code;
	public static String firstname;
	public static String lastname;
	public static String customerDetail;
	public FileWriter customerWriter;
	public static BufferedReader bufferReader = null;
	public static TreeSet<String> customerSet = new TreeSet<String>();
	

	public Customer() throws IOException {
		customerWriter = new FileWriter(Constants.EXTRACTED_CUSTOMER_FILE);
		customerWriter.append(Constants.CUSTOMER_CODE + Constants.COMMA + Constants.FIRST_NAME + Constants.COMMA + Constants.LAST_NAME + Constants.NEW_LINE);
	}
	
	/**
	 * Process the customer sample csv file and extracts the selected customer data
	 * @param custCode
	 * @throws IOException
	 */
	public void extractCustomer(String custCode) throws IOException {

		try {
			bufferReader = new BufferedReader(new FileReader(Constants.CUSTOMER_CSV));
			while ((customerDetail = bufferReader.readLine()) != null) {
				if(!customerDetail.contains(Constants.CUSTOMER_CODE)) {
					code = customerDetail.split(Constants.COMMA)[0];
					if(code.equals(custCode) && !customerSet.contains(code)) {
						customerSet.add(code);
						firstname = customerDetail.split(Constants.COMMA)[1];
						lastname = customerDetail.split(Constants.COMMA)[2];
						customerWriter.append(code + Constants.COMMA + firstname + Constants.COMMA + lastname + Constants.NEW_LINE);
					}	
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferReader != null) {
				try {
					bufferReader.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
