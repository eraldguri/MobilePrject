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
import com.erald_guri.mobileproject.view_models.PhotoViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment : BaseFragment<FragmentPhotoBinding>(
    FragmentPhotoBinding::inflate
) {

    private val viewModel by viewModels<PhotoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPhotos().observe(viewLifecycleOwner) {
            it?.let { result ->
                when (result.status) {
                    ApiStatus.SUCCESS -> {
                        setupRecyclerView(it.data?.body())
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

    private fun setupRecyclerView(photos: List<PhotoModel>?) {
        val photoAdapter = PhotoAdapter(requireContext(), photos!!, onItemClickListener)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = photoAdapter
        }
    }

    private val onItemClickListener = object: OnClickListener {
        override fun onItemClick(photo: PhotoModel) {
            val action = PhotoFragmentDirections.actionPhotoFragmentToPhotoDetailFragment(photo.id)
            findNavController().navigate(action)
        }

    }

}