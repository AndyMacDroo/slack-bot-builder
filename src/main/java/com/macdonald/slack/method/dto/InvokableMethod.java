package com.macdonald.slack.method.dto;

import com.macdonald.slack.slack.SlackEventType;

import java.lang.reflect.Method;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class InvokableMethod
{
    private Method method;
    private String regexPattern;
    private boolean conversationAware;
    private SlackEventType eventTrigger;
    private Class[] parameterClassArray;
}
