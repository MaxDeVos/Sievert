package models;

import jakarta.mail.Flags;
import jakarta.mail.MessagingException;
import utils.FlagUtils;

import java.util.Collection;
import java.util.StringJoiner;

public class IMAPFlags extends Flags
{

    public IMAPFlags(Collection<String> flagStrings){
        super();
        for(String flagName : flagStrings){
            if(FlagUtils.isFlagNameASystemFlag(flagName)){
                add(FlagUtils.systemFlagFactory(flagName));
            }
            else{
                // TODO this is pretty dumb, account for all illegal characters and throw exception
                if(flagName.startsWith("[") && flagName.endsWith("]")){
                    flagName = flagName.substring(1,flagName.length() - 1);
                }
                add(flagName);
            }
        }
    }
    public IMAPFlags(Flags newFlags){
        super();
        add(newFlags);
    }

    public IMAPFlags(){
        super();
    }

    public String toString(){
        StringJoiner joiner = new StringJoiner(" | ");
        FlagUtils.getAllFlagStrings(this).forEach(joiner::add);
        return joiner.toString();
    }

}
