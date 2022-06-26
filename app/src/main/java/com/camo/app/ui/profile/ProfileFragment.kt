package com.camo.app.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.camo.app.R
import com.camo.app.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private val viewModel : ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        val profileWishListAdapter = ProfileWishListAdapter()
        val profilePostTitleAdapter = ProfilePostTitleAdapter()
        val profilePostAdapter = ProfilePostAdapter()

        binding.rvProfile.adapter = ConcatAdapter(profileWishListAdapter,profilePostTitleAdapter,profilePostAdapter)

        viewModel.profile.observe(viewLifecycleOwner) {
            Log.d("getdata", "외않되:${it.toString()}")
            binding.user = it.myPage.user
            profileWishListAdapter.submitList(listOf(it.myPage.wishList))
            profilePostTitleAdapter.submitList(listOf(it.myPage.usersPosts))
            profilePostAdapter.submitList(it.myPage.usersPosts.posts)
        }
    }
}