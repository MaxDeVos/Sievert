package sieve.actions.flags;

import jakarta.mail.Flags;
import jakarta.mail.MessagingException;
import models.EmailMessage;
import models.IMAPFlags;
import org.apache.jsieve.mail.Action;
import sieve.actions.MailAction;
import sieve.actions.actionContexts.AddFlagActionContext;
import sieve.actions.actionContexts.RemoveFlagActionContext;
import utils.FlagUtils;

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
        if (action instanceof final AddFlagActionContext removeFlagActionContext) {
            execute(removeFlagActionContext, mail);
        }
    }

    public void execute(AddFlagActionContext actionContext, EmailMessage mail) throws MessagingException
    {
        // JavaMail takes care of case where flag doesn't exist and handles lowercase conversion
        IMAPFlags newFlags = new IMAPFlags(actionContext.flags);
        newFlags.add(mail.getOriginalMessageReference().getFlags());
        mail.getOriginalMessageReference().setFlags(newFlags, true);

    }
}
