package com.macdonald.slack.impl;

import com.macdonald.slack.annotation.SlackBotResponseHandler;
import com.macdonald.slack.messaging.dto.BotResponse;
import com.macdonald.slack.messaging.dto.SlackMessage;
import com.macdonald.slack.slack.SlackEventType;

import org.springframework.stereotype.Component;

@Component
public class MyBotImpl
{
    @SlackBotResponseHandler(regexPattern = "Hello", eventTrigger = SlackEventType.MESSAGE)
    public BotResponse replyToMessage(final SlackMessage message)
    {
        final BotResponse botResponse = BotResponse.builder()
            .text("hello <@" + message.getUser() + ">, how are you today?")
            .type("message")
            .id(1)
            .user(message.getUser()).channel(message.getChannel()).build();
        return botResponse;
    }

}
