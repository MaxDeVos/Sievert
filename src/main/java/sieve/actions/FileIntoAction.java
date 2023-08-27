package sieve.actions;

import models.EmailMessage;
import org.apache.jsieve.mail.Action;
import org.apache.jsieve.mail.ActionFileInto;

import jakarta.mail.MessagingException;

public class FileIntoAction implements MailAction
{

    public void execute(Action action, EmailMessage mail) throws MessagingException
    {
        if (action instanceof final ActionFileInto fileIntoAction) {
            execute(fileIntoAction, mail);
        }
    }

    /**
     * <p>
     * Executes the passed ActionFileInto.
     * </p>
     *
     * <p>
     * This implementation accepts any destination with the root of <code>INBOX</code>.
     * </p>
     *
     * <p>
     * As the current POP3 server does not support sub-folders, the mail is
     * stored in the INBOX for the recipient of the mail and the full intended
     * destination added as a prefix to the message's subject.
     * </p>
     *
     * <p>
     * When IMAP support is added to James, it will be possible to support
     * sub-folders of <code>INBOX</code> fully.
     * </p>
     *
     * @param anAction
     * @param aMail
     * @throws MessagingException
     */
    public void execute(ActionFileInto anAction, EmailMessage aMail) throws MessagingException
    {
//        LogManager.getLogger().log(Level.INFO, "FILING " + aMail.getSubject() + " INTO " + anAction.getDestination());
//        System.out.println( "FILING " + aMail.getSubject() + " INTO " + anAction.getDestination());
        aMail.move(anAction.getDestination());
    }
}
