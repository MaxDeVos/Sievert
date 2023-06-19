package utils;

public class StringUtils extends org.apache.commons.lang3.StringUtils
{
    public static String padTrimToLength(String input, int length){
        String out = org.apache.commons.lang3.StringUtils.abbreviate(input, length);
        return rightPad(out, length);
    }
}
