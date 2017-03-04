package com.macdonald.slack.annotation;

import com.macdonald.slack.slack.SlackEventType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SlackBotResponseHandler
{
    String regexPattern () default ".*";
    SlackEventType eventTrigger ();
}
