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
public class SlackUser
{
    private String id;
    private String name;
    private boolean deleted;
    private String color;
    @JsonProperty("is_admin")
    private boolean isAdmin;
    @JsonProperty("is_owner")
    private boolean isOwner;
    @JsonProperty("is_primary_owner")
    private boolean isPrimaryOwner;
    @JsonProperty("is_restricted")
    private boolean isRestricted;
    @JsonProperty("is_ultra_restricted")
    private boolean isUltraRestricted;
    private boolean has2fa;
    @JsonProperty("two_factor_type")
    private String twoFactorType;
    @JsonProperty("has_files")
    private boolean hasFiles;
}
