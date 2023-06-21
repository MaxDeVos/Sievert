package sieve.commands;

import org.apache.jsieve.Arguments;
import org.apache.jsieve.Block;
import org.apache.jsieve.SieveContext;
import org.apache.jsieve.commands.AbstractActionCommand;
import org.apache.jsieve.exception.SieveException;
import org.apache.jsieve.mail.ActionDiscard;
import org.apache.jsieve.mail.MailAdapter;

/**
 * Class Discard implements the Discard Command as defined in RFC 3028, section
 * 4.5.
 */
public class DiscardCommand extends AbstractActionCommand
{

    /**
     * Constructor for Discard.
     */
    public DiscardCommand() {
        super();
    }

    /**
     * <p>
     * Discard silently discards a Mail by cancelling the implicit keep as
     * specified in RFC 3028, Section 4.5.
     * </p>
     * <p>
     *
     * @see org.apache.jsieve.commands.AbstractCommand#executeBasic(MailAdapter,
     *      Arguments, Block, SieveContext)
     *      </p>
     */
    protected Object executeBasic(MailAdapter mail, Arguments arguments,
                                  Block block, SieveContext context) throws SieveException {
        // Just cancels the implicit keep
        // See http://tools.ietf.org/html/rfc5228#section-4.4
        context.getCommandStateManager().setImplicitKeep(false);

        mail.addAction(new ActionDiscard());

        return null;
    }

}
