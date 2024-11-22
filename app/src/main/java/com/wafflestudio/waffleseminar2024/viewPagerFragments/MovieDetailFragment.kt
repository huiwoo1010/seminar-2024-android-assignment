package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.wafflestudio.waffleseminar2024.Genre
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.adapter.GenreChipAdapter
import com.wafflestudio.waffleseminar2024.databinding.FragmentMovieDetailBinding
import com.wafflestudio.waffleseminar2024.viewmodel.FavmovieViewModel
import com.wafflestudio.waffleseminar2024.viewmodel.MovieViewModel
import com.wafflestudio.waffleseminar2024.viewmodel.MovieViewModelFactory
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale
import androidx.core.content.ContextCompat
import com.wafflestudio.waffleseminar2024.viewmodel.FavmovieViewModelFactory

class MovieDetailFragment : Fragment() {
    private lateinit var navController: NavController

    private val viewModel: MovieViewModel by viewModels { MovieViewModelFactory(requireContext()) }
    private val favMovieViewModel: FavmovieViewModel by viewModels { FavmovieViewModelFactory(requireContext()) }
    private val movieId: Int by lazy {
        arguments?.getInt("movieId") ?: 0
    }

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var genreChipAdapter: GenreChipAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        viewModel.fetchMovieDetails(movieId)
        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            movie?.let {
                it.genres?.let { it1 -> setupRecyclerView(it1) }
                binding.movieTitle.text = it.title
                binding.backdropImg.load("https://image.tmdb.org/t/p/original" + it.backdrop_path)
                binding.posterImg.load("https://image.tmdb.org/t/p/original" + it.poster_path)
                binding.ratingBar.rating = (it.vote_average ?: 10.0).toFloat()/2
                binding.rateText.text = String.format("%.1f", it.vote_average ?: 0.0)
                binding.runtimeText.text= "${it.runtime?.div(60)}h ${it.runtime?.rem(60)}m"
                binding.releaseyearText.text= it.release_date?.substring(0, 4) ?: ""
                binding.overviewText.text = it.overview
                binding.originaltitleText.text = it.original_title
                binding.statusText.text = it.status
                binding.budgetText.text = DecimalFormat("$#,###").format(it.budget)
                binding.revenueText.text = DecimalFormat("$#,###").format(it.revenue)
            }
        }

        binding.favoriteButton.setImageResource(R.drawable.star_filled)
        binding.favoriteButton.setOnClickListener {
            val movie = viewModel.movie.value
            movie?.let {
                it.isFavorite = !it.isFavorite
                viewModel.updateMovie(it)

                lifecycleScope.launch {
                    favMovieViewModel.toggleFavoriteStatus(it)
                }

                updateFavoriteButtonIcon(it.isFavorite) // UI 갱신
                Log.d("MyApp", "Favorite status: ${it.isFavorite}")
            } ?: Log.d("MyApp", "Movie is null")
        }
    }

    private fun setupRecyclerView(data: List<Genre>) {
        genreChipAdapter = GenreChipAdapter(data)
        binding.genreRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.genreRecyclerView.adapter = genreChipAdapter
    }

    private fun updateFavoriteButtonIcon(isFavorite: Boolean) {
        val color = if (isFavorite) { "#1BD0C8" } else { "#D8D8D8" }
        val drawable = binding.favoriteButton.drawable?.mutate()
        drawable?.setTint(Color.parseColor(color))
        binding.favoriteButton.setImageDrawable(drawable)
    }
}
