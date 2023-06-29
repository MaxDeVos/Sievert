package utils;

import jakarta.mail.Flags;

import java.util.*;

public class FlagUtils
{

    public static List<String> getAllFlagStrings(Flags flags){
        List<String> out = new LinkedList<>(Arrays.stream(flags.getUserFlags()).toList());
        for(Flags.Flag flag : flags.getSystemFlags()){
            out.add(getSystemFlagName(flag));
        }
        return out;
    }

    public static boolean isFlagNameASystemFlag(String flagName){
        flagName = flagName.replace("\\\\","");
        return switch (flagName.toUpperCase(Locale.ENGLISH))
            {
                case "SEEN", "FLAGGED", "DELETED", "ANSWERED", "DRAFT", "RECENT" -> true;
                default ->  false;
            };
    }

    public static Flags.Flag systemFlagFactory(String flagName)
    {

        return switch (flagName)
            {
                case "SEEN", "\\\\SEEN" -> Flags.Flag.SEEN;
                case "FLAGGED", "\\\\FLAGGED" -> Flags.Flag.FLAGGED;
                case "DELETED", "\\\\DELETED" -> Flags.Flag.DELETED;
                case "ANSWERED", "\\\\ANSWERED" -> Flags.Flag.ANSWERED;
                case "DRAFT", "\\\\DRAFT" -> Flags.Flag.DRAFT;
                case "RECENT", "\\\\RECENT" -> Flags.Flag.RECENT;
                default -> Flags.Flag.USER;
            };
    }

    public static String getSystemFlagName(Flags.Flag flag){
        if(flag.equals(Flags.Flag.SEEN)){
            return "\\\\SEEN";
        }
        else if(flag.equals(Flags.Flag.FLAGGED)){
            return "\\\\FLAGGED";
        }
        else if(flag.equals(Flags.Flag.DELETED)){
            return "\\\\DELETED";
        }
        else if(flag.equals(Flags.Flag.ANSWERED)){
            return "\\\\ANSWERED";
        }
        else if(flag.equals(Flags.Flag.DRAFT)){
            return "\\\\DRAFT";
        }
        else if(flag.equals(Flags.Flag.RECENT)){
            return "\\\\RECENT";
        }
        throw new IllegalStateException("NOT A SYSTEM FLAG");
    }

}
