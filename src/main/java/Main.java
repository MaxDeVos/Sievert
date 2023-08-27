import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

import jakarta.mail.Folder;
import jakarta.mail.Provider;
import jakarta.mail.Session;
import java.util.Properties;

public class Main
{

    public static void main(String[] args) throws Exception
    {

        Session session = Session.getInstance(new Properties());
        session.setDebug(true);

        IMAPStore store = (IMAPStore) session.getStore("imaps");
        store.connect("imappro.zoho.com", 993, "max@maxdevos.com", System.getenv().get("ZOHO_APP_PASSWORD"));


        IMAPFolder inbox = (IMAPFolder) store.getFolder("INBOX");
        SieveWatchdog sieveWatchdog = new SieveWatchdog("test.sieve");
        inbox.addMessageCountListener(sieveWatchdog);
        inbox.open(Folder.READ_WRITE);

        sieveWatchdog.processMessages(inbox.getMessages());

        while(true){
            inbox.idle();
        }
    }
}
