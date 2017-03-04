package com.macdonald.slack.slack.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class Channel {
    private String id;
    private String name;
    private String user;
    private long created;
    private String creator;
    @JsonProperty("is_im")
    boolean isIm;
    @JsonProperty("is_org_shared")
    boolean isOrgShared;
    @JsonProperty("is_user_deleted")
    boolean isUserDeleted;
}
