package com.example.submissionandroidfundamental.presentation.listuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.submissionandroidfundamental.databinding.FragmentListUserBinding
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ListUserFragment : Fragment() {
    private lateinit var binding: FragmentListUserBinding
    private val viewModel: ListUserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentListUserBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.rvUsersSearch.adapter = adapterSearch
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            inToolbar.etSearch.apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {

                        viewModel?.searchUSer(query)
                        observe()
                        clearFocus()
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                })
            }

        }
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: ListUserState) {
        when (state) {
            is ListUserState.Loading -> {
                binding.msvSearch.viewState =
                    MultiStateView.ViewState.LOADING
            }
            is ListUserState.Success -> {
                binding.msvSearch.viewState =
                    if (state.dataEntity.isEmpty()) MultiStateView.ViewState.EMPTY
                    else
                        MultiStateView.ViewState.CONTENT
            }
            is ListUserState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Response Error, Silahkan Coba Lagi",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
            else -> {}
        }
    }


    private val adapterSearch: ListSearchUserAdapter
        get() {
            return ListSearchUserAdapter(ListSearchUserAdapter.OnClick {
                findNavController().navigate(
                    ListUserFragmentDirections.actionListUserFragmentToDetailUserFragment(
                        it?.login!!
                    )
                )
            })

        }


}