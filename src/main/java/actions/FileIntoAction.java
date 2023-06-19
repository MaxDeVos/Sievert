package actions;

import models.EmailMessage;
import org.apache.jsieve.mail.Action;
import org.apache.jsieve.mail.ActionFileInto;

import javax.mail.MessagingException;

public class FileIntoAction implements MailAction
{

    private static final char HIERARCHY_DELIMITER = '.';

    public void execute(Action action, EmailMessage mail) throws MessagingException
    {
        if (action instanceof ActionFileInto) {
            final ActionFileInto fileIntoAction = (ActionFileInto) action;
            execute(fileIntoAction, mail);
        }
    }

    /**
     * <p>
     * Executes the passed ActionFileInto.
     * </p>
     *
     * <p>
     * This implementation accepts any destination with the root of <code>INBOX</code>.
     * </p>
     *
     * <p>
     * As the current POP3 server does not support sub-folders, the mail is
     * stored in the INBOX for the recipient of the mail and the full intended
     * destination added as a prefix to the message's subject.
     * </p>
     *
     * <p>
     * When IMAP support is added to James, it will be possible to support
     * sub-folders of <code>INBOX</code> fully.
     * </p>
     *
     * @param anAction
     * @param aMail
     * @throws MessagingException
     */
    public void execute(ActionFileInto anAction, EmailMessage aMail) throws MessagingException
    {

        System.out.println("FILING " + aMail.getSubject() + " INTO " + anAction.getDestination());

//        String destinationMailbox = anAction.getDestination();
//        MailAddress recipient;
//        boolean delivered = false;
//        try
//        {
//            recipient = ActionUtils.getSoleRecipient(aMail);
//            MimeMessage localMessage = createMimeMessage(aMail, recipient);
//
//            if (!(destinationMailbox.length() > 0
//                    && destinationMailbox.charAt(0) == HIERARCHY_DELIMITER)) {
//                destinationMailbox =  HIERARCHY_DELIMITER + destinationMailbox;
//            }
//
//            final String mailbox = destinationMailbox.replace(HIERARCHY_DELIMITER, '/');
//            final String host;
//            if (mailbox.charAt(0) == '/') {
//                host = "@localhost";
//            } else {
//                host = "@localhost/";
//            }
//            final String url = "mailbox://" + recipient.getUser() + host + mailbox;
//            //TODO: copying this message so many times seems a waste
//            context.post(url, localMessage);
//            delivered = true;
//        }
//        catch (MessagingException ex)
//        {
//            final Log log = context.getLog();
//            if (log.isDebugEnabled()) {
//                log.debug("Error while storing mail into. "+destinationMailbox, ex);
//            }
//            throw ex;
//        }
//        finally
//        {
//            // Ensure the mail is always ghosted
//            aMail.setState(Mail.GHOST);
//        }
//        if (delivered)
//        {
//            final Log log = context.getLog();
//            if (log.isDebugEnabled()) {
//                log.debug("Filed Message ID: "
//                        + aMail.getMessage().getMessageID()
//                        + " into destination: \""
//                        + destinationMailbox + "\"");
//            }
//        }
    }

//    private static MimeMessage createMimeMessage(Mail aMail, MailAddress recipient) throws MessagingException {
//        // Adapted from LocalDelivery Mailet
//        // Add qmail's de facto standard Delivered-To header
//        MimeMessage localMessage = new MimeMessage(aMail.getMessage())
//        {
//            protected void updateHeaders() throws MessagingException
//            {
//                if (getMessageID() == null)
//                    super.updateHeaders();
//                else
//                    modified = false;
//            }
//        };
//        localMessage.addHeader("Delivered-To", recipient.toString());
//
//        localMessage.saveChanges();
//        return localMessage;
//    }
}
