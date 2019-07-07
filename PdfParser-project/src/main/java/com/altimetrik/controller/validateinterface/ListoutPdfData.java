package com.altimetrik.controller.validateinterface;

import java.sql.SQLException;
import java.sql.Statement;

public interface ListoutPdfData {
	
	public void listAllPdfData(Statement st) throws SQLException;
	
	public void listDataWithIndividual(String invoiceNo, Statement st) throws SQLException;
	
}
