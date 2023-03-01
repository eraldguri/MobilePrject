package com.erald_guri.mobileproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erald_guri.mobileproject.data.model.PhotoModel
import com.erald_guri.mobileproject.databinding.LayoutListItemBinding
import com.erald_guri.mobileproject.databinding.LayoutLoadingBinding
import com.erald_guri.mobileproject.ui.PhotoViewHolder
import com.erald_guri.mobileproject.ui.ProgressViewHolder

class PhotoAdapter(
    private val context: Context,
    private val photos: List<PhotoModel>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            VIEW_TYPE_ITEM -> {
                val binding = LayoutListItemBinding.inflate(inflater, parent, false)
                return PhotoViewHolder(context, binding)
            }
            VIEW_TYPE_LOADING -> {
                val binding = LayoutLoadingBinding.inflate(inflater, parent, false)
                return ProgressViewHolder(binding)
            }
        }
        return null!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoViewHolder) {
            val photo = photos[position]
            holder.bind(photo)
        }
    }

    override fun getItemCount(): Int = photos.size

    override fun getItemViewType(position: Int): Int = if (photos[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM

    companion object {
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }
}