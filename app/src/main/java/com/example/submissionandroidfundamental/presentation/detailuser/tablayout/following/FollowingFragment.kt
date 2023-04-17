package com.example.submissionandroidfundamental.presentation.detailuser.tablayout.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.submissionandroidfundamental.databinding.FragmentFollowingBinding
import com.example.submissionandroidfundamental.utils.SectionPagerAdapter.Companion.ARGS_USERNAME
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FollowingFragment : Fragment() {
    private lateinit var binding: FragmentFollowingBinding
    private val viewModel: FollowingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowingBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.rvFollowing.adapter = followingAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getListFollowing(arguments?.getString(ARGS_USERNAME) ?: "")
        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle).onEach { state ->
            handleState(state)
        }.launchIn(lifecycleScope)
    }

    private fun handleState(state: ListFollowingState) {
        with(binding) {
            when (state) {
                is ListFollowingState.Loading -> msvFollowing.viewState =
                    MultiStateView.ViewState.LOADING
                is ListFollowingState.Success -> {
                    msvFollowing.viewState =
                        if (state.dataEntity.isEmpty()) MultiStateView.ViewState.EMPTY
                        else MultiStateView.ViewState.CONTENT
                }

                else -> {}
            }
        }

    }

    private val followingAdapter: ListFollowingUserAdapter
        get() {
            return ListFollowingUserAdapter()
        }

}