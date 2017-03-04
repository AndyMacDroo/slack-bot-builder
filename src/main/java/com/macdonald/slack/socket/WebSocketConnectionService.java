package com.macdonald.slack.socket;


import com.macdonald.slack.bot.SlackWebSocketHandler;
import com.macdonald.slack.configuration.CoreBotConfigurationService;
import com.macdonald.slack.slack.SlackServiceConnection;
import com.macdonald.slack.slack.models.RealTimeMessaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.annotation.PostConstruct;

@Service
public class WebSocketConnectionService
{
    private SlackServiceConnection slackServiceConnection;
    private RealTimeMessaging realTimeMessaging;
    private WebSocketHandler slackWebSocketHandler;

    @Autowired
    public WebSocketConnectionService(SlackServiceConnection connection, SlackWebSocketHandler slackWebSocketHandler,
                                      CoreBotConfigurationService coreBotConfigurationService)
    {
        this.slackServiceConnection = connection;
        this.slackWebSocketHandler = slackWebSocketHandler;
        realTimeMessaging = slackServiceConnection.startRealTimeMessaging(coreBotConfigurationService.getBotToken());
        makeConnection();
    }

    @PostConstruct
    private void makeConnection()
    {
        WebSocketConnectionManager manager = new WebSocketConnectionManager(client(), slackWebSocketHandler, realTimeMessaging.getWebSocketUrl());
        manager.start();
    }


    WebSocketClient client()
    {
        return new StandardWebSocketClient();
    }

}
