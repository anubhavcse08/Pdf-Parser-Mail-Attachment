package com.altimetrik;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.altimetrik.constant.Data;
import com.altimetrik.controller.ValidatingMailInterface.ListoutPdfData;
import com.altimetrik.controller.ValidatingMailInterface.ValidatePdfStatus;
import com.altimetrik.controller.emailAttachment.ReceiveEmailWithAttachment;
import com.altimetrik.controller.sendMailApproval.SendingApprovalMail;
import com.altimetrik.service.JDBC_Connection.JdbcConnection;
import com.altimetrik.service.approvingMail.ApprovingMail;
import com.altimetrik.service.readPdfData.ReadingPdfData;
import com.altimetrik.service.readPdfText.ReadingPdfText;
import com.altimetrik.view.DisplayData;

public class Main {
	public static String text;

	public static void main(String args[]) throws IOException, SQLException {
		boolean flag = false;
		Statement st = null;
		String pop3Host = Data.pop3Host;
		String mailStoreType = Data.mailStoreType;
		final String userName = Data.userName;
		final String password = Data.password;

		ListoutPdfData displayData = new DisplayData();
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
				st = JdbcConnection.connectDB(readData.findInvoiceNo(Data.invoiceNo),
						readData.findInvoiceDate(Data.invoiceDate), readData.findCustomerPO(Data.customerPO),
						readData.findAddress(Data.address), readData.findTotalInvoice(Data.totalInvoice));
				flag = true;
				break;
			case 2:
				displayData.listAllPdfData(st);
				break;
			case 3:
				System.out.println(Data.enterInvoiceNo);
				String invoiceNo = sc.next();
				displayData.listDataWithIndividual(invoiceNo, st);
				break;
			case 4:
				System.out.println(Data.enterInvoiceNo);
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
