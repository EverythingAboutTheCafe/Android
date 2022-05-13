package com.camo.app.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("post_id") val postId :String,
    val title :Title,
    @SerializedName("post_images")val postImages: List<Images>,
    val content: Content,
    val cafe: Cafe
)

data class Title (
    val user :User,
    @SerializedName("upload_time") val uploadTime: String
)

data class User (
    @SerializedName("user_name") val userName: String,
    @SerializedName("user_profile_image_url") val userProfileImageUrl: String,
)

data class Images (
    @SerializedName("post_image_url") var postImageUrl: String
)

data class Content (
    val label: String,
    @SerializedName("visit_time") val visitTime: String,
    val congestion: Int
)

data class Cafe (
    @SerializedName("cafe_name") val cafeName: String,
    @SerializedName("cafe_address") val cafeAddress: String,
    @SerializedName("cafe_like") val cafeLike: Boolean
)