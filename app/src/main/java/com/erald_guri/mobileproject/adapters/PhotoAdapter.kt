package com.erald_guri.mobileproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erald_guri.mobileproject.data.model.PhotoModel
import com.erald_guri.mobileproject.databinding.LayoutListItemBinding
import com.erald_guri.mobileproject.databinding.LayoutLoadingBinding
import com.erald_guri.mobileproject.interfaces.OnClickListener
import com.erald_guri.mobileproject.ui.PhotoViewHolder
import com.erald_guri.mobileproject.ui.ProgressViewHolder

class PhotoAdapter(
    private val context: Context,
    private val photos: ArrayList<PhotoModel>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isLoaderEnabled = false

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoViewHolder) {
            val photo = photos[position]
            holder.bind(photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding = LayoutListItemBinding.inflate(inflater, parent, false)
            PhotoViewHolder(context, binding, onClickListener)
        } else {
            val binding = LayoutLoadingBinding.inflate(inflater, parent, false)
            ProgressViewHolder(binding)
        }
    }

    companion object {
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == photos.size - 1 && isLoaderEnabled) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    fun showLoader() {
        isLoaderEnabled = true
        //TODO: add new photo
        add(PhotoModel())
    }

    fun hideLoader() {
        isLoaderEnabled = false
        val item = getPhoto(photos.size - 1)
        if (item != null) {
            photos.removeAt(photos.size - 1)
            notifyItemRemoved(photos.size - 1)
        }
    }

    fun add(photo: PhotoModel) {
        photos.add(photo)
        notifyItemInserted(photos.size - 1)
    }

    fun addAll(results: ArrayList<PhotoModel>) {
        for (photo in results) {
            add(photo)
        }
    }

    fun getPhoto(position: Int): PhotoModel = photos[position]

}