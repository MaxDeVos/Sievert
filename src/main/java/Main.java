import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPMessage;
import com.sun.mail.imap.IMAPStore;
import com.sun.mail.imap.SortTerm;
import com.sun.mail.smtp.SMTPMessage;
import listeners.MailListener;
import models.EmailMessage;
import utils.StringUtils;
import org.apache.jsieve.*;
import org.apache.jsieve.parser.generated.Node;

import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.event.MessageCountAdapter;
import jakarta.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
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

        Session session = Session.getInstance(new Properties());
        session.setDebug(true);
        IMAPStore store = (IMAPStore) session.getStore("imaps");


        store.connect("imappro.zoho.com", 993, "max@maxdevos.com", System.getenv().get("ZOHO_APP_PASSWORD"));
//        store.connect( "imap.gmail.com", 993, "devosmaxwell@gmail.com", System.getenv().get("GMAIL_APP_PASSWORD"));

        // Select email folder, iterate through messages
        IMAPFolder inbox = (IMAPFolder) store.getFolder("INBOX");

        MailListener mailListener = new MailListener();
        inbox.addMessageChangedListener(mailListener);
        inbox.addFolderListener(mailListener);
        inbox.addMessageCountListener(mailListener);
        inbox.open(Folder.READ_WRITE);

//        Message[] messages = inbox.getMessages();




        inbox.idle();

//        for (Message m : messages)
//        {
//            EmailMessage mm = new EmailMessage((MimeMessage) m);
//
//            IMAPMessage imapMessage = (IMAPMessage) m;
//            Flags flags = imapMessage.getFlags();
//            flags.add("maxTestFlag");
//
//            imapMessage.setFlags(flags, true);
//
//            inbox.close();
//            store.close();
//
//            System.out.println(mm.getSubject());
//            System.out.println(mm.getSentDate().toString());
//
//            System.out.println("WAITING");
//            Thread.sleep(5000);
//
//            Session session = Session.getInstance(new Properties());
//            IMAPStore store = (IMAPStore) session.getStore("imaps");
//            store.connect("imap.mailfence.com", 993, "maxdevos@mailfence.com", "Upfront!Dinginess2!Lapdog!Bonfire");
//
//            IMAPFolder mailfenceInbox = (IMAPFolder) store.getFolder("INBOX/Important");
//            mailfenceInbox.open(Folder.READ_WRITE);
////            Message[] messages = mailfenceInbox.getMessages();
//
//            mailfenceInbox.addMessages(new Message[]{mm});
//            FileOutputStream outputStream = new FileOutputStream(StringUtils.sanitize(mm.getSubject()) + ".eml");
//            mm.writeTo(outputStream);
//            outputStream.close();
////            sieve.JavaxMailAdapter mailAdapter = new sieve.JavaxMailAdapter(mm);
////            factory.evaluate(mailAdapter, n);
//            break;
//        }
    }
}
