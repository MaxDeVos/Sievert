package sieve.actions.actionContexts;

import org.apache.jsieve.mail.Action;

import java.util.ArrayList;
import java.util.List;

public class RemoveFlagActionContext implements Action
{
    public List<String> flags = new ArrayList<>();

    public RemoveFlagActionContext(List<String> flags){
        this.flags.addAll(flags);
    }

    public RemoveFlagActionContext(String flag){
        this.flags.add(flag);
    }

}
