package com.test.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InvoiceItem {

	
	public static String code;
	public static String amount;
	public static String quantity;
	public static String itemCode;
	public static String invoice_item;
	public static String invoiceDetail;
	public FileWriter invoiceItemWriter;	
	public static BufferedReader bufferReader = null;
	
	
	public InvoiceItem() throws IOException {
		
		invoiceItemWriter = new FileWriter(Constants.EXTRACTED_INVOICE_ITEM_FILE);
		invoiceItemWriter.append(Constants.INVOICE_CODE + Constants.COMMA + Constants.ITEM_CODE + Constants.COMMA + Constants.AMOUNT + Constants.COMMA + Constants.QUANTITY + Constants.NEW_LINE);	
	}
	
	/**
	 * Extracts the invoice items for the selected customers from the customers sample csv file
	 * @param invCode
	 * @throws IOException
	 */
	public void extractInvoiceItem(String invCode) throws IOException {

		try {
			bufferReader = new BufferedReader(new FileReader(Constants.INVOICE_ITEM_CSV));
			while ((invoiceDetail = bufferReader.readLine()) != null) {				
				if(!invoiceDetail.contains(Constants.INVOICE_CODE)) {
					code = invoiceDetail.split(Constants.COMMA)[0];
					if(code.equals(invCode)) {
						itemCode = invoiceDetail.split(Constants.COMMA)[1];
						amount = invoiceDetail.split(Constants.COMMA)[2];
						quantity = invoiceDetail.split(Constants.COMMA)[3];
						System.out.println(code+" : " + itemCode + " : " + amount + " : " + quantity);
						invoiceItemWriter.append(code + Constants.COMMA + itemCode + Constants.COMMA + amount  + Constants.COMMA + quantity + Constants.NEW_LINE);
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
