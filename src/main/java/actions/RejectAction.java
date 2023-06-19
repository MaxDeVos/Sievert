package actions;


import javax.mail.MessagingException;

import models.EmailMessage;
import org.apache.jsieve.mail.Action;
import org.apache.jsieve.mail.ActionReject;

/**
 * Performs the rejection of a mail, with a reply to the sender.
 * <h4>Thread Safety</h4>
 * <p>An instance maybe safe accessed concurrently by multiple threads.</p>
 */
public class RejectAction implements MailAction
{

    public void execute(Action action, EmailMessage mail)
            throws MessagingException {
        if (action instanceof ActionReject) {
            final ActionReject actionReject = (ActionReject) action;
            execute(actionReject, mail);
        }

    }

    /**
     * <p>
     * Method execute executes the passed ActionReject. It sends an RFC 2098
     * compliant reject MDN back to the sender.
     * </p>
     * <p>
     * NOTE: The Mimecontent type should be 'report', but as we do not yet have
     * a DataHandler for this yet, its currently 'text'!
     *
     * @param anAction not null
     * @param aMail not null
     * @throws MessagingException
     */
    public void execute(ActionReject anAction, EmailMessage aMail) throws MessagingException
    {
        System.out.println("REJECTED " + aMail.getSubject());
    }

}
