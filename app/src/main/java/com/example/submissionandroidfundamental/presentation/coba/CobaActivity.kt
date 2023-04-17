package com.example.submissionandroidfundamental.presentation.coba

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.example.submissionandroidfundamental.R
import com.example.submissionandroidfundamental.databinding.ActivityCobaBinding
import com.example.submissionandroidfundamental.presentation.coba.tab.CobaTabFragment
import com.example.submissionandroidfundamental.presentation.detailuser.DetailUserFragmentArgs
import com.example.submissionandroidfundamental.presentation.detailuser.DetailUserState
import com.example.submissionandroidfundamental.presentation.detailuser.DetailUserViewModel
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class CobaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCobaBinding
    private val args by navArgs<DetailUserFragmentArgs>()
    private val viewModel: DetailUserViewModel by viewModels()
    private lateinit var page: CobaActivity

    @Inject
    lateinit var model: CobaActivityModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCobaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.apply {
            btnOpen.setOnClickListener {
                openGithub()
            }
        }
        setTabView()
        setToolbar()

        viewModel.detailUser(args.userName)

        observe()
    }

    private fun setTabView() {
        model.run {
            val data = data?.items
            val tabFragment = CobaTabFragment.newInstance(
                transform(data)
            )

            attachFragment(binding.frame, tabFragment)
        }
    }

    private fun setToolbar() {
        binding.toolbar.title = "Detail User"
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar.setNavigationOnClickListener {
            findNavController(R.id.listUserFragment).navigateUp()
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

    private fun attachFragment(root: ViewGroup, fragment: Fragment, delay: Long? = 0) {
        root.postDelayed(
            {
                try {
                    val tag = fragment::class.java.simpleName
                    page.supportFragmentManager.beginTransaction().replace(root.id, fragment, tag)
                        .commitAllowingStateLoss()
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }, delay ?: 0
        )
    }
}