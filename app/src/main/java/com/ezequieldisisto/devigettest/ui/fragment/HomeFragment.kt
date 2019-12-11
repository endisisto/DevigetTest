package com.ezequieldisisto.devigettest.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ezequieldisisto.devigettest.R
import com.ezequieldisisto.devigettest.ui.adapter.PostAdapter
import com.ezequieldisisto.devigettest.util.DividerItemDecorator
import com.ezequieldisisto.devigettest.util.EndlessScrollListener
import com.ezequieldisisto.devigettest.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var postViewModel: PostViewModel
    private var adapter = PostAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        postViewModel = ViewModelProviders.of(requireActivity()).get(PostViewModel::class.java)

        postViewModel.postList.observe(this, Observer {

            swipeLayout.isRefreshing = false
            progress.visibility = View.GONE
            postRecycler.visibility = View.VISIBLE
            adapter.addPosts(it)

        })

        postViewModel.status.observe(this, Observer {
            when (it) {
                PostViewModel.Status.FULL_LIST -> {
                    postRecycler.clearOnScrollListeners()
                }
                PostViewModel.Status.EMPTY_LIST -> {
                    // for the dismiss all
                }
                PostViewModel.Status.ERROR -> {
                    Toast.makeText(
                            requireContext(),
                            getString(R.string.error_request),
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        postViewModel.getPostList()

        setUpViews()
    }

    private fun setUpViews() {
        swipeLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeLayout.setOnRefreshListener {
            swipeLayout.isRefreshing = true
            postRecycler.addOnScrollListener(getScrollListener())
            postViewModel.getPostList()
        }

        postRecycler.adapter = adapter
        postRecycler.layoutManager = LinearLayoutManager(requireContext())
        postRecycler.addItemDecoration(DividerItemDecorator(requireContext()))
        postRecycler.addOnScrollListener(getScrollListener())
    }

    private fun getScrollListener(): EndlessScrollListener {
        return object : EndlessScrollListener(postRecycler.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                postViewModel.getNextPostList()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}