package com.camo.app.ui.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

        val timelineAdapter = TimelineAdapter()
        binding.rvTimeline.adapter = timelineAdapter
        viewModel.posts.observe(viewLifecycleOwner) {
            timelineAdapter.submitList(it)
        }

    }
}