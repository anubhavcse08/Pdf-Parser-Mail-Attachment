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

}
