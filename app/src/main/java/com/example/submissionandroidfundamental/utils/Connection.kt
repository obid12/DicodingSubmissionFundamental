package com.example.submissionandroidfundamental.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.submissionandroidfundamental.presentation.detailuser.tablayout.following.FollowingFragment
import com.example.submissionandroidfundamental.presentation.detailuser.tablayout.followrs.FollowersFragment


class SectionPagerAdapter(
    fragmentManager: androidx.fragment.app.FragmentManager,
    lifecycle: Lifecycle, private val username: String
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FollowingFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARGS_USERNAME, username)
                    }
                }
            }
            else -> {
                FollowersFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARGS_USERNAME, username)
                    }
                }
            }
        }
    }

    companion object {
        const val ARGS_USERNAME = "username"
    }

}