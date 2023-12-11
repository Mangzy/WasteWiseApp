package com.example.wastewise.ui.home

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wastewise.data.remote.response.article.Article
import com.example.wastewise.databinding.CardArticleBinding
import com.example.wastewise.ui.detail__article.DetailArticleActivity

class ArticleAdapter : PagingDataAdapter<Article, ArticleAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private var onItemClickCallback: OnItemClickCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CardArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        if (review != null) {
            holder.bind(review)
            Log.d("Name", "onBindViewHolder: ${holder.tvItemName.text}")
        } else {
            Log.d("ArticleAdapter", "review is null")
        }

        holder.cvItemArticle.setOnClickListener {
            review?.let { onItemClickCallback?.onItemClicked(it) }


            val intent = Intent(holder.itemView.context, DetailArticleActivity::class.java)
            intent.putExtra(DetailArticleActivity.EXTRA_ID, review?.contentId)
            intent.putExtra(DetailArticleActivity.EXTRA_TITLE, review?.title)

            holder.itemView.context.startActivity(intent)
        }

    }

    inner class MyViewHolder(private val binding: CardArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        val cvItemArticle: CardView = binding.cvItemArticle
        val tvItemName: TextView = binding.tvTitleArtice
        val tvItemAuthor: TextView = binding.tvArticleAuthor

        fun bind(article: Article) {
            with(binding) {
                tvItemName.text = article.title
                tvItemAuthor.text = article.author
                Glide.with(itemView.context)
                    .load(article.image)
                    .into(ivItemPhoto)
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Article)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.contentId == newItem.contentId
            }
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }
}