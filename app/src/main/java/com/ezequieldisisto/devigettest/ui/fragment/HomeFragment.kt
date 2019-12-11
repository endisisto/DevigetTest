package com.ezequieldisisto.devigettest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.ezequieldisisto.devigettest.R
import com.ezequieldisisto.devigettest.viewmodel.PostViewModel

class HomeFragment : Fragment() {

    private lateinit var postViewModel: PostViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_detailFragment) }

        postViewModel = ViewModelProviders.of(requireActivity()).get(PostViewModel::class.java)

        postViewModel.postList.observe(this, Observer {

        })

        postViewModel.getPostList()

    }
}