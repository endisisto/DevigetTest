package com.ezequieldisisto.devigettest.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezequieldisisto.devigettest.R
import com.ezequieldisisto.devigettest.ui.adapter.PostAdapter
import com.ezequieldisisto.devigettest.util.DividerItemDecorator
import com.ezequieldisisto.devigettest.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var postViewModel: PostViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        view.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_detailFragment) }

        setUpViews()

        postViewModel = ViewModelProviders.of(requireActivity()).get(PostViewModel::class.java)

        postViewModel.postList.observe(this, Observer {

            swipeLayout.isRefreshing = false
            progress.visibility = View.GONE
            postRecycler.visibility = View.VISIBLE
            postRecycler.adapter = PostAdapter(it)
        })

        postViewModel.getPostList()

    }

    private fun setUpViews() {
        swipeLayout.setColorSchemeResources(R.color.colorAccent)
        swipeLayout.setOnRefreshListener {
            swipeLayout.isRefreshing = true
            postViewModel.getPostList()
        }

        postRecycler.layoutManager = LinearLayoutManager(requireContext())
        postRecycler.addItemDecoration(DividerItemDecorator(requireContext()))

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}