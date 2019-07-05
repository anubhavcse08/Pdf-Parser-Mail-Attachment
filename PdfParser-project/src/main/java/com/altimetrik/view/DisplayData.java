package com.altimetrik.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.altimetrik.controller.ValidatingMailInterface.ListoutPdfData;

public class DisplayData implements ListoutPdfData {

	public void listAllPdfData(Statement st) throws SQLException {
		ResultSet rs = st.executeQuery("Select * from PDFDATA");
		if (!rs.isBeforeFirst()) {
			System.out.println("Data is not available...");
		}
		while (rs.next()) {
			System.out.println("Invoice No: " + rs.getString(1) + "\n\nInvoice Date: " + rs.getString(2)
					+ "\n\nCustomer PO: " + rs.getString(3) + "\n\nAddress: \n" + rs.getString(4)
					+ "\n\nTotal Invoice: " + rs.getString(5) + "\n\nStatus: " + rs.getString(6) + "\n");
			System.out.println("----------------------------------");
		}

	}

	public void listDataWithIndividual(String invoiceNo, Statement st) throws SQLException {
		ResultSet rs = st.executeQuery("Select * from PDFDATA where INVOICENO = '" + invoiceNo + "'");
		if (!rs.isBeforeFirst()) {
			System.out.println("Invoice no. is not available...");
		}
		while (rs.next())
			System.out.println("Invoice No: " + rs.getString(1) + "\n\nInvoice Date: " + rs.getString(2)
					+ "\n\nCustomer PO: " + rs.getString(3) + "\n\nAddress: \n" + rs.getString(4)
					+ "\n\nTotal Invoice: " + rs.getString(5) + "\n\nStatus: " + rs.getString(6));

	}

}
