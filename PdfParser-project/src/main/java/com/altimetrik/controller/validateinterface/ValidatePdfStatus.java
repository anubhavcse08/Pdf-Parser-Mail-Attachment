package com.altimetrik.controller.validateinterface;

import java.sql.SQLException;
import java.sql.Statement;

public interface ValidatePdfStatus {
	
	public boolean validateSatusAndUpdate(Statement st, String invoiceNo, String status) throws SQLException;

}
