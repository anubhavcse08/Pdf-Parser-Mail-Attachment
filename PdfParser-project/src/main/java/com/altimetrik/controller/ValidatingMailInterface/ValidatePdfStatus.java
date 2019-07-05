package com.altimetrik.controller.ValidatingMailInterface;

import java.sql.SQLException;
import java.sql.Statement;

public interface ValidatePdfStatus {
	
	public boolean validateSatusAndUpdate(Statement st, String invoiceNo, String status) throws SQLException;

}
