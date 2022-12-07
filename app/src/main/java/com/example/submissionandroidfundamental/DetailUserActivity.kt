package com.example.submissionandroidfundamental

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.submissionandroidfundamental.databinding.ActivityDetailUserBinding


@Suppress("DEPRECATION")
class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.extras?.get(EXTRA_DETAIL) as User
        binding.data = user
        binding.apply {
            toolbar.title = "Detail User"
            toolbar.setNavigationIcon(R.drawable.ic_back)
            toolbar.setNavigationOnClickListener {
                finish()
            }
            btnOpen.setOnClickListener {
                openGithub()
            }
        }
    }

    private fun openGithub() {
        val url = "https://www.github.com/${user.username}"
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }.also {
            startActivity(it)
        }
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}