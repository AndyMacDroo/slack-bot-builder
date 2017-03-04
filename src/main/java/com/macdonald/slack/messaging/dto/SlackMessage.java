package com.macdonald.slack.messaging.dto;


import com.macdonald.slack.slack.SlackEventType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SlackMessage
{
    private int id;
    private String message;
    private String user;
    private String channel;
    private String eventType;
}
