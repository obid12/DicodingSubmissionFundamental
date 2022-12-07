package com.example.submissionandroidfundamental

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.submissionandroidfundamental.DetailUserActivity.Companion.EXTRA_DETAIL
import com.example.submissionandroidfundamental.databinding.ActivityMainBinding
import com.example.submissionandroidfundamental.utils.bindRecyclerViewListHero

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            rvUsers.adapter = ListUserAdapter(
                ListUserAdapter.OnClick {
                    toDetailUser(it)
                }
            )
            list.addAll(listUsers)

            inToolbar.etSearch.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    (rvUsers.adapter as ListUserAdapter).filter(query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {

                    (rvUsers.adapter as ListUserAdapter).filter(newText)
                    return true
                }
            })

            inToolbar.ivProfile.setOnClickListener {
                val userProfile = User(
                    "obid12", "L. Gilang Obidia Ramdhani",
                    "Malang", 10, "Tokoved", 10, 20, R.drawable.user11
                )
                toDetailUser(userProfile)
            }

            bindRecyclerViewListHero(rvUsers, list)
        }


        setContentView(binding.root)
    }

    private fun toDetailUser(user: User?) {
        Intent(this@MainActivity, DetailUserActivity::class.java).apply {
            putExtra(EXTRA_DETAIL, user)
        }.also {
            startActivity(it)
        }
    }

    private val listUsers: MutableList<User>
        @SuppressLint("Recycle")
        get() {
            val dataUsername = resources.getStringArray(R.array.username)
            val dataName = resources.getStringArray(R.array.name)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataRepository = resources.getStringArray(R.array.repository)
            val dataCompany = resources.getStringArray(R.array.company)
            val dataFollowers = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)
            val dataAvatar = resources.obtainTypedArray(R.array.avatar)

            val listUser = mutableListOf<User>()
            for (i in dataName.indices) {
                val user = User(
                    dataUsername[i],
                    dataName[i],
                    dataLocation[i],
                    dataRepository[i].toInt(),
                    dataCompany[i],
                    dataFollowers[i].toInt(),
                    dataFollowing[i].toInt(),
                    dataAvatar.getResourceId(i, -1)
                )
                listUser.add(user)
            }
            return listUser

        }
}