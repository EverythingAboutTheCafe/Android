package com.camo.app.ui.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.camo.app.common.PostAdapter
import com.camo.app.common.PostCafeAdapter
import com.camo.app.common.PostTitleAdapter
import com.camo.app.databinding.FragmentTimelineBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimelineFragment : Fragment() {

    private lateinit var binding : FragmentTimelineBinding
    private val viewModel : TimelineViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimelineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        val timelinePostAdapter = TimelinePostAdapter()

        binding.rvTimeline.adapter = timelinePostAdapter

        viewModel.posts.observe(viewLifecycleOwner) {
            timelinePostAdapter.submitList(it)
        }
    }
}