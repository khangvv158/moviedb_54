package com.sun.moviedb_54.screen.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sun.moviedb_54.R
import com.sun.moviedb_54.databinding.FragmentFavouriteBinding
import com.sun.moviedb_54.extensions.addFragment
import com.sun.moviedb_54.screen.detailmovie.DetailMovieFragment
import kotlinx.coroutines.runBlocking
import org.koin.android.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding
    private val favoriteMovieViewModel by viewModel<FavouriteViewModel>()
    private val favoriteAdapter by lazy {
        FavouriteAdapter {
            addFragment(DetailMovieFragment.newInstance(it), R.id.mainFrame)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false)
        binding.apply {
            viewModel = this@FavouriteFragment.favoriteMovieViewModel
            lifecycleOwner = this@FavouriteFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteMovieViewModel.getAllFavoriteMovie()
        binding.favoriteAdapter = favoriteAdapter
        requireActivity().supportFragmentManager.addOnBackStackChangedListener {
            if (isCheckFavorite) {
                runBlocking {
                    favoriteMovieViewModel.favoriteMovie.value?.clear()
                    favoriteMovieViewModel.getAllFavoriteMovie()
                    isCheckFavorite = false
                }
            }
        }
    }

    companion object {
        var isCheckFavorite = false

        fun newInstance() = FavouriteFragment()
    }
}
