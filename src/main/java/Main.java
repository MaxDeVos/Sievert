import com.sun.mail.imap.IMAPStore;
import models.EmailMessage;
import org.apache.jsieve.*;
import org.apache.jsieve.parser.generated.Node;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Main
{

    public static void main(String[] args) throws Exception
    {

        // Set up Sieve stuff
        ConfigurationManager configurationManager = new ConfigurationManager();
        SieveFactory factory = configurationManager.build();

        // Parse sieve file
        File scriptResource = new File(ClassLoader.getSystemResource("test.sieve").getFile());
        Node n = factory.parse(new FileInputStream(scriptResource));

        // Configure email server
        String host = "imap.gmail.com";
        int port = 993;
        String username = "devosmaxwell@gmail.com";
        String password = "brlktiwghykveuet";
        Properties props = new Properties();
        props.put("mail.imap.host", host);
        props.put("mail.imap.user", username);
        props.put("mail.imap.port", port);
        props.put("mail.imap.ssl.enable", true);
        props.put("mail.imap.ssl.trust", "*");

        // Connect to email server
        Session session = Session.getInstance(props);
        IMAPStore store = (IMAPStore) session.getStore("imaps");
        store.connect(host, port, username, password);

        // Select email folder, iterate through messages
        Folder inbox = store.getFolder("real people emails");
//        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);
        Message[] messages = inbox.getMessages();
        for (Message m : messages)
        {
            EmailMessage mm = new EmailMessage((MimeMessage) m);
            JavaxMailAdapter mailAdapter = new JavaxMailAdapter(mm);
            factory.evaluate(mailAdapter, n);
        }
    }
}
