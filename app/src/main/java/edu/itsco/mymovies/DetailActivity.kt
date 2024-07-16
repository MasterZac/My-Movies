package edu.itsco.mymovies

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import edu.itsco.mymovies.databinding.ActivityDetailBinding
import edu.itsco.mymovies.model.Movie

class DetailActivity : AppCompatActivity() {

    companion object{
        val EXTRA_MOVIE = "DetailActivity:movie"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)

        if(movie != null)
        {
            title = movie.title
            Glide
                .with(this)
                .load("https://image.tmdb.org/t/p/w780/${movie.backdrop_path}")
                .into(binding.backdrop)
            binding.summary.text = movie.overview
            bindDetailInfo(binding.detailInfo, movie)
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Movie Details"
    }

    private fun bindDetailInfo(detailInfo: TextView, movie: Movie) {
        detailInfo.text = buildSpannedString {
            bold { append("Original language: ") }
            appendLine(movie.original_language)

            bold { append("Original title: ") }
            appendLine(movie.original_title)

            bold { append("Release date: ") }
            appendLine(movie.release_date)

            bold {append("Popularity: ")}
            appendLine(movie.popularity.toString())

            bold { append("Vote Average: ") }
            appendLine(movie.vote_average.toString())
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}