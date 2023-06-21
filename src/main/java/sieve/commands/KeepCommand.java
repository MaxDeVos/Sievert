package sieve.commands;

import org.apache.jsieve.Arguments;
import org.apache.jsieve.Block;
import org.apache.jsieve.SieveContext;
import org.apache.jsieve.commands.AbstractActionCommand;
import org.apache.jsieve.exception.SieveException;
import org.apache.jsieve.mail.ActionKeep;
import org.apache.jsieve.mail.MailAdapter;

/**
 * Class Keep implements the Keep Command as defined in RFC 3028, section 4.4.
 */
public class KeepCommand extends AbstractActionCommand
{

    /**
     * Constructor for Keep.
     */
    public KeepCommand() {
        super();
    }

    /**
     * <p>
     * Add an ActionKeep to the List of Actions to be performed.
     * </p>
     * <p>
     * Also,
     *
     * @see org.apache.jsieve.commands.AbstractCommand#executeBasic(MailAdapter,
     *      Arguments, Block, SieveContext)
     *      </p>
     */
    protected Object executeBasic(MailAdapter mail, Arguments arguments,
                                  Block block, SieveContext context) throws SieveException {
        mail.addAction(new ActionKeep());
        return null;
    }

}
