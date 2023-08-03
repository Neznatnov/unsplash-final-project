package com.neznatnov.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageModule {
    private String id;
    private String slug;
    private String created_at;
    private String updated_at;
    private String promoted_at;
    private int width;
    private int height;
    private String color;
    private String blur_hash;
    private String description;
    private String alt_description;
    private int likes;
    private boolean liked_by_user;
    private UserModule user;
}
