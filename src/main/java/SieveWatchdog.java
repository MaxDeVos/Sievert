import com.sun.mail.imap.IMAPMessage;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.event.MessageCountEvent;
import jakarta.mail.event.MessageCountListener;
import models.EmailMessage;
import org.apache.jsieve.ConfigurationManager;
import org.apache.jsieve.SieveConfigurationException;
import org.apache.jsieve.SieveFactory;
import org.apache.jsieve.exception.SieveException;
import org.apache.jsieve.parser.generated.Node;
import org.apache.jsieve.parser.generated.ParseException;
import sieve.JavaxMailAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SieveWatchdog implements MessageCountListener
{

    SieveFactory sieveFactory;
    Node sieveHead;

    public SieveWatchdog(String sieveScriptPath)
    {
        try
        {
            // Set up Sieve stuff
            ConfigurationManager configurationManager = new ConfigurationManager();
            sieveFactory = configurationManager.build();

            // Parse sieve file
            File scriptResource = new File(ClassLoader.getSystemResource(sieveScriptPath).getFile());
            sieveHead = sieveFactory.parse(new FileInputStream(scriptResource));
        }
        catch (SieveConfigurationException | FileNotFoundException | ParseException e)
        {
            // TODO uh handle this?
            System.exit(-1);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void messagesAdded(MessageCountEvent e)
    {
        processMessages(e.getMessages());
    }

    public void processMessage(Message m){
        try
        {
            System.out.println(m.getSubject());
            JavaxMailAdapter mailAdapter = new JavaxMailAdapter((IMAPMessage) m);
            sieveFactory.evaluate(mailAdapter, sieveHead);
        }
        catch (MessagingException | SieveException | IOException ex)
        {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void processMessages(Message[] messages){
        for(Message m : messages){
            processMessage(m);
        }
    }

    /**
     * Invoked when messages are removed (expunged) from a folder.
     *
     * @param    e    the MessageCountEvent
     */
    @Override
    public void messagesRemoved(MessageCountEvent e)
    {

    }



}
