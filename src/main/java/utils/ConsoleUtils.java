package utils;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;

import java.util.List;
import java.util.StringJoiner;

public class ConsoleUtils
{

    public static void printMessageSubjects(Message[] messages){
        printMessageSubjects(messages, null);
    }

    public static void printMessageSubjects(Message[] messages, String title){
        System.out.println(StringUtils.createHeader(title));
        for(Message message : messages){
            try
            {
                System.out.println(StringUtils.sanitize(message.getSubject()));
            } catch (MessagingException ex)
            {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void printListContents(List<Object> list){
        StringJoiner joiner = new StringJoiner(" | ");
        for(Object o : list){
            joiner.add(String.valueOf(o));
        }
        System.out.println(" | " + joiner + " | ");
    }
}
