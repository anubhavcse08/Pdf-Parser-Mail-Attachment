package com.altimetrik.service;

import java.sql.SQLException;
import java.sql.Statement;

import com.altimetrik.controller.ReceiveEmailWithAttachment;
import com.altimetrik.controller.validateinterface.ValidatePdfStatus;

public class ApprovingMail implements ValidatePdfStatus {
	String toEmail = ReceiveEmailWithAttachment.emailTo != null
			? ReceiveEmailWithAttachment.emailTo.substring(ReceiveEmailWithAttachment.emailTo.indexOf('<') + 1,
					ReceiveEmailWithAttachment.emailTo.indexOf('>'))
			: null;

	@Override
	public boolean validateSatusAndUpdate(Statement st, String invoiceNo, String status) throws SQLException {
		boolean check = false;
		if (status.equals("approved")) {
			st.executeUpdate("UPDATE PDFDATA SET STATUS = 'approved', EMAILTO = '" + toEmail + "' WHERE INVOICENO = '"
					+ invoiceNo + "'");
			check = true;
		} else if (status.equals("rejected")) {
			st.executeUpdate("UPDATE PDFDATA SET STATUS = 'rejected', EMAILTO = '" + toEmail + "' WHERE INVOICENO = '"
					+ invoiceNo + "'");
			check = false;
		}

		return check;
	}

}
