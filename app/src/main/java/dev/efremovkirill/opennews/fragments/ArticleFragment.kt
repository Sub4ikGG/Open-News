package dev.efremovkirill.opennews.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dev.efremovkirill.opennews.data.Article
import dev.efremovkirill.opennews.databinding.FragmentArticleBinding


class ArticleFragment : Fragment() {
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    //val args: NavArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArticleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val article = args.article
        val article: Article = arguments?.getSerializable("article") as Article
        binding.apply {
            val source = "(Click) Source: ${article.source.name}"
            oneSourceTextView.text = source
            oneAuthorTextView.text = article.author ?: "No author"
            oneArticleTitleTextView.text = article.title
            oneArticleDescTextView.text = Html.fromHtml(article.description).toString()
            oneArticleContentTextView.text = Html.fromHtml(article.content).toString()
            oneArticlePublishedTextView.text = article.publishedAt.replace("T", " ").replace("Z", " ")

            oneSourceTextView.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                startActivity(browserIntent)
            }

            loadImage(requireContext(), article)
        }
    }

    private fun FragmentArticleBinding.loadImage(
        context: Context,
        article: Article
    ) {
        Glide
            .with(context)
            .load(article.urlToImage)
            .into(oneArticleImageView)
    }
}