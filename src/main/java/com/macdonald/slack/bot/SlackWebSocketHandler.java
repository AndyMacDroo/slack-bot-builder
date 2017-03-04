package com.macdonald.slack.bot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.macdonald.slack.messaging.dto.BotResponse;
import com.macdonald.slack.messaging.dto.SlackMessage;
import com.macdonald.slack.slack.models.SlackEvent;
import com.macdonald.slack.socket.SlackMessagePostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.List;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class SlackWebSocketHandler extends AbstractWebSocketHandler
{

    private SlackMessageProcessor messageHandler;
    private SlackMessagePostService slackMessagePostService;

    @Autowired
    public SlackWebSocketHandler(SlackMessageProcessor messageHandler, SlackMessagePostService slackMessagePostService)
    {
        this.messageHandler = messageHandler;
        this.slackMessagePostService = slackMessagePostService;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception
    {
        try
        {
            SlackEvent slackEvent = new ObjectMapper().readValue(textMessage.getPayload(), SlackEvent.class);
            SlackMessage.SlackMessageBuilder slackMessage = SlackMessage.builder();
            if(slackEvent.getText() != null)
            {
                slackMessage
                    .message(slackEvent.getText());
            }
            if(slackEvent.getChannelId() != null)
            {
                slackMessage
                    .channel(slackEvent.getChannelId());
            }
            if(slackEvent.getUserId() != null)
            {
                slackMessage
                    .user(slackEvent.getUserId());
            }
            if(slackEvent.getType() != null)
            {
                slackMessage
                    .eventType(slackEvent.getType());
            }

            List<BotResponse> botResponseList = messageHandler.processSlackMessage(slackMessage.build());
            botResponseList.forEach((BotResponse response) ->
                postMessage(session, response));
        }
        catch(Exception e)
        {
            log.debug(e.getMessage(), e);
        }
    }

    private void postMessage(WebSocketSession session, BotResponse response)
    {
        try
        {
            slackMessagePostService.postMessage(response, session);
        }
        catch(IOException e)
        {
            log.debug(e.getMessage(), e);
        }
    }

}
