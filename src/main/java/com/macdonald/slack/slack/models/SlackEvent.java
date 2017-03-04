package com.macdonald.slack.slack.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class SlackEvent
{
    private static final Logger logger = LoggerFactory.getLogger(SlackEvent.class);
    private int id;
    private String type;
    @JsonProperty("channel_id")
    private String channelId;
    @JsonProperty("user_id")
    private String userId;
    private String text;

    private Error error;
    private boolean ok;
    @JsonProperty("reply_to")
    private int replyTo;
    @JsonProperty("is_starred")
    private boolean isStarred;
    @JsonProperty("pinned_to")
    private String[] pinnedTo;
    private Channel channel;

    private File file;
    @JsonProperty("file_id")
    private String fileId;
    private SlackUser slackUser;
    @JsonProperty("has_pins")
    private boolean hasPins;
    private Reaction[] reactions;
    private boolean upload;
    private boolean hidden;
    private String latest;
    private String presence;
    private Comment comment;
    @JsonProperty("comment_id")
    private String commentId;
    private String reaction;
    @JsonProperty("item_user")
    private String itemUser;
    private String[] names;
    private String value;
    private String plan;
    private String url;
    private String domain;
    @JsonProperty("email_domain")
    private String emailDomain;

    @JsonProperty("subteam_id")
    private String subteamId;

    private String ts;
    @JsonProperty("deleted_ts")
    private String deletedTs;
    @JsonProperty("event_ts")
    private String eventTs;

    @JsonSetter("channel")
    public void setChannel(JsonNode jsonNode) {
        if (jsonNode.isObject()) {
            try {
                this.channel = new ObjectMapper().treeToValue(jsonNode, Channel.class);
            } catch (JsonProcessingException e) {
                logger.error("Error deserializing json: {}", e.getMessage());
            }
        } else if (jsonNode.isTextual()) {
            this.channelId = jsonNode.asText();
        }
    }

    @JsonSetter("file")
    public void setFile(JsonNode jsonNode) {
        if (jsonNode.isObject()) {
            try {
                this.file = new ObjectMapper().treeToValue(jsonNode, File.class);
            } catch (JsonProcessingException e) {
                logger.error("Error deserializing json: {}", e.getMessage());
            }
        } else if (jsonNode.isTextual()) {
            this.fileId = jsonNode.asText();
        }
    }

    @JsonSetter("comment")
    public void setComment(JsonNode jsonNode) {
        if (jsonNode.isObject()) {
            try {
                this.comment = new ObjectMapper().treeToValue(jsonNode, Comment.class);
            } catch (JsonProcessingException e) {
                logger.error("Error deserializing json: {}", e.getMessage());
            }
        } else if (jsonNode.isTextual()) {
            this.commentId = jsonNode.asText();
        }
    }

    @JsonSetter("user")
    public void setUser(JsonNode jsonNode) {
        if (jsonNode.isObject()) {
            try {
                this.slackUser = new ObjectMapper().treeToValue(jsonNode, SlackUser.class);
            } catch (JsonProcessingException e) {
                logger.error("Error deserializing json: {}", e.getMessage());
            }
        } else if (jsonNode.isTextual()) {
            this.userId = jsonNode.asText();
        }
    }

}
