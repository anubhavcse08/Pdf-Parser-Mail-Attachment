package com.altimetrik.pdfParser.servicetest;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import com.altimetrik.service.ReadingPdfData;
import com.altimetrik.service.ReadingPdfText;

public class ServiceTest {

	static ReadingPdfData readPdfData;

	@BeforeClass
	public static void initialize() throws InvalidPasswordException, IOException {
		ReadingPdfText.readPdfText();
		readPdfData = new ReadingPdfData();
	}

	@Test
	public void findPdfText() {
		String invoiceNum = "invNo";
		assertEquals("Invoice no. not found.", readPdfData.findInvoiceNo(invoiceNum));
	}
	
	@Test(expected=DatabaseException.class)
	public void addOneTest() throws DatabaseException
	{
		Invoice invoice = new Invoice();
		invoice.setAddress("sfsdf");
		invoice.setAmount(23423);
		invoice.setCustomerPO("sdfsdg");
		invoice.setApproved(true);
		dao.addToDatabase(invoice);
		
	}
	
	@Test
	public void getOneTest() throws DatabaseException 
	{
		Invoice invoice = new Invoice();
		invoice.setInvoiceNo("24442342");
		invoice.setAddress("sfsdf");
		invoice.setAmount(23423);
		invoice.setInvoiceDate(LocalDate.now());
		invoice.setCustomerPO("sdfsdg");
		invoice.setApproved(true);
		invoice.setEmail("pdfparser12345gmail.com");
		dao.addToDatabase(invoice);
		assertEquals(invoice.getInvoiceNo(),dao.getFromDatabase(invoice.getInvoiceNo()).getInvoiceNo());
		//assertEquals(invoice.getInvoiceNo(),dao.getFromDatabase(invoice.getInvoiceNo()));
		
		
		
	}
	@Test
	public void approveInvoiceTest() throws DatabaseException 
	{
		Invoice invoice = new Invoice();
		invoice.setInvoiceNo("24442346");
		invoice.setAddress("sfsdf");
		invoice.setAmount(23423);
		invoice.setInvoiceDate(LocalDate.now());
		invoice.setCustomerPO("sdfsdg");
		invoice.setApproved(false);
		invoice.setEmail("pdfparser12345gmail.com");
		dao.addToDatabase(invoice);
		dao.approveInvoice(invoice.getInvoiceNo());
		Invoice obj = dao.getFromDatabase(invoice.getInvoiceNo());
		assertTrue(obj.isApproved());
		
	}

}
