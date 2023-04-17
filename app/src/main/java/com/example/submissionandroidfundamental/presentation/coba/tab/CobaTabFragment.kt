package com.example.submissionandroidfundamental.presentation.coba.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.submissionandroidfundamental.databinding.FragmentCobaTabBinding
import com.example.submissionandroidfundamental.presentation.coba.UserItemModel
import com.example.submissionandroidfundamental.presentation.detailuser.DetailUserFragmentArgs
import com.example.submissionandroidfundamental.presentation.detailuser.tablayout.following.FollowingFragment
import com.example.submissionandroidfundamental.presentation.detailuser.tablayout.followrs.FollowersFragment
import com.example.submissionandroidfundamental.utils.SectionPagerAdapterActivity
import com.google.android.material.tabs.TabLayoutMediator

class CobaTabFragment : Fragment() {

    private lateinit var binding: FragmentCobaTabBinding
    private var adapterTabView: SectionPagerAdapterActivity? = null
    private val args by navArgs<DetailUserFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCobaTabBinding.inflate(layoutInflater, container, false)
        setViewPager()
        return binding.root
    }

    private fun setViewPager() {
        adapterTabView =
            SectionPagerAdapterActivity(childFragmentManager, lifecycle, args.userName).apply {
                clearFragment()
                addFragment(FollowersFragment(), "Follower")
                addFragment(FollowingFragment(), "Following")

            }

        binding.viewPagerHome.apply {
            adapter = adapterTabView
            offscreenPageLimit = 2
        }

        binding.apply {
            TabLayoutMediator(tabLayoutHome, viewPagerHome) { tab, position ->
                tab.text = adapterTabView?.getTabName(position)
            }.attach()
            viewPagerHome.apply {
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        tabLayoutHome.selectTab(tabLayoutHome.getTabAt(position))
                    }
                })
            }
        }
    }

    companion object {
        private const val ARGS_LIST_USER = "ARGS_LIST_USER"
        fun newInstance(
            listUser: ArrayList<UserItemModel> = arrayListOf()
        ): CobaTabFragment {
            return CobaTabFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARGS_LIST_USER, listUser)
                }
            }
        }
    }
}