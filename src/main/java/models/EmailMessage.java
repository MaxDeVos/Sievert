package models;

import com.sun.mail.imap.IMAPMessage;

import jakarta.mail.Address;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.LinkedList;
import java.util.List;

public class EmailMessage extends MimeMessage
{
    String subject;
    public EmailMessage(MimeMessage message) throws MessagingException
    {
        super(message);
        subject = getSubject();
        setHeader("x-returns-to-sender", String.valueOf(returnsToSender()));
    }

    public boolean returnsToSender(){
        try
        {
            // god, I miss writing C# lol
            String[] returnPath = getHeader("Return-Path");
            List<String> returnPathAddresses = new LinkedList<>();
            for (String path: returnPath)
            {
                String rPath = path.substring(1, path.length() - 1);
                returnPathAddresses.add(rPath);
            }

            for (String replyTo : returnPathAddresses)
            {
                for (Address from : getFrom())
                {
                    if(((InternetAddress) from).getAddress().equalsIgnoreCase(replyTo))
                    {
                        return true;
                    }
                }
            }
            return false;
        } catch (MessagingException e)
        {
            System.err.println("Failed to evaluate if message returns to self for message:");
            System.err.println(subject);
            e.printStackTrace();
            return true;
        }
    }

}
