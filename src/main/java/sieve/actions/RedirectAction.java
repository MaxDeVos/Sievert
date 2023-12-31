package sieve.actions;

import models.EmailMessage;
import org.apache.jsieve.mail.Action;
import org.apache.jsieve.mail.ActionRedirect;
import utils.StringUtils;

import jakarta.mail.MessagingException;

public class RedirectAction implements MailAction
{

    public void execute(Action action, EmailMessage mail) throws MessagingException
    {
        if (action instanceof final ActionRedirect actionRedirect) {
            execute(actionRedirect, mail);
        }
    }

    /**
     * Method execute executes the passed ActionRedirect.
     *
     * @param anAction not nul
     * @param aMail not null
     * @throws MessagingException
     */
    public void execute(ActionRedirect anAction, EmailMessage aMail) throws MessagingException
    {
        System.out.println("REDIRECT | " + StringUtils.padTrimToLength(anAction.getAddress(), 25) + " | " +
                StringUtils.abbreviate(aMail.getSubject(), 100));
    }
}
