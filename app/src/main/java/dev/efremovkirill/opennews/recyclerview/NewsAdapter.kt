package dev.efremovkirill.opennews.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.efremovkirill.opennews.R
import dev.efremovkirill.opennews.data.Article
import dev.efremovkirill.opennews.databinding.ArticleCardBinding
import android.text.Html

class NewsAdapter(var onNavListener: OnNavListener): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var news: List<Article> = emptyList()

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private val binding = ArticleCardBinding.bind(view)

        fun bind(article: Article) {
            binding.apply {
                articleTitleTextView.text = article.title
                articleDescTextView.text = Html.fromHtml(article.description).toString()
                articlePublishedTextView.text = article.publishedAt.replace("T", " ").replace("Z", " ")

                loadImage(article)
            }

            binding.articleReadMoreButton.setOnClickListener {
                onNavListener.onNav(article)
            }
        }

        private fun ArticleCardBinding.loadImage(article: Article) {
            Glide
                .with(view)
                .load(article.urlToImage)
                .into(articleImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.article_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun put(news: List<Article>) {
        this.news = news
        notifyDataSetChanged()
    }

    interface OnNavListener {
        fun onNav(article: Article)
    }
}