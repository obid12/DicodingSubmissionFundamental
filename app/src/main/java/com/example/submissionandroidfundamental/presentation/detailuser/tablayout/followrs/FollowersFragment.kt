package com.example.submissionandroidfundamental.presentation.detailuser.tablayout.followrs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.submissionandroidfundamental.databinding.FragmentFollowersBinding
import com.example.submissionandroidfundamental.presentation.listuser.ListSearchUserAdapter
import com.example.submissionandroidfundamental.utils.SectionPagerAdapter
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FollowersFragment : Fragment() {

    private lateinit var binding: FragmentFollowersBinding
    private val viewModel: FollowersViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowersBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.rvFollowers.adapter = followersAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getListFollowers(arguments?.getString(SectionPagerAdapter.ARGS_USERNAME) ?: "")
        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle).onEach { state ->
            handleState(state)
        }.launchIn(lifecycleScope)
    }

    private fun handleState(state: ListFollowersState) {
        with(binding) {

            when (state) {
                is ListFollowersState.Loading -> msvFollowers.viewState =
                    MultiStateView.ViewState.LOADING
                is ListFollowersState.Success -> {
                    msvFollowers.viewState =
                        if (state.dataEntity.isEmpty()) MultiStateView.ViewState.EMPTY
                        else MultiStateView.ViewState.CONTENT
                }

                else -> {}
            }

        }

    }

    private val followersAdapter: ListSearchUserAdapter
        get() {
            return ListSearchUserAdapter(
                ListSearchUserAdapter.OnClick {

                }
            )
        }

}