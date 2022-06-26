package com.camo.app.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.camo.app.model.*
import com.camo.app.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile> = _profile

    init {
        loadProfileData()
    }

    private fun loadProfileData() {
        val profileData = profileRepository.getProfile()
        profileData?.let { data ->
            _profile.value = data
            _user.value = data.myPage.user
        }
    }
}