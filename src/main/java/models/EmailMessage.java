package models;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPMessage;

import jakarta.mail.Address;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.LinkedList;
import java.util.List;

public class EmailMessage extends MimeMessage
{
    String subject;
    IMAPMessage originalMessageReference;
    public EmailMessage(IMAPMessage message) throws MessagingException
    {
        super(message);
        subject = getSubject();
        System.out.println(subject + " | X-RETURNS-TO-SENDER: " + returnsToSender());
        setHeader("x-returns-to-sender", String.valueOf(returnsToSender()));
        originalMessageReference = message;
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

    public IMAPMessage getOriginalMessageReference(){
        return originalMessageReference;
    }

    public void move(String destination) throws MessagingException
    {
        if(destination.equalsIgnoreCase(originalMessageReference.getFolder().getName()))
        {
           return;
        }

        IMAPFolder currentFolder = (IMAPFolder) originalMessageReference.getFolder();
        IMAPFolder destinationFolder = (IMAPFolder) originalMessageReference.getFolder().getStore().getFolder(destination);
        destinationFolder.open(Folder.READ_WRITE);
        currentFolder.moveMessages(new Message[]{originalMessageReference}, destinationFolder);
        destinationFolder.getMessage(originalMessageReference.getMessageNumber());
        destinationFolder.close(true);
    }

}
