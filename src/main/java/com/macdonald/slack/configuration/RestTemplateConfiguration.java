package com.macdonald.slack.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.macdonald.slack.slack.models.RealTimeMessaging;
import com.macdonald.slack.slack.models.SlackUser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.log4j.Log4j;

@Log4j
@Configuration
public class RestTemplateConfiguration
{

    @Bean
    public RestTemplate getConfiguredRestTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> httpMessageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        Jackson2ObjectMapperBuilder mapperBuilder = new Jackson2ObjectMapperBuilder();
        mapperBuilder.deserializerByType(RealTimeMessaging.class, new JsonDeserializer<RealTimeMessaging>()
        {
            @Override
            public RealTimeMessaging deserialize(JsonParser p, DeserializationContext ctxt)
            {
                try
                {
                    final ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode node = p.readValueAsTree();
                    RealTimeMessaging realTimeMessaging = new RealTimeMessaging();
                    realTimeMessaging.setWebSocketUrl(node.get("url").asText());
                    realTimeMessaging.setSlackUser(objectMapper.treeToValue(node.get("self"), SlackUser.class));
                    List<String> dmChannels = new ArrayList<>();
                    for(JsonNode jsonNode : node.get("ims"))
                    {
                        dmChannels.add(jsonNode.get("id").asText());
                    }
                    realTimeMessaging.setDmChannels(dmChannels);
                    List<SlackUser> users = new ArrayList<>();
                    for(JsonNode jsonNode : node.get("users"))
                    {
                        users.add(objectMapper.treeToValue(jsonNode, SlackUser.class));
                    }
                    return realTimeMessaging;
                }
                catch(Exception e)
                {
                    log.error(e.getMessage(), e);
                    return null;
                }
            }
        });
        jsonConverter.setObjectMapper(mapperBuilder.build());
        httpMessageConverters.add(jsonConverter);
        restTemplate.setMessageConverters(httpMessageConverters);
        return restTemplate;
    }


}
