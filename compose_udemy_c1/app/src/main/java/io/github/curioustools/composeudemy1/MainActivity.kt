package io.github.curioustools.composeudemy1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.curioustools.composeudemy1.databinding.ActivityMainBinding
import io.github.curioustools.composeudemy1.resume.ResumeActivity
import io.github.curioustools.composeudemy1.tipcalculator.TipCalculatorActivity
import io.github.curioustools.composeudemy1.compose_in_frag.ComposeDashboardActivity
import io.github.curioustools.composeudemy1.full_notes.NotesActivity
import io.github.curioustools.composeudemy1.movie.MovieActivity

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            resume.setOnClickListener {
                startActivity(Intent(this@MainActivity,ResumeActivity::class.java))
            }
            tipCalculator.setOnClickListener {
                startActivity(Intent(this@MainActivity,TipCalculatorActivity::class.java))
            }

            composeFragmentDashboard.setOnClickListener {
                startActivity(Intent(this@MainActivity, ComposeDashboardActivity::class.java))

            }
            movie.setOnClickListener {
                startActivity(Intent(this@MainActivity, MovieActivity::class.java))

            }
            notesdb.setOnClickListener {
                startActivity(Intent(this@MainActivity, NotesActivity::class.java))
            }
        }
    }
}