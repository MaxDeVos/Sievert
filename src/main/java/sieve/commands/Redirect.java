package sieve.commands;

import org.apache.jsieve.Arguments;
import org.apache.jsieve.Block;
import org.apache.jsieve.SieveContext;
import org.apache.jsieve.StringListArgument;
import org.apache.jsieve.commands.AbstractActionCommand;
import org.apache.jsieve.exception.SieveException;
import org.apache.jsieve.mail.ActionRedirect;
import org.apache.jsieve.mail.MailAdapter;

/**
 * Class Redirect implements the Redirect Command as defined in RFC 3028,
 * section 4.3.
 */
public class Redirect extends AbstractActionCommand
{

    /**
     * Constructor for Redirect.
     */
    public Redirect() {
        super();
    }

    /**
     * <p>
     * Add an ActionRedirect to the List of Actions to be performed passing the
     * sole StringList argument as the recipient.
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
        String recipient = ((StringListArgument) arguments
                .getArgumentList().get(0)).getList().get(0);

        mail.addAction(new ActionRedirect(recipient));

        return null;
    }

    /**
     * @see org.apache.jsieve.commands.AbstractCommand#validateArguments(Arguments,
     *      SieveContext)
     */
    protected void validateArguments(Arguments arguments, SieveContext context)
            throws SieveException {
        validateSingleStringArguments(arguments, context);
    }

}
