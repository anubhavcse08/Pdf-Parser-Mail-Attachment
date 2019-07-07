package com.altimetrik.service;

import com.altimetrik.Main;
import com.altimetrik.constant.HelperData;

public class ReadingPdfData {
	public String invoiceNo;
	public String invoiceDate;
	public String customerPO;
	public String address;
	public String totalInvoice;

	public String findInvoiceNo(String invoiceNum) {
		if (Main.text.contains(invoiceNum)) {
			invoiceNo = Main.text.substring(Main.text.indexOf(invoiceNum) + invoiceNum.length(),
					Main.text.indexOf(HelperData.invoiceDate)).trim();
			return invoiceNo;
		} else
			return HelperData.invoiceNumNotFound;
	}

	public String findInvoiceDate(String invoiceDateStr) {
		if (Main.text.contains(invoiceDateStr)) {
			invoiceDate = Main.text.substring(Main.text.indexOf(invoiceDateStr) + invoiceDateStr.length(),
					Main.text.indexOf(invoiceDateStr) + invoiceDateStr.length() + 10).trim();
			return invoiceDate;
		} else
			return HelperData.invoiceDateNotFound;
	}

	public String findCustomerPO(String customerPOStr) {
		if (Main.text.contains(customerPOStr)) {
			customerPO = Main.text.substring(Main.text.indexOf(customerPOStr) + customerPOStr.length(),
					Main.text.indexOf("Account No")).trim();
			return customerPO;
		} else
			return HelperData.customerPONotFound;
	}

	public String findAddress(String addressStr) {
		if (Main.text.contains(addressStr)) {
			address = Main.text.substring(Main.text.indexOf(addressStr) + addressStr.length(),
					Main.text.indexOf("Remit To")).trim();
			return address;
		} else
			return HelperData.addressNotFoundForShipping;
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
			return HelperData.totalInvoiceNotFound;
	}

}
