package com.ezequieldisisto.devigettest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ezequieldisisto.devigettest.R
import com.ezequieldisisto.devigettest.model.Post
import com.ezequieldisisto.devigettest.ui.activity.MainActivity
import com.ezequieldisisto.devigettest.util.Constants
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).showBack()

        val post = arguments?.getParcelable<Post>(Constants.KEY_POST)

        post?.let {
            author.text = it.author
            title.text = it.title

            if (it.thumbnail.isNullOrEmpty() || !URLUtil.isValidUrl(it.thumbnail)) {
                image.visibility = View.GONE
            } else {
                Glide.with(requireContext()).load(it.thumbnail).into(image)
            }
        }
    }
}