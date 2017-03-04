package com.macdonald.slack.slack;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.macdonald.slack.configuration.CoreBotConfigurationService;
import com.macdonald.slack.slack.models.RealTimeMessaging;
import com.macdonald.slack.slack.models.SlackUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class SlackServiceConnection
{
    private CoreBotConfigurationService coreBotConfigurationService;
    private RealTimeMessaging realTimeMessaging;
    private RestTemplate restTemplate;

    @Autowired
    public SlackServiceConnection(CoreBotConfigurationService coreBotConfigurationService,
                                  RestTemplate restTemplate)
    {
        this.coreBotConfigurationService = coreBotConfigurationService;
        this.restTemplate = restTemplate;
    }

    public RealTimeMessaging startRealTimeMessaging(String slackToken)
    {
        try
        {
            realTimeMessaging = new RealTimeMessaging();
            ResponseEntity<RealTimeMessaging> response = restTemplate.getForEntity(coreBotConfigurationService.getRtmUrl(), RealTimeMessaging.class, slackToken);
            if(response.getBody() != null)
            {
                realTimeMessaging.setWebSocketUrl(response.getBody().getWebSocketUrl());
                realTimeMessaging.setDmChannels(response.getBody().getDmChannels());
                realTimeMessaging.setSlackUser(response.getBody().getSlackUser());
                log.debug(String.format("RTM connection successful. WebSocket URL: %s", realTimeMessaging.getWebSocketUrl()));
            }
            else
            {
                log.debug(String.format("RTM response invalid. Response: %s", response));
            }
        }
        catch(RestClientException e)
        {
            log.error(e.getMessage(), e);
        }

        return realTimeMessaging;

    }
}
