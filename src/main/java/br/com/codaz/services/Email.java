package br.com.codaz.services;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email{

	public static void email(String user){
	    Properties props = new Properties();
	    
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class",
	    "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465");

	    Session session = Session.getDefaultInstance(props,
	      new javax.mail.Authenticator() {
	           protected PasswordAuthentication getPasswordAuthentication()
	           {
	                 return new PasswordAuthentication("mateush7811@gmail.com",
	                 "passwpa11");
	           }
	      });

	    /** Ativa Debug para sessão */
	    session.setDebug(true);

	    try {

	      Message message = new MimeMessage(session);
	      message.setFrom(new InternetAddress("mateush7811@gmail.com"));
	      //Remetente

	      Address[] toUser = InternetAddress //Destinatário(s)
	                 .parse("mateush781@gmail.com");

	      message.setRecipients(Message.RecipientType.TO, toUser);
	      message.setSubject("Serviço Iniciado");//Assunto
	      message.setText("O bot foi ativado com sucesso para: "+user);
	      /**Método para enviar a mensagem criada*/
	      Transport.send(message);

	      System.out.println("Feito!!!");

	     } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	  }
	
	public static void fim(){
	    Properties props = new Properties();
	    
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class",
	    "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465");

	    Session session = Session.getDefaultInstance(props,
	      new javax.mail.Authenticator() {
	           protected PasswordAuthentication getPasswordAuthentication()
	           {
	                 return new PasswordAuthentication("mateush7811@gmail.com",
	                 "passwpa11");
	           }
	      });

	    /** Ativa Debug para sessão */
	    session.setDebug(true);

	    try {

	      Message message = new MimeMessage(session);
	      message.setFrom(new InternetAddress("mateush7811@gmail.com"));
	      //Remetente

	      Address[] toUser = InternetAddress //Destinatário(s)
	                 .parse("mateush781@gmail.com");

	      message.setRecipients(Message.RecipientType.TO, toUser);
	      message.setSubject("Serviço Iniciado");//Assunto
	      message.setText("O serviço foi finalizado!");
	      /**Método para enviar a mensagem criada*/
	      Transport.send(message);

	      System.out.println("Feito!!!");

	     } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	  }
	
}
