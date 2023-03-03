package com.erald_guri.mobileproject.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erald_guri.mobileproject.data.model.PhotoModel
import com.erald_guri.mobileproject.databinding.LayoutListItemBinding
import com.erald_guri.mobileproject.interfaces.OnClickListener

class PhotoViewHolder(
    private val context: Context,
    private val binding: LayoutListItemBinding,
    private val onClickListener: OnClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: PhotoModel) {
        binding.apply {
            tvAuthor.text = photo.author
            Glide.with(context)
                .load(photo.downloadUrl)
                .override(400, 400)
                .into(imageView)

            root.setOnClickListener {
                onClickListener.onItemClick(photo)
            }
        }
    }

}