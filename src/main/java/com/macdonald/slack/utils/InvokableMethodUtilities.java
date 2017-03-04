package com.macdonald.slack.utils;

import com.macdonald.slack.messaging.dto.SlackMessage;
import com.macdonald.slack.method.dto.InvokableMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InvokableMethodUtilities
{

    private InvokableMethodUtilities()
    {

    }

    public static List<InvokableMethod> getMatchingInvokableMethods(final SlackMessage slackMessage, List<InvokableMethod> invokableMethods)
    {
        if(slackMessage.getMessage() != null)
        {
            return invokableMethods.stream()
                .filter(invokableMethod -> matchesRegex(slackMessage.getMessage(), invokableMethod.getRegexPattern()))
                .collect(Collectors.toList());
        }
        else
        {
            return new ArrayList<>();
        }
    }

    private static boolean matchesRegex(String message, String regexPattern)
    {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(message);
        return matcher.matches();
    }
}
