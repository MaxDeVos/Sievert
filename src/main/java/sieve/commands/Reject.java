package sieve.commands;

import org.apache.jsieve.Arguments;
import org.apache.jsieve.Block;
import org.apache.jsieve.SieveContext;
import org.apache.jsieve.StringListArgument;
import org.apache.jsieve.commands.AbstractActionCommand;
import org.apache.jsieve.exception.CommandException;
import org.apache.jsieve.exception.SieveException;
import org.apache.jsieve.mail.ActionReject;
import org.apache.jsieve.mail.MailAdapter;

/**
 * Class | Interface Enter description here
 *
 * Creation Date: 11-Jan-04
 *
 * @author sbrewin
 *
 * Copyright 2003, Synergy Systems Limited
 */
/**
 * Class Reject implements the Reject Command as defined in RFC 3028, section
 * 4.1.
 */
public class Reject extends AbstractActionCommand {

    /**
     * Constructor for Reject.
     */
    public Reject() {
        super();
    }

    /**
     * <p>
     * Add an ActionReject to the List of Actions to be performed.
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
        final String message = ((StringListArgument) arguments
                .getArgumentList().get(0)).getList().get(0);

        mail.addAction(new ActionReject(message));
        return null;
    }

    /**
     * @see org.apache.jsieve.commands.AbstractCommand#validateState(SieveContext)
     */
    protected void validateState(final SieveContext context)
            throws CommandException {
        super.validateState(context);

        if (context.getCommandStateManager().isHasActions())
            throw context
                    .getCoordinate()
                    .commandException(
                            "The \"reject\" command is not allowed with other Action Commands");
    }

    /**
     * @see org.apache.jsieve.commands.AbstractCommand#updateState(SieveContext)
     */
    protected void updateState(SieveContext context) {
        super.updateState(context);
        context.getCommandStateManager().setRejected(true);
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
