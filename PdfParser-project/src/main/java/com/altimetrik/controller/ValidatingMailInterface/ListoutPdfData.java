package com.altimetrik.controller.ValidatingMailInterface;

import java.sql.SQLException;
import java.sql.Statement;

public interface ListoutPdfData {
	
	public void listAllPdfData(Statement st) throws SQLException;
	
	public void listDataWithIndividual(String invoiceNo, Statement st) throws SQLException;
	
}
