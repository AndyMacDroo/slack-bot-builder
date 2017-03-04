package com.macdonald.slack.slack.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class RealTimeMessaging
{
    private String webSocketUrl;
    private List<String> dmChannels;
    private SlackUser slackUser;

}
