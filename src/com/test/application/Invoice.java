package com.test.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Invoice {
	
	public static String code;
	public static String date;
	public static String amount;
	public static String invoiceCode;
	public static String invoiceDetail;
	public static BufferedReader bufferReader = null;
	public FileWriter invoiceWriter;	
	
	public Invoice() throws IOException {
		
		invoiceWriter = new FileWriter(Constants.EXTRACTED_INVOICE_FILE);
		invoiceWriter.append(Constants.CUSTOMER_CODE + Constants.COMMA + Constants.INVOICE_CODE + Constants.COMMA + Constants.AMOUNT + Constants.COMMA + Constants.NEW_LINE);	
	}
	
	/**
	 * Extracts the invoice for the selected customers from the customers sample file
	 * @param custCode
	 * @throws IOException
	 */
	public void extractInvoice(String custCode) throws IOException {

		try {		
			bufferReader = new BufferedReader(new FileReader(Constants.INVOICE_CSV));
			while ((invoiceDetail = bufferReader.readLine()) != null) {				
				if(!invoiceDetail.contains(Constants.CUSTOMER_CODE)) {
					code = invoiceDetail.split(Constants.COMMA)[0];
					if(code.equals(custCode)) {
						invoiceCode = invoiceDetail.split(Constants.COMMA)[1];
						amount = invoiceDetail.split(Constants.COMMA)[2];
						date = invoiceDetail.split(Constants.COMMA)[3];
						System.out.println(code+" : "+invoiceCode+" : "+amount+" : "+date);
						invoiceWriter.append(code + Constants.COMMA + invoiceCode + Constants.COMMA + amount + Constants.COMMA + date + Constants.NEW_LINE);
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
