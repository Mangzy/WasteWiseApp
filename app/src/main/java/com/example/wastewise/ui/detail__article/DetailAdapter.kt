package com.example.wastewise.ui.detail__article

import android.view.LayoutInflater
import com.example.wastewise.databinding.ImageArticleBinding
import com.example.wastewise.databinding.ParagraphArticleBinding
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wastewise.data.remote.response.detail_article.ContentItem

class DetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<ContentItem>()

    enum class ItemType {
        IMAGE,
        TEXT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ItemType.values()[viewType]) {
            ItemType.IMAGE -> ImageViewHolder(
                ImageArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            ItemType.TEXT -> TextViewHolder(
                ParagraphArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImageViewHolder -> holder.bindImage(items[position] as ContentItem.ImageContent)
            is TextViewHolder -> holder.bindText(items[position] as ContentItem.TextContent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        val currentItem = items[position]

        return when (currentItem) {
            is ContentItem.ImageContent -> ItemType.IMAGE.ordinal
            is ContentItem.TextContent -> ItemType.TEXT.ordinal
        }
    }

    fun submitList(newItems: List<ContentItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(private val binding: ImageArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindImage(item: ContentItem.ImageContent) {
            Glide.with(itemView.context)
                .load(item.image)
                .into(binding.imageArticle)
            binding.captionArticle.text = item.caption
        }
    }

    inner class TextViewHolder(private val binding: ParagraphArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindText(item: ContentItem.TextContent) {
            binding.paragraphArticle1.text = item.text
        }
    }
}