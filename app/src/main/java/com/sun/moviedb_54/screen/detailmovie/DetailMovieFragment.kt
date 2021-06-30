package com.sun.moviedb_54.screen.detailmovie

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sun.moviedb_54.R
import com.sun.moviedb_54.databinding.FragmentDetailMovieBinding
import com.sun.moviedb_54.extensions.addFragment
import com.sun.moviedb_54.screen.actordetail.ActorDetailFragment
import com.sun.moviedb_54.screen.favourite.FavouriteFragment
import com.sun.moviedb_54.ultis.Constant.URI_YOUTUBE_APP
import com.sun.moviedb_54.ultis.Constant.URI_YOUTUBE_WEBSITE
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailMovieBinding
    private var idMovie: Int? = null
    private val detailMovieViewModel by viewModel<DetailMovieViewModel>()
    private val recommendationAdapter by lazy {
        RecommendationAdapter {
            addFragment(newInstance(it), R.id.mainFrame)
        }
    }
    private val actorMovieAdapter by lazy {
        ActorMovieAdapter {
            addFragment(ActorDetailFragment.newInstance(it), R.id.mainFrame)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_movie, container, false)
        binding.apply {
            viewModel = this@DetailMovieFragment.detailMovieViewModel
            lifecycleOwner = this@DetailMovieFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initEvent()
        handleEvent()
    }

    private fun initData() {
        idMovie?.let { detailMovieViewModel.getDetailMovie(it) }
    }

    private fun initView() {
        binding.recommendAdapter = recommendationAdapter
        binding.actorMovieAdapter = actorMovieAdapter
        idMovie?.let { checkFavorite(it) }
    }

    private fun initEvent() {
        imageBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        imagePlay.setOnClickListener {
            detailMovieViewModel.keyYoutube?.let { key -> openYouTube(key) } ?: Toast.makeText(
                context,
                getString(R.string.no_video),
                Toast.LENGTH_SHORT
            ).show()
        }
        imageFavorite.setOnClickListener {
            FavouriteFragment.isCheckFavorite = !FavouriteFragment.isCheckFavorite
            if (detailMovieViewModel.isFavorite) {
                detailMovieViewModel.deleteFavorite()
                imageFavorite.setImageResource(R.drawable.ic_heart_default)
            } else {
                detailMovieViewModel.addFavorite()
                imageFavorite.setImageResource(R.drawable.ic_heart_red)
            }
        }
    }

    private fun openYouTube(keyYoutube: String) {
        try {
            context?.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(URI_YOUTUBE_APP + keyYoutube)
                )
            )
        } catch (e: ActivityNotFoundException) {
            context?.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(URI_YOUTUBE_WEBSITE + keyYoutube)
                )
            )
        }
    }

    private fun checkFavorite(id: Int) {
        if (detailMovieViewModel.checkFavorite(id)) {
            imageFavorite.setImageResource(R.drawable.ic_heart_red)
        }
    }

    private fun handleEvent() {
        imageBack.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    companion object {

        fun newInstance(id: Int?) = DetailMovieFragment().apply {
            idMovie = id
        }
    }
}
