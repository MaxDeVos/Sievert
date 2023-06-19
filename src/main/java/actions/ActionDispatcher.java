package actions;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.mail.MessagingException;

import models.EmailMessage;
import org.apache.jsieve.mail.*;

/**
 * Dynamically dispatches an Action depending on the type of Action received at runtime.
 * <h4>Thread Safety</h4>
 * <p>An instance maybe safe accessed concurrently by multiple threads.</p>
 */
public class ActionDispatcher
{
    /**
     * A Map keyed by the type of Action. The values are the methods to invoke to
     * handle the Action.
     * <Action, MailAction>
     */
    private ConcurrentMap<Class, MailAction> fieldMailActionMap;

    /**
     * Constructor for ActionDispatcher.
     * @throws NoSuchMethodException
     */
    public ActionDispatcher() throws MessagingException
    {
        super();
        setMethodMap(defaultMethodMap());
    }

    /**
     * Method execute executes the passed Action by invoking the method mapped by the
     * receiver with a parameter of the EXACT type of Action.
     * @param anAction not null
     * @param aMail not null
     * @throws MessagingException
     */
    public void execute(final Action anAction, final EmailMessage aMail) throws MessagingException
    {
        final MailAction mailAction = getMethodMap().get(anAction.getClass());
        mailAction.execute(anAction, aMail);
    }

    /**
     * Returns the methodMap.
     * @return Map
     */
    public ConcurrentMap<Class, MailAction> getMethodMap()
    {
        return fieldMailActionMap;
    }

    /**
     * Returns a new methodMap.
     * @return Map
     */
    private ConcurrentMap<Class, MailAction> defaultMethodMap()
    {
        final ConcurrentMap<Class, MailAction> actionMap = new ConcurrentHashMap<>(4);
        actionMap.put(ActionFileInto.class, new FileIntoAction());
        actionMap.put(ActionKeep.class, new KeepAction());
        actionMap.put(ActionRedirect.class, new RedirectAction());
        actionMap.put(ActionReject.class, new RejectAction());
//        actionMap.put(ActionDiscard.class, new Discard)
        return actionMap;
    }

    /**
     * Sets the mail action mail.
     * @param mailActionMap <Action, MailAction> not null
     */
    protected void setMethodMap(ConcurrentMap<Class, MailAction>  mailActionMap)
    {
        fieldMailActionMap = mailActionMap;
    }
}
