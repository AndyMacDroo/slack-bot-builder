package com.macdonald.slack.messaging.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BotResponse
{
    private int id;
    private String type;
    private String channel;
    private String user;
    private String text;

    public String toJSONString() throws JsonProcessingException
    {
        return (new ObjectMapper()).writeValueAsString(this);
    }
}
