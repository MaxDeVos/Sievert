package listeners;

import utils.ConsoleUtils;
import utils.StringUtils;

import jakarta.mail.Message;
import jakarta.mail.event.*;

public class MailListener implements MessageChangedListener, StoreListener, FolderListener, MessageCountListener, ConnectionListener
{

    /**
     * Invoked when a message is changed. The change-type specifies
     * what changed.
     *
     * @param    e    the MessageChangedEvent
     * @see MessageChangedEvent#FLAGS_CHANGED
     * @see MessageChangedEvent#ENVELOPE_CHANGED
     */
    @Override
    public void messageChanged(MessageChangedEvent e)
    {
        ConsoleUtils.printMessageSubjects(new Message[]{e.getMessage()}, "Message Changed");
    }

    /**
     * Invoked when a Folder is created.
     *
     * @param    e    the FolderEvent
     */
    @Override
    public void folderCreated(FolderEvent e)
    {

    }

    /**
     * Invoked when a folder is deleted.
     *
     * @param    e    the FolderEvent
     */
    @Override
    public void folderDeleted(FolderEvent e)
    {

    }

    /**
     * Invoked when a folder is renamed.
     *
     * @param    e    the FolderEvent
     */
    @Override
    public void folderRenamed(FolderEvent e)
    {

    }

    /**
     * Invoked when messages are added into a folder.
     *
     * @param    e    the MessageCountEvent
     */
    @Override
    public void messagesAdded(MessageCountEvent e)
    {
        ConsoleUtils.printMessageSubjects(e.getMessages(), "Messages Added");
    }

    /**
     * Invoked when messages are removed (expunged) from a folder.
     *
     * @param    e    the MessageCountEvent
     */
    @Override
    public void messagesRemoved(MessageCountEvent e)
    {
        ConsoleUtils.printMessageSubjects(e.getMessages(), "Messages Removed");
    }

    /**
     * Invoked when the Store generates a notification event.
     *
     * @param    e    the StoreEvent
     * @see StoreEvent#ALERT
     * @see StoreEvent#NOTICE
     */
    @Override
    public void notification(StoreEvent e)
    {
        StringUtils.createHeader("NOTIFICATION");
        System.out.println(e.getMessage());
    }

    /**
     * Invoked when a Store/Folder/Transport is opened.
     *
     * @param    e    the ConnectionEvent
     */
    @Override
    public void opened(ConnectionEvent e)
    {
        StringUtils.createHeader("CONNECTION OPENED");
    }

    /**
     * Invoked when a Store is disconnected. Note that a folder
     * cannot be disconnected, so a folder will not fire this event
     *
     * @param    e    the ConnectionEvent
     */
    @Override
    public void disconnected(ConnectionEvent e)
    {
        StringUtils.createHeader("CONNECTION DISCONNECTED");
    }

    /**
     * Invoked when a Store/Folder/Transport is closed.
     *
     * @param    e    the ConnectionEvent
     */
    @Override
    public void closed(ConnectionEvent e)
    {
        StringUtils.createHeader("CONNECTION CLOSED");
    }
}
