package com.macdonald.slack.slack.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class Comment {
    private String id;
    private long created;
    private long timestamp;
    private String user;
    private String comment;
    private String channel;

}
