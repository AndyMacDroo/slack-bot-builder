package com.macdonald.slack.socket;


import com.macdonald.slack.messaging.dto.BotResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Service
public class SlackMessagePostService
{
    public void postMessage(BotResponse response, WebSocketSession session) throws IOException
    {
        if(response != null)
        {
            session.sendMessage(new TextMessage(response.toJSONString()));
        }
    }
}
