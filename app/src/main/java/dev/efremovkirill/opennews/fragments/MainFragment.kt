package dev.efremovkirill.opennews.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.efremovkirill.opennews.R
import dev.efremovkirill.opennews.data.Article
import dev.efremovkirill.opennews.di.App
import dev.efremovkirill.opennews.recyclerview.NewsAdapter
import dev.efremovkirill.opennews.retrofit.Repository
import dev.efremovkirill.opennews.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.Exception

class MainFragment : Fragment(), NewsAdapter.OnNavListener {
    private val newsAdapter = NewsAdapter(this)
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsRecyclerView: RecyclerView = view.findViewById(R.id.news_rcView)

        newsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        newsRecyclerView.adapter = newsAdapter

        mainViewModel.newsLiveData.observe(viewLifecycleOwner) { news -> newsAdapter.put(news!!) }
    }

    override fun onNav(article: Article) {
        try {
            val bundle = Bundle()
            bundle.putSerializable("article", article)

            findNavController().navigate(R.id.action_mainFragment_to_articleFragment, bundle)
        }
        catch (e: Exception) {
            Log.d("Bug", "${e.message}")
        }
    }
}