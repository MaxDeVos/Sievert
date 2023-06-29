package sieve.actions;


import jakarta.mail.MessagingException;

import models.EmailMessage;
import org.apache.jsieve.mail.Action;
import org.apache.jsieve.mail.ActionFileInto;
import org.apache.jsieve.mail.ActionKeep;

/**
 * Performs the filing of a mail into the inbox.
 * <h4>Thread Safety</h4>
 * <p>An instance maybe safe accessed concurrently by multiple threads.</p>
 */
public class KeepAction extends FileIntoAction implements MailAction
{

    private static final String INBOX = "INBOX";

    public void execute(Action action, EmailMessage mail) throws MessagingException {
        if (action instanceof final ActionKeep actionKeep) {
            execute(actionKeep, mail);
        }
    }

    /**
     * <p>
     * Executes the passed ActionKeep.
     * </p>
     *
     * <p>
     * In this implementation, "keep" is equivalent to "fileinto" with a
     * destination of "INBOX".
     * </p>
     *
     * @param anAction not null
     * @param aMail not null
     * @throws MessagingException
     */
    public void execute(ActionKeep anAction, EmailMessage aMail) throws MessagingException
    {
        final ActionFileInto action = new ActionFileInto(INBOX);
        execute(action, aMail);
    }
}
