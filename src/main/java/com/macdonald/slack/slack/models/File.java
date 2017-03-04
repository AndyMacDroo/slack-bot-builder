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
public class File
{
    private String id;
    private long created;
    private long timestamp;
    private String name;
    private String title;
    private String mimetype;
    private String filetype;
    @JsonProperty("pretty_type")
    private String prettyType;
    private String user;
    private String mode;
    private boolean editable;
    @JsonProperty("is_external")
    private boolean isExternal;
    @JsonProperty("external_type")
    private String externalType;
    private String username;
    private int size;
    @JsonProperty("url_private")
    private String urlPrivate;
    @JsonProperty("url_private_download")
    private String urlPrivateDownload;
    private String thumb64;
    private String thumb80;
    private String thumb360;
    @JsonProperty("thumb360_gif")
    private String thumb360Gif;
    @JsonProperty("thumb360_w")
    private String thumb360W;
    @JsonProperty("thumb360_h")
    private String thumb360H;
    private String thumb480;
    @JsonProperty("thumb480_w")
    private String thumb480W;
    @JsonProperty("thumb480_h")
    private String thumb480H;
    private String thumb160;
    private String permalink;
    @JsonProperty("permalink_public")
    private String permalinkPublic;
    @JsonProperty("edit_link")
    private String editLink;
    private String preview;
    @JsonProperty("preview_highlight")
    private String previewHighlight;
    private int lines;
    @JsonProperty("lines_more")
    private int linesMore;
    @JsonProperty("is_public")
    private boolean isPublic;
    @JsonProperty("public_url_shared")
    private boolean publicUrlShared;
    @JsonProperty("display_as_bot")
    private boolean displayAsBot;
    private String[] channels;
    private String[] groups;
    private String[] ims;
    @JsonProperty("initial_comment")
    private Comment initialComment;
    @JsonProperty("num_stars")
    private int numStars;
    @JsonProperty("is_starred")
    private boolean isStarred;
    @JsonProperty("pinned_to")
    private String[] pinnedTo;
    private Reaction reactions;
    @JsonProperty("comments_count")
    private int commentsCount;

}
