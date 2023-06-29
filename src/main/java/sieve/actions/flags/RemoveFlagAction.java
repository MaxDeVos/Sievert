package sieve.actions.flags;

import jakarta.mail.Flags;
import jakarta.mail.MessagingException;
import models.EmailMessage;
import models.IMAPFlags;
import org.apache.jsieve.mail.Action;
import org.apache.jsieve.mail.ActionFileInto;
import sieve.actions.MailAction;
import sieve.actions.actionContexts.RemoveFlagActionContext;
import utils.ConsoleUtils;
import utils.FlagUtils;

import java.util.Arrays;
import java.util.Collections;

public class RemoveFlagAction implements MailAction
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
        if (action instanceof final RemoveFlagActionContext removeFlagActionContext) {
            execute(removeFlagActionContext, mail);
        }
    }

    public void execute(RemoveFlagActionContext actionContext, EmailMessage mail) throws MessagingException
    {

        // JavaMail takes care of case where flag doesn't exist and handles lowercase conversion
//        Flags originalFlags = mail.getOriginalMessageReference().getFlags();
        IMAPFlags newFlags = new IMAPFlags(actionContext.flags);
//        originalFlags.remove(newFlags);
        mail.getOriginalMessageReference().setFlags(newFlags, false);
    }
}
