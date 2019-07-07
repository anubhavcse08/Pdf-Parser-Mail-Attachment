package com.altimetrik.controller;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.altimetrik.constant.HelperData;

public class SendingApprovalMail {

	public static void sendEmail() {
		String toEmail = ReceiveEmailWithAttachment.emailTo != null
				? ReceiveEmailWithAttachment.emailTo.substring(ReceiveEmailWithAttachment.emailTo.indexOf('<') + 1,
						ReceiveEmailWithAttachment.emailTo.indexOf('>'))
				: null;
		String fromEmail = ReceiveEmailWithAttachment.emailFrom != null
				? ReceiveEmailWithAttachment.emailFrom.substring(ReceiveEmailWithAttachment.emailFrom.indexOf('<') + 1,
						ReceiveEmailWithAttachment.emailFrom.indexOf('>'))
				: null;
		if (toEmail != null && fromEmail != null) {
			Properties props = System.getProperties();
			props.put(HelperData.mailAuth, "true");
			props.put(HelperData.mailStarttlsEnable, "true");
			props.put(HelperData.mailHost, HelperData.smtpGmail);
			props.put(HelperData.mailPort, "587");

			Session session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(HelperData.userName, HelperData.password);
				}
			});
			try {
				MimeMessage msg = new MimeMessage(session);

				msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
				msg.addHeader("format", "flowed");
				msg.addHeader("Content-Transfer-Encoding", "8bit");

				msg.setFrom(new InternetAddress(fromEmail, "Anubhav"));
				msg.setReplyTo(InternetAddress.parse(toEmail, false));
				msg.setSubject(HelperData.setSubjectMail, "UTF-8");
				msg.setText(HelperData.setTextMail, "UTF-8");
				msg.setSentDate(new Date());

				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
				Transport.send(msg);

				System.out.println(HelperData.emailSentMail);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			System.out.println(HelperData.mailNotFoundToSend);

	}

}
