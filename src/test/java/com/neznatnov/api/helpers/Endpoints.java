package com.neznatnov.api.helpers;

public class Endpoints {
    public static final String GET_COLLECTIONS_ID = "/collections/{id}";
    public static final String GET_COLLECTIONS_PHOTOS = "/collections/{id}/photos";
    public static final String GET_PHOTOS_RANDOM = "/photos/random";
    public static final String GET_PHOTO_SEARCH = "/search/photos";
    public static final String GET_PHOTO_STATISTICS = "/photos/{photoId}/statistics";
    public static final String GET_USER_LIKES = "/users/{username}/likes";
    public static final String GET_USER_COLLECTIONS = "/users/{username}/collections";
}
