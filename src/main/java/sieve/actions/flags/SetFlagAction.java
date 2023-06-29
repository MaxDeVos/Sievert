package sieve.actions.flags;

import jakarta.mail.Flags;
import jakarta.mail.MessagingException;
import models.EmailMessage;
import models.IMAPFlags;
import org.apache.jsieve.mail.Action;
import sieve.actions.MailAction;
import sieve.actions.actionContexts.SetFlagActionContext;
import utils.FlagUtils;

//TODO make this match the RFC 5232
public class SetFlagAction implements MailAction
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
        if (action instanceof final SetFlagActionContext removeFlagActionContext)
        {
            execute(removeFlagActionContext, mail);
        }
    }

    public void execute(SetFlagActionContext action, EmailMessage mail) throws MessagingException
    {
        // JavaMail takes care of case where flag doesn't exist and handles lowercase conversion
        IMAPFlags flags = new IMAPFlags(action.flags);
    }
}
