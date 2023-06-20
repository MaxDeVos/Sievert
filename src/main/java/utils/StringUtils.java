package utils;

import org.apache.commons.lang3.RegExUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils
{
    public static String padTrimToLength(String input, int length){
        String out = org.apache.commons.lang3.StringUtils.abbreviate(input, length);
        return rightPad(out, length);
    }

    /**
     * Returns strings with only alphanumeric characters and a dash. Removes everything else, including spaces
     * @param input unsanitized string
     * @return sanitized string
     */
    public static String sanitize(String input){
        String preRegex = input.replace(" ","");
        return RegExUtils.removeAll(preRegex, "[^A-Za-z0-9\\-]");
    }


    public static String createHeader(String title, int length)
    {
        if(title == null){
            title = "";
        }
        return StringUtils.center(title, length, '=');
    }

    public static String createHeader(String title){
            return createHeader(title, 25);
    }



}
