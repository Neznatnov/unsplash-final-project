package com.neznatnov.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionModule {
    private String id;
    private String title;
    private String description;
    private String published_at;
    private String last_collected_at;
    private String updated_at;
    private boolean featured;
    private int total_photos;
    private boolean private_collection;
    private String share_key;
    private UserModule user;
}
