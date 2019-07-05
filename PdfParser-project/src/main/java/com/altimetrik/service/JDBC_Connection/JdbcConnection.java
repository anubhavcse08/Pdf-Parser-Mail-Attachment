package com.altimetrik.service.JDBC_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.altimetrik.constant.Data;

public class JdbcConnection {

	public static Statement connectDB(String invoiceNo, String invoiceDate, String customerPO, String address,
			String totalInvoice) throws SQLException {
		Statement st = null;
		Connection con = null;
		try {
			Class.forName(Data.oracleJDBC);
			try {
				con = DriverManager.getConnection(Data.dbURL, Data.dbUserName, Data.dbPassword);
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

				st.executeUpdate("delete from PDFDATA where INVOICENO='" + invoiceNo + "'");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return st;
	}

}
