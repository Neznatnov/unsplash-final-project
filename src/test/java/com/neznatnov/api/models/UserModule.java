package com.neznatnov.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModule {
    private String id;
    private String updated_at;
    private String username;
    private String name;
    private String first_name;
    private String last_name;
    private String bio;
    private String location;
    private String instagram_username;
    private int total_collections;
    private int total_likes;
    private int total_photos;
    private boolean accepted_tos;
    private boolean for_hire;
}
