package tech.ru1t3rl.madlevel6task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.ru1t3rl.madlevel6task2.R
import tech.ru1t3rl.madlevel6task2.adapter.MovieAdapter
import tech.ru1t3rl.madlevel6task2.databinding.FragmentMoviesBinding
import tech.ru1t3rl.madlevel6task2.model.Movie
import tech.ru1t3rl.madlevel6task2.viewmodel.MovieViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root

    }

    private val viewModel: MovieViewModel by activityViewModels()
    private val movies = arrayListOf<Movie>()
    private val movieSelectAdapter = MovieAdapter(movies) { movie -> onMovieClick(movie) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeMovies()
    }

    private fun initView() {
        val gridLayoutManager = GridLayoutManager(requireContext(),
            1,
            RecyclerView.VERTICAL,
            false)
        binding.rvMovies.layoutManager = gridLayoutManager
        binding.rvMovies.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.rvMovies.viewTreeObserver.removeOnGlobalLayoutListener(this)
                gridLayoutManager.spanCount =3
                gridLayoutManager.requestLayout()
            }
        })

        binding.rvMovies.adapter = movieSelectAdapter

        binding.btnSubmit.setOnClickListener { onSubmitClick() }
    }

    private fun observeMovies() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            movies.clear()
            movies.addAll(it)
            movieSelectAdapter.notifyDataSetChanged()
        })

        viewModel.errorText.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun onSubmitClick() {
        viewModel.getMoviesByYear(binding.etYear.text.toString())
    }

    private fun onMovieClick(movieIndex: Int) {
        viewModel.setCurrentSelectedMovie(movieIndex)
        findNavController().navigate(R.id.novieFragment)
    }
}