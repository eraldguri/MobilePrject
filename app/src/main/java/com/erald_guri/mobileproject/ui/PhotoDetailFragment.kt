package com.erald_guri.mobileproject.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.erald_guri.mobileproject.base.BaseFragment
import com.erald_guri.mobileproject.data.model.PhotoModel
import com.erald_guri.mobileproject.databinding.FragmentPhotoDetailBinding
import com.erald_guri.mobileproject.utils.ApiStatus
import com.erald_guri.mobileproject.view_models.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.InputStream
import kotlin.properties.Delegates

@AndroidEntryPoint
class PhotoDetailFragment : BaseFragment<FragmentPhotoDetailBinding>(
    FragmentPhotoDetailBinding::inflate
) {

    private val viewModel by viewModels<PhotoViewModel>()

    var photoId by Delegates.notNull<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoId = PhotoDetailFragmentArgs.fromBundle(requireArguments()).position
        normalImage()

        binding.btnNormal.setOnClickListener { normalImage() }
        binding.btnBlur.setOnClickListener { blurredImage() }
        binding.btnGrayScale.setOnClickListener { grayScaleImage() }
    }

    private fun normalImage() {
        viewModel.getPhotoNormal(photoId).observe(viewLifecycleOwner) {
            it?.let { result ->
                when (result.status) {
                    ApiStatus.SUCCESS -> {
                        val byteStream = result.data?.byteStream()
                        renderPhoto(byteStream)
                    }
                    ApiStatus.ERROR -> {
                        Log.d("error", it.message!!)
                    }
                    ApiStatus.LOADING -> {

                    }
                }
            }
        }
    }

    private fun blurredImage() {
        viewModel.getPhotoBlurred(photoId, 5).observe(viewLifecycleOwner) {
            it?.let { result ->
                when (result.status) {
                    ApiStatus.SUCCESS -> {
                        val byteStream = result.data?.byteStream()
                        renderPhoto(byteStream)
                    }
                    ApiStatus.ERROR -> {
                        Log.d("error", result.message!!)
                    }
                    ApiStatus.LOADING -> {

                    }
                }
            }
        }
    }

    private fun grayScaleImage() {
        viewModel.getPhotoGrayScaled(photoId, 2).observe(viewLifecycleOwner) {
            it?.let { result ->
                when (result.status) {
                    ApiStatus.SUCCESS -> {
                        val byteStream = result.data?.byteStream()
                        renderPhoto(byteStream)
                    }
                    ApiStatus.ERROR -> {
                        Log.d("error", result.message!!)
                    }
                    ApiStatus.LOADING -> {

                    }
                }
            }
        }
    }

    private fun renderPhoto(byteStream: InputStream?) {
        val bitmap = BitmapFactory.decodeStream(byteStream)
        Glide.with(requireContext())
            .load(bitmap)
            .override(800, 1000)
            .into(binding.imageView)
    }

}