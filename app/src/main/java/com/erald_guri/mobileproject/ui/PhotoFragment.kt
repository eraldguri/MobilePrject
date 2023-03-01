package com.erald_guri.mobileproject.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.erald_guri.mobileproject.base.BaseFragment
import com.erald_guri.mobileproject.databinding.FragmentPhotoBinding
import com.erald_guri.mobileproject.utils.ApiStatus
import com.erald_guri.mobileproject.view_models.PhotoViewModel
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

                    }
                    ApiStatus.ERROR -> {

                    }
                    ApiStatus.LOADING -> {

                    }
                }
            }
        }
    }

}