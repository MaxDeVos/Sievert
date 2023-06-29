package sieve;

import com.sun.mail.imap.IMAPMessage;
import models.EmailMessage;
import org.apache.jsieve.SieveContext;
import org.apache.jsieve.exception.InternetAddressException;
import org.apache.jsieve.exception.SieveException;
import org.apache.jsieve.mail.Action;
import org.apache.jsieve.mail.AddressImpl;
import org.apache.jsieve.mail.MailAdapter;
import org.apache.jsieve.mail.SieveMailException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import org.apache.jsieve.parser.address.SieveAddressBuilder;
import org.apache.jsieve.parser.generated.address.ParseException;
import utils.ConsoleUtils;
import utils.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class JavaxMailAdapter implements MailAdapter
{

    EmailMessage message;

    SieveContext sieveContext;
    List<Action> actions = new LinkedList<>();
    ActionDispatcher actionDispatcher;

    public JavaxMailAdapter(IMAPMessage message) throws IOException, MessagingException
    {
        this.actionDispatcher = new ActionDispatcher();
        this.message = new EmailMessage(message);
    }

    /**
     * <p>Sets the context for the current sieve script execution.</p>
     * <p>Sieve engines <code>MUST</code> set this property before any calls
     * related to the execution of a script are made.</p>
     * <p>Implementations intended to be shared between separate threads of
     * execution <code>MUST</code> ensure that they manage concurrency contexts,
     * for example by storage in a thread local variable. Engines <code>MUST</code>
     * - for a script execution - ensure that all calls are made within the
     * same thread of execution.</p>
     *
     * @param context the current context,
     *                or null to clear the contest once the execution of a script has completed.
     */
    @Override
    public void setContext(SieveContext context)
    {
        sieveContext = context;
    }


    /**
     * Method getActions answers the List of Actions accumulated by the
     * receiver. Implementations may elect to supply an unmodifiable collection.
     *
     * @return <code>List</code> of {@link Action}'s, not null, possibly
     * unmodifiable
     */
    @Override
    public List<Action> getActions()
    {
        return actions;
    }

    /**
     * Method addAction adds an Action to the List of Actions to be performed by
     * the receiver.
     *
     * @param action
     */
    @Override
    public void addAction(Action action)
    {
        actions.add(action);
    }


    /**
     * Method executeActions. Applies the Actions accumulated by the receiver.
     */
    @Override
    public void executeActions() throws SieveException
    {
        for (Action action : actions)
        {
            try
            {
                actionDispatcher.execute(action, message);
            } catch (MessagingException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Method getHeader answers a List of all of the headers in the receiver
     * whose name is equal to the passed name. If no headers are found an empty
     * List is returned.
     *
     * @param name
     * @return <code>List</code> not null, possibly empty, possible
     * unmodifiable
     * @throws SieveMailException
     */
    @Override
    public List<String> getHeader(String name) throws SieveMailException
    {
        List<String> out;
        try
        {
            out = new LinkedList<>(Arrays.stream(message.getHeader(name)).toList());
        } catch (MessagingException e)
        {
            throw new SieveMailException(e);
        }
        return out;
    }

    /**
     * <p>
     * Method getMatchingHeader answers a List of all of the headers in the
     * receiver with the passed name. If no headers are found an empty List is
     * returned.
     * </p>
     *
     * <p>
     * This method differs from getHeader(String) in that it ignores case and
     * the whitespace prefixes and suffixes of a header name when performing the
     * match, as required by RFC 3028. Thus "From", "from ", " From" and " from "
     * are considered equal.
     * </p>
     *
     * @param name
     * @return <code>List</code>, not null possibly empty, possible
     * unmodifiable
     * @throws SieveMailException
     */
    @Override
    public List<String> getMatchingHeader(String name) throws SieveMailException
    {
        List<String> out;

        name = name.trim();

        try
        {
            String[] results = message.getHeader(name);
            if(results.length == 0){
                return new LinkedList<>();
            }
            out = new LinkedList<>(Arrays.stream(results).toList());
        } catch (MessagingException e)
        {
            throw new SieveMailException(e);
        }

        return out;
    }

    /**
     * Method getHeaderNames answers a List of all of the headers in the
     * receiver. No duplicates are allowed.
     *
     * @return <code>List</code>, not null possible empty, possible
     * unmodifiable
     * @throws SieveMailException
     */
    @Override
    public List<String> getHeaderNames() throws SieveMailException
    {
        LinkedList<String> out = new LinkedList<>();
        try
        {
            message.getAllHeaders().asIterator().forEachRemaining(header ->
            {
                out.add(header.getName());
            });
        } catch (MessagingException e)
        {
            throw new RuntimeException(e);
        }
        return out;
    }


    /**
     * Method getSize answers the receiver's message size in octets.
     *
     * @return int
     * @throws SieveMailException
     */
    @Override
    public int getSize() throws SieveMailException
    {
        try
        {
            return message.getSize();
        } catch (MessagingException e)
        {
            throw new SieveMailException(e);
        }
    }

    /**
     * Method getContentType returns string/mime representation of the message
     * type.
     *
     * @return String
     * @throws SieveMailException
     */
    @Override
    public String getContentType() throws SieveMailException
    {
        try
        {
            return message.getContentType();
        } catch (MessagingException e)
        {
            throw new SieveMailException(e);
        }
    }

    /**
     * Is the given phrase found in the body text of this mail?
     * This search should be case insensitive.
     *
     * @param phrasesCaseInsensitive the phrases to search
     * @return true when the mail has a textual body and contains the phrase
     * (case insensitive), false otherwise
     * @throws SieveMailException when the search cannot be completed
     */
    @Override
    public boolean isInBodyText(List<String> phrasesCaseInsensitive) throws SieveMailException
    {
        for (String query : phrasesCaseInsensitive)
        {
            try
            {
                if (StringUtils.containsIgnoreCase(message.getContent().toString(), query))
                {
                    return true;
                }
            } catch (IOException | MessagingException e)
            {
                throw new SieveMailException(e);
            }
        }
        return false;
    }

    /**
     * Is the given phrase found in the body raw of this mail?
     * This search should be case insensitive and the mail should not be decoded.
     *
     * @param phrasesCaseInsensitive the phrases to search
     * @return true when the mail has a textual body and contains the phrase
     * (case insensitive), false otherwise
     * @throws SieveMailException when the search cannot be completed
     */
    @Override
    public boolean isInBodyRaw(List<String> phrasesCaseInsensitive) throws SieveMailException
    {
        // TODO make this actually raw
        return isInBodyText(phrasesCaseInsensitive);
    }

    /**
     * Is the given phrase found in the body contents of the specified content types of this mail?
     * This search should be case insensitive.
     *
     * @param contentTypes           Content types of the body parts we should search into.
     * @param phrasesCaseInsensitive the phrases to search
     * @return true when the mail has a textual body and contains the phrase
     * (case insensitive), false otherwise
     * @throws SieveMailException when the search cannot be completed
     */
    @Override
    public boolean isInBodyContent(List<String> contentTypes, List<String> phrasesCaseInsensitive) throws SieveMailException
    {
        // TODO handle non-text-only queries you fucking idiot
        return isInBodyText(phrasesCaseInsensitive);
    }

    /** bruh */
    public Address[] parseAddresses(String arg) throws SieveMailException, InternetAddressException
    {
        String rawAddresses = null;
        try
        {
            rawAddresses = message.getHeader(arg.toLowerCase(Locale.ENGLISH), ",");
            rawAddresses = StringUtils.replace(rawAddresses, "\r", "");
            rawAddresses = StringUtils.replace(rawAddresses, "\n", "");

            SieveAddressBuilder builder = new SieveAddressBuilder();
            builder.addAddresses(rawAddresses);
            return builder.getAddresses();
        }
        catch (MessagingException e)
        {
            System.out.println("PISSED THE BED");
            throw new SieveMailException(e);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            System.out.println(rawAddresses);
            throw new RuntimeException(e);
        }
    }
}
