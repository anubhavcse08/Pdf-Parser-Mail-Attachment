package com.altimetrik;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.altimetrik.DAO.JdbcConnection;
import com.altimetrik.constant.HelperData;
import com.altimetrik.controller.ReceiveEmailWithAttachment;
import com.altimetrik.controller.SendingApprovalMail;
import com.altimetrik.controller.validateinterface.ListoutPdfData;
import com.altimetrik.controller.validateinterface.ValidatePdfStatus;
import com.altimetrik.service.ApprovingMail;
import com.altimetrik.service.ReadingPdfData;
import com.altimetrik.service.ReadingPdfText;
import com.altimetrik.view.ViewData;

public class Main {
	public static String text;

	public static void main(String args[]) throws IOException, SQLException {
		boolean flag = false;
		Statement st = null;
		String pop3Host = HelperData.pop3Host;
		String mailStoreType = HelperData.mailStoreType;
		final String userName = HelperData.userName;
		final String password = HelperData.password;

		ListoutPdfData displayData = new ViewData();
		ValidatePdfStatus validateStatus = new ApprovingMail();
		Scanner sc = new Scanner(System.in);
		int option = 0;
		do {
			System.out.println("PDF MENU\n========");
			System.out.println("1.Read the attachment from mail : ");
			if (flag) {
				System.out.println("2.PDF Details : ");
				System.out.println("3.Search by invoice no. : ");
				System.out.println("4.Check approvable : ");
			}
			System.out.println("5.Exit : ");
			option = sc.nextInt();
			switch (option) {
			case 1:
				System.out.println("Reading...");
				ReceiveEmailWithAttachment.receiveEmail(pop3Host, mailStoreType, userName, password);

				text = ReadingPdfText.readPdfText();
				ReadingPdfData readData = new ReadingPdfData();
				st = JdbcConnection.connectDB(readData.findInvoiceNo(HelperData.invoiceNo),
						readData.findInvoiceDate(HelperData.invoiceDate),
						readData.findCustomerPO(HelperData.customerPO), readData.findAddress(HelperData.address),
						readData.findTotalInvoice(HelperData.totalInvoice));
				flag = true;
				break;
			case 2:
				displayData.listAllPdfData(st);
				break;
			case 3:
				System.out.println(HelperData.enterInvoiceNo);
				String invoiceNo = sc.next();
				displayData.listDataWithIndividual(invoiceNo, st);
				break;
			case 4:
				System.out.println(HelperData.enterInvoiceNo);
				String invoiceNumber = sc.next();
				System.out.println("Either Approved or Rejected...");
				String status = sc.next().toLowerCase();
				boolean check = validateStatus.validateSatusAndUpdate(st, invoiceNumber, status);
				if (check)
					SendingApprovalMail.sendEmail();
				break;
			case 5:
				System.exit(0);
				break;

			default:
				System.out.println("Choose Given Option...");
				break;
			}
			System.out.println("\n");
		} while (option != 0);
		sc.close();
		st.close();
	}

}
