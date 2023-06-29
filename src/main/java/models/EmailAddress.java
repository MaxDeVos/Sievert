package models;

import org.apache.commons.lang3.RegExUtils;
import org.apache.jsieve.mail.MailAdapter;

public class EmailAddress implements MailAdapter.Address
{

    private String localPart;
    private String domain;

    private String name;


    public EmailAddress(String emailAddress){
        if (emailAddress.contains("<") || emailAddress.contains(">")){

        }
        String[] parts = emailAddress.split("@");

    }

    /**
     * Gets the local part of the email address. Specified in <a
     * href='http://james.apache.org/server/rfclist/basic/rfc0822.txt'>RFC822</a>.
     *
     * @return local part, not null
     */
    @Override
    public String getLocalPart()
    {
        return null;
    }

    /**
     * Gets the domain part of the email address. Specified in <a
     * href='http://james.apache.org/server/rfclist/basic/rfc0822.txt'>RFC822</a>.
     *
     * @return domain, not null
     */
    @Override
    public String getDomain()
    {
        return null;
    }
}
