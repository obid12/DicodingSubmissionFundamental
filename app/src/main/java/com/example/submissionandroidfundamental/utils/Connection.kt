package com.example.submissionandroidfundamental.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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

class SectionPagerAdapterActivity(
    manager: FragmentManager, lifecycle: Lifecycle, private val username: String
) :
    FragmentStateAdapter(manager, lifecycle) {

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmnetTitleList = ArrayList<String>()

    fun clearFragment() {
        mFragmentList.clear()
        mFragmnetTitleList.clear()
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmnetTitleList.add(title)
    }

    fun getFragmentAt(position: Int): Fragment {
        return mFragmentList[position]
    }

    fun getTabName(position: Int): String {
        return mFragmnetTitleList[position]
    }

    override fun getItemCount(): Int = mFragmentList.size

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position].apply {
            arguments = Bundle().apply {
                putString(SectionPagerAdapter.ARGS_USERNAME, username)
            }
        }
    }


    companion object {
        const val ARGS_USERNAME = "username"
    }

}