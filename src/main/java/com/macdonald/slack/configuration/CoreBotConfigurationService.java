package com.macdonald.slack.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
@Getter
public class CoreBotConfigurationService
{
    @Value("${BOT_TOKEN}")
    private String botToken;

    @Value("${RTM_URL}")
    private String rtmUrl;
}
