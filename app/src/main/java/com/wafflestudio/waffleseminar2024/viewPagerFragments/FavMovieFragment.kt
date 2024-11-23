package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.HomeActivity
import com.wafflestudio.waffleseminar2024.MovieItem
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.adapter.searchResultRecyclerViewAdapter
import com.wafflestudio.waffleseminar2024.data.database.MyEntity
import com.wafflestudio.waffleseminar2024.databinding.FragmentAppBinding
import com.wafflestudio.waffleseminar2024.databinding.FragmentFavmovieBinding
import com.wafflestudio.waffleseminar2024.viewmodel.FavmovieViewModel
import com.wafflestudio.waffleseminar2024.viewmodel.FavmovieViewModelFactory
import kotlinx.coroutines.launch

class FavMovieFragment : Fragment() {
    private lateinit var navController: NavController
    private var _binding: FragmentFavmovieBinding? = null
    private val binding get() = _binding!!

    lateinit var searchResultRecyclerView: RecyclerView

    private val favMovieViewModel: FavmovieViewModel by viewModels { FavmovieViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavmovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSearchResultRecyclerView()

        navController = findNavController()

        val profileButton: ImageView = binding.profileButton
        profileButton.setOnClickListener {
            (activity as HomeActivity).viewPager.currentItem = 3
        }

        Log.d("AppFragment", "Fragment lifecycle state: ${viewLifecycleOwner.lifecycle.currentState}")

        // LiveData 관찰
        favMovieViewModel.favoriteMovies.observe(viewLifecycleOwner) { _favoriteMovies ->
            if (_favoriteMovies.isNullOrEmpty()) {
                Log.d("AppFragment", "No favorite movies to display")
            } else {
                Log.d("AppFragment", "Movies found: ${_favoriteMovies.size}")
            }
            showResult(_favoriteMovies ?: emptyList())
        }
    }

    override fun onResume() {
        super.onResume()
        // Fragment가 다시 활성화될 때 데이터를 로드
        Log.d("FavMovieFragment", "onResume called, reloading favorite movies")
        viewLifecycleOwner.lifecycleScope.launch {
            favMovieViewModel.loadFavoriteMovies()
        }
    }

    private fun setSearchResultRecyclerView() {
        searchResultRecyclerView = binding.searchResultRecyclerView
        searchResultRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    private fun showResult(data: List<MyEntity>) {
        searchResultRecyclerView.adapter = searchResultRecyclerViewAdapter(data) { movie ->
            val action = FavMovieFragmentDirections.actionToMovieDetailFragment(movie.id!!)
            navController.navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
