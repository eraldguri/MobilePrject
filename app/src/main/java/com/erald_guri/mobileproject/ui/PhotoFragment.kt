package com.erald_guri.mobileproject.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.erald_guri.mobileproject.adapters.PhotoAdapter
import com.erald_guri.mobileproject.base.BaseFragment
import com.erald_guri.mobileproject.data.model.PhotoModel
import com.erald_guri.mobileproject.databinding.FragmentPhotoBinding
import com.erald_guri.mobileproject.interfaces.OnClickListener
import com.erald_guri.mobileproject.utils.ApiStatus
import com.erald_guri.mobileproject.utils.RecyclerViewScrollListener
import com.erald_guri.mobileproject.view_models.PhotoViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment : BaseFragment<FragmentPhotoBinding>(
    FragmentPhotoBinding::inflate
) {

    private val viewModel by viewModels<PhotoViewModel>()
    private lateinit var photoAdapter: PhotoAdapter

    private var currentPage = PAGE_START_INDEX

    private var isLoading = false
    private var isLastPage = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFirstPage()
    }

    private fun loadFirstPage() {
        viewModel.getPhotos(currentPage, 20).observe(viewLifecycleOwner) {
            it?.let { result ->
                when (result.status) {
                    ApiStatus.SUCCESS -> {
                        val data = ArrayList<PhotoModel>()
                        data.addAll(result.data?.body()!!)

                        photoAdapter = PhotoAdapter(requireContext(), data, onItemClickListener)

                        if (currentPage <= TOTAL_PAGES) {
                            photoAdapter.showLoader()
                        } else {
                            isLastPage = true
                        }

                        val linearLayoutManager = LinearLayoutManager(requireContext())
                        binding.recycler.apply {
                            layoutManager = linearLayoutManager
                            adapter = photoAdapter
                            addOnScrollListener(object: RecyclerViewScrollListener(linearLayoutManager) {
                                override fun loadMore() {
                                    isLoading = true
                                    currentPage += 1
                                    loadNextPage(currentPage)
                                }

                                override fun isLoading(): Boolean {
                                    return isLoading
                                }

                                override fun isLastPage(): Boolean {
                                    return isLastPage
                                }

                            })
                        }
                    }
                    ApiStatus.ERROR -> {
                        Snackbar.make(binding.root, it.message!!, Snackbar.LENGTH_SHORT).show()
                    }
                    ApiStatus.LOADING -> {

                    }
                }
            }
        }
    }

    private fun loadNextPage(page: Int) {
        viewModel.getPhotos(page, 20).observe(viewLifecycleOwner) {
            it?.let { result ->
                when (result.status) {
                    ApiStatus.SUCCESS -> {
                        photoAdapter.hideLoader()
                        isLoading = false

                        val data = ArrayList<PhotoModel>()
                        data.addAll(result.data?.body()!!)
                        photoAdapter.addAll(data)

                        if (currentPage != TOTAL_PAGES) {
                            photoAdapter.showLoader()
                        } else {
                            isLastPage = true
                        }
                    }
                    ApiStatus.ERROR -> {
                        Snackbar.make(binding.root, it.message!!, Snackbar.LENGTH_SHORT).show()
                    }
                    ApiStatus.LOADING -> {

                    }
                }
            }
        }
    }

    private val onItemClickListener = object: OnClickListener {
        override fun onItemClick(photo: PhotoModel) {
            val action = PhotoFragmentDirections.actionPhotoFragmentToPhotoDetailFragment(photo.id)
            findNavController().navigate(action)
        }

    }

    companion object {
        const val PAGE_START_INDEX = 1
        const val TOTAL_PAGES = 20
    }

}