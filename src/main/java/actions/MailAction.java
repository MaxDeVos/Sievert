package actions;

import models.EmailMessage;
import org.apache.jsieve.mail.Action;

import javax.mail.MessagingException;

public interface MailAction
{

    /**
     * Executes the given action.
     * @param action not null
     * @param mail not null
     * @throws MessagingException when action cannot be executed
     */
    void execute(final Action action, final EmailMessage mail) throws MessagingException;
}

