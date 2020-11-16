package com.test.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExtractCustomerInvoice {

	public static BufferedReader bufferedReader = null;
		 
	public static void main(String[] args) throws IOException {
		
		String customer_code;
		String invoice_item;
		String invoiceCode;
		
		Customer customer = new Customer();
		Invoice invoice = new Invoice();
		InvoiceItem invoiceItem = new InvoiceItem();
		
		try {
			bufferedReader = new BufferedReader(new FileReader(Constants.CUSTOMER_SAMPLE_CSV));
			while ((customer_code = bufferedReader.readLine()) != null) {
				if(!customer_code.contains(Constants.CUSTOMER_CODE)) {
					customer.extractCustomer(customer_code);
					invoice.extractInvoice(customer_code);
				}
			}

			bufferedReader = new BufferedReader(new FileReader(Constants.INVOICE_ITEM_CSV));
			while ((invoice_item = bufferedReader.readLine()) != null) {
				if(!invoice_item.contains(Constants.INVOICE_CODE)) {
					invoiceCode = invoice_item.split(",")[0];
					invoiceItem.extractInvoiceItem(invoiceCode);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
					customer.customerWriter.flush();
					customer.customerWriter.close();
					invoice.invoiceWriter.flush();
					invoice.invoiceWriter.close();
					invoiceItem.invoiceItemWriter.flush();
					invoiceItem.invoiceItemWriter.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
