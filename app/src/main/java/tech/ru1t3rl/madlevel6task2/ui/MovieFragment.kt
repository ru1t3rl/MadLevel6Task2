package tech.ru1t3rl.madlevel6task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import tech.ru1t3rl.madlevel6task2.databinding.FragmentMovieBinding
import tech.ru1t3rl.madlevel6task2.viewmodel.MovieViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieFragment : Fragment() {
    private val viewModel: MovieViewModel by activityViewModels()

    private val binding get() = _binding!!
    private var _binding: FragmentMovieBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val index = viewModel.currentSelectedMovie
        val movie = viewModel.movies.value?.get(index)

        if (movie != null) {
            binding.tvSummary.text = movie.overview
            binding.tvRating.text = movie.rating.toString()
            binding.tvReleaseDate.text = movie.releaseDate
            binding.tvTitle.text = movie.title
            Glide.with(requireContext()).load(movie.getPosterUrl()).into(binding.ivPoster)
            Glide.with(requireContext()).load(movie.getBackdropUrl()).into(binding.ivBackdrop)
        }

    }
}