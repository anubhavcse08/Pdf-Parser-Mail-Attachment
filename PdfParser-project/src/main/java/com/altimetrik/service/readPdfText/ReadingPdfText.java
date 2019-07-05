package com.altimetrik.service.readPdfText;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

import com.altimetrik.constant.Data;
import com.altimetrik.controller.emailAttachment.ReceiveEmailWithAttachment;

public class ReadingPdfText {

	public static String readPdfText() throws InvalidPasswordException, IOException {

		File file = new File(Data.dbPropertiesPath);
		PDDocument document = PDDocument
				.load(ReceiveEmailWithAttachment.targetFile != null ? ReceiveEmailWithAttachment.targetFile : file);

		PDFTextStripper pdfStripper = new PDFTextStripper();
		String text = pdfStripper.getText(document);
		document.close();
		return text;
	}

}
