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

				// String query = "INSERT INTO PDFDATA( , INVOICEDATE,
				// CUSTOMERPO, ADDRESS, TOTALINVOICE, STATUS, EMAILTO)
				// VALUES(?,?,?,?,?,?,?)";
				String squery = "INSERT INTO PDFDATA(INVOICENO, INVOICEDATE, CUSTOMERPO, ADDRESS, TOTALINVOICE, STATUS, EMAILTO) VALUES('"
						+ invoiceNo + "','" + invoiceDate + "','" + customerPO + "','" + address + "','" + totalInvoice
						+ "'," + null + "," + null + ")";

				 st.executeQuery(squery);
				//
				// PreparedStatement ps = con.prepareStatement(query);
				// ps.setString(1, invoiceNo);
				// ps.setString(2, invoiceDate);
				// ps.setString(3, customerPO);
				// ps.setString(4, address);
				// ps.setString(5, totalInvoice);
				// ps.setString(6, null);
				// ps.setString(7, null);
				// ps.executeUpdate();

//				st.executeUpdate("delete from PDFDATA where INVOICENO='" + invoiceNo + "'");

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
