package com.example.wastewise.ui.booklet

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.wastewise.data.remote.response.booklet.Booklet
import com.example.wastewise.databinding.CardBookletBinding

class BookletAdapter : ListAdapter<Booklet, BookletAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private var originalList: List<Booklet>? = null
    private lateinit var onItemClickCallback: OnItemClickCallback

    init {
        originalList = currentList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CardBookletBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)

        holder.cvItemBooklet.setOnClickListener {
            review?.let { onItemClickCallback.onItemClicked(it) }



        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class MyViewHolder (private val binding: CardBookletBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        private var onItemClickCallback: OnItemClickCallback? = null
        val cvItemBooklet: CardView = binding.cvItemBooklet
        val isOrganic: Boolean = false

        fun bind(user: Booklet) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(user.image)
                    .into(ivItemPhotoBooklet)
                tvTitleBooklet.text = user.name
                tvDescriptionBooklet.text = user.description
                Log.d("BookletAdapter", "bind: ${user.name}")
                Log.d("BookletAdapter", "bind: ${user.type}")
                tvCategoryBooklet.text = user.type
            }
        }

    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Booklet)
    }

    companion object {
        private val DIFF_CALLBACK = object : androidx.recyclerview.widget.DiffUtil.ItemCallback<Booklet>() {
            override fun areItemsTheSame(oldItem: Booklet, newItem: Booklet): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Booklet, newItem: Booklet): Boolean {
                return oldItem == newItem
            }
        }
    }
}