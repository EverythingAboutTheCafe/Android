package com.camo.app.repository

import android.content.Context
import android.util.Log
import com.camo.app.AssetLoader
import com.camo.app.model.Profile
import com.google.gson.Gson
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val context:Context
) {

    private val gson = Gson()
    private val assetLoader = AssetLoader(context)

    fun getProfile(): Profile? {
        val profileJsonString = assetLoader.getJsonString("profile.json")
        val profileData = gson.fromJson(profileJsonString, Profile::class.java)
        return profileData
    }
}