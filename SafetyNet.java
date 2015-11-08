package example;

import 	java.io.*;
import 	java.util.*;
import	javax.servlet.*;
import	javax.servlet.http.*;
import	javax.mail.*;
import	javax.mail.internet.*;

public class SafetyNet extends HttpServlet{
    
        short score = 0;
         
    public void checkQuery(String query)
    {
        query = query.toLowerCase();
        if ((query.contains("kill") && query.contains("myself"))
            || query.contains("how to commit") || query.contains("suicide") || query.contains("how to commit")
            || query.contains("ways to commit ") || query.contains("painless") || query.contains("death")
            || query.contains("way to die")|| query.contains("lethal dosage") || query.contains("kill myself")
            || query.contains("no reason to live") || query.contains("i need help"))
        {
            score += 5;
            if (score > 15)
                sendEmail();
            else
                System.out.println("Score: " + score);
        }   
    }
        
      public void sendEmail(HttpServletRequest request, HttpServletresponse response) throws ServletException, IOException
      {
      String to = "jhernandez86@student.rcc.edu"; //email for testing, get from database, the contact information
      String from  = "help@safetynet.com";//???
      String host = "localhost";//place we're sending from
      Properties properties = System.getProperties();//Retriving system properties

      //email server
      properties.setProperty("mail.smtp.host", host);

      Session session = Session.getInstance(properties);//retrive default Session object

      //responsive type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      try{
        //default MimeMessage object
        MimeMessage message = new MimeMessage(session);

        //set From: header field of the header.
        message.setFrom(new InternetAddress(from));

        //Set To: header field of the header
        message.addRecipent(Message.RecipentType.TO, new InternetAddress(to));

        //Set Subject: header field
        message.setSubject("Important Message!");

        //actual HTML message
        message.setContent("<h1>Listen empathise and don’t judge</h1><p>" +
                "The web browsing activity of your family member show signs of " +
                "falling back into a relapse of depression. Please take time out" +
                " of your day to listen to them actively, empathise and don't judge.</p>","text/html");

        Transport.send(message);// Send message

      }catch (MessagingException mex) {
        mex.printStackTrace();
      }
      }
}