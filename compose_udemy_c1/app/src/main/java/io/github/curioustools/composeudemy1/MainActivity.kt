package io.github.curioustools.composeudemy1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.curioustools.composeudemy1.databinding.ActivityMainBinding
import io.github.curioustools.composeudemy1.proj1_resume.ResumeActivity
import io.github.curioustools.composeudemy1.proj2_tipcalculator.TipCalculatorActivity
import io.github.curioustools.composeudemy1.proj3_compose_in_frag.ComposeDashboardActivity

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
        }
    }
}