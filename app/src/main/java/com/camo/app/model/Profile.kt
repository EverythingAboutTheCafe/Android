package com.camo.app.model

import com.google.gson.annotations.SerializedName

data class Profile (
    @SerializedName("my_page") val myPage:MyPage
)

data class MyPage (
    val user: User,
    @SerializedName("wish_list") val wishList: WishList,
    @SerializedName("users_posts") val usersPosts: UsersPosts
)

data class WishList (
    @SerializedName("wish_list_title") val wishListTitle: String,
    val cafe: List<Cafe>
)

data class UsersPosts (
    @SerializedName("users_posts_title") val usersPostsTitle: String,
    val posts: List<Post>
)
