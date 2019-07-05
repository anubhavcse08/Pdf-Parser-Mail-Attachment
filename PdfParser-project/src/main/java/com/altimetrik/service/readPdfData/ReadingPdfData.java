package com.altimetrik.service.readPdfData;

import com.altimetrik.Main;
import com.altimetrik.constant.Data;

public class ReadingPdfData {
	public String invoiceNo;
	public String invoiceDate;
	public String customerPO;
	public String address;
	public String totalInvoice;

	public String findInvoiceNo(String invoiceNum) {
		if (Main.text.contains(invoiceNum)) {
			invoiceNo = Main.text.substring(Main.text.indexOf(invoiceNum) + invoiceNum.length(),
					Main.text.indexOf(Data.invoiceDate)).trim();
			return invoiceNo;
		} else
			return Data.invoiceNumNotFound;
	}

	public String findInvoiceDate(String invoiceDateStr) {
		if (Main.text.contains(invoiceDateStr)) {
			invoiceDate = Main.text.substring(Main.text.indexOf(invoiceDateStr) + invoiceDateStr.length(),
					Main.text.indexOf(invoiceDateStr) + invoiceDateStr.length() + 10).trim();
			return invoiceDate;
		} else
			return Data.invoiceDateNotFound;
	}

	public String findCustomerPO(String customerPOStr) {
		if (Main.text.contains(customerPOStr)) {
			customerPO = Main.text.substring(Main.text.indexOf(customerPOStr) + customerPOStr.length(),
					Main.text.indexOf("Account No")).trim();
			return customerPO;
		} else
			return Data.customerPONotFound;
	}

	public String findAddress(String addressStr) {
		if (Main.text.contains(addressStr)) {
			address = Main.text.substring(Main.text.indexOf(addressStr) + addressStr.length(),
					Main.text.indexOf("Remit To")).trim();
			return address;
		} else
			return Data.addressNotFoundForShipping;
	}

	public String findTotalInvoice(String totalInvoiceStr) {
		if (Main.text.contains(totalInvoiceStr)) {
			for (int i = Main.text.indexOf(totalInvoiceStr); i > 0; i--) {
				if (Main.text.charAt(i) == '$') {
					totalInvoice = Main.text.substring(i, Main.text.indexOf(totalInvoiceStr)).trim();
					break;
				}
			}
			return totalInvoice;
		} else
			return Data.totalInvoiceNotFound;
	}

}
