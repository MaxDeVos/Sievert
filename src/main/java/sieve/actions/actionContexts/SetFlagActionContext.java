package sieve.actions.actionContexts;

import org.apache.jsieve.mail.Action;

import java.util.ArrayList;
import java.util.List;

public class SetFlagActionContext implements Action
{
    public List<String> flags = new ArrayList<>();

    public SetFlagActionContext(List<String> flags){
        this.flags.addAll(flags);
    }

    public SetFlagActionContext(String flag){
        this.flags.add(flag);
    }

}
