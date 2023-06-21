package sieve.actions.actionContexts;

import org.apache.jsieve.mail.Action;

import java.util.ArrayList;
import java.util.List;

public class AddFlagActionContext implements Action
{
    List<String> flags = new ArrayList<>();

    public AddFlagActionContext(List<String> flags){
        this.flags.addAll(flags);
    }

    public AddFlagActionContext(String flag){
        this.flags.add(flag);
    }

}
