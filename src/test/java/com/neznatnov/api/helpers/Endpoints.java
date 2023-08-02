package com.neznatnov.api.helpers;

public class Endpoints {

    public static final String getCollectionsId = "/collections/{id}";
    public static final String getCollectionsPhotos = "/collections/{id}/photos";
    public static final String getPhotosRandom = "/photos/random";
    public static final String getPhotoSearch = "/search/photos";
    public static final String getPhotoStatistics = "/photos/{photoId}/statistics";
    public static final String getUserLikes = "/users/{username}/likes";
    public static final String getUserCollections = "/users/{username}/collections";
}
