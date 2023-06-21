package sieve.commands.flags;

import org.apache.jsieve.Arguments;
import org.apache.jsieve.Block;
import org.apache.jsieve.SieveContext;
import org.apache.jsieve.commands.AbstractActionCommand;
import org.apache.jsieve.exception.SieveException;
import org.apache.jsieve.mail.MailAdapter;

public class RemoveFlagCommand extends AbstractActionCommand
{

    public RemoveFlagCommand(){
        super();
    }

    /**
     * Abstract method executeBasic invokes a Sieve Command.
     *
     * @param mail
     * @param arguments
     * @param block
     * @param context   <code>SieveContext</code> giving contextual information, not
     *                  null
     * @return Object
     * @throws SieveException
     */
    @Override
    protected Object executeBasic(MailAdapter mail, Arguments arguments, Block block, SieveContext context) throws SieveException
    {
        return null;
    }
}
