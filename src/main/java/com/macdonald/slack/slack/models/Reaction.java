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
public class Reaction {
    private String name;
    private int count;
    private String[] users;

}