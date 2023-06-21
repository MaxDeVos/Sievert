package sieve.actions.flags;

import jakarta.mail.MessagingException;
import models.EmailMessage;
import org.apache.jsieve.mail.Action;
import sieve.actions.MailAction;

public class AddFlagAction implements MailAction
{
    /**
     * Executes the given action.
     *
     * @param action not null
     * @param mail   not null
     * @throws MessagingException when action cannot be executed
     */
    @Override
    public void execute(Action action, EmailMessage mail) throws MessagingException
    {

    }
}
