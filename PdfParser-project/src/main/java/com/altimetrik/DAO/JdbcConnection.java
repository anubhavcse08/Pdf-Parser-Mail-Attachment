package com.altimetrik.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.altimetrik.constant.HelperData;

public class JdbcConnection {
	public static Connection con = null;
	public static Statement connectDB(String invoiceNo, String invoiceDate, String customerPO, String address,
			String totalInvoice) throws SQLException {
		Statement st = null;
		
		try {
			Class.forName(HelperData.oracleJDBC);
			try {
				con = DriverManager.getConnection(HelperData.dbURL, HelperData.dbUserName, HelperData.dbPassword);
				st = con.createStatement();

				String squery = "INSERT INTO PDFDATA(INVOICENO, INVOICEDATE, CUSTOMERPO, ADDRESS, TOTALINVOICE, STATUS, EMAILTO) VALUES('"
						+ invoiceNo + "','" + invoiceDate + "','" + customerPO + "','" + address + "','" + totalInvoice
						+ "'," + null + "," + null + ")";

				 st.executeQuery(squery);
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			con.close();
		}
		return st;
	}

}
