package com.example.submissionandroidfundamental.presentation.detailuser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.submissionandroidfundamental.R
import com.example.submissionandroidfundamental.databinding.FragmentDetailUserBinding
import com.example.submissionandroidfundamental.utils.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailUserFragment : Fragment() {
    private lateinit var binding: FragmentDetailUserBinding
    private val args by navArgs<DetailUserFragmentArgs>()
    private val viewModel: DetailUserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailUserBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.apply {
            btnOpen.setOnClickListener {
                openGithub()
            }
        }
        setViewPager()
        setToolbar()




        return binding.root
    }

    private fun setViewPager() {
        val viewPager: ViewPager2 = binding.viewPagerHome
        val tabs: TabLayout = binding.tabLayoutHome

        viewPager.adapter =
            SectionPagerAdapter(childFragmentManager, requireActivity().lifecycle, args.userName)

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()
    }

    private fun setToolbar() {
        binding.toolbar.title = "Detail User"
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.detailUser(args.userName)

        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: DetailUserState) {
        with(binding) {

            when (state) {
                is DetailUserState.Loading -> msvDetailUser.viewState =
                    MultiStateView.ViewState.LOADING
                is DetailUserState.Success -> msvDetailUser.viewState =
                    MultiStateView.ViewState.CONTENT
                else -> {}
            }

        }

    }

    private fun openGithub() {
        val url = "https://www.github.com/${args.userName}"
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }.also {
            startActivity(it)
        }
    }

    companion object {
        private val TAB_TITLES = arrayOf(
            "Following",
            "Followers"
        )
    }


}