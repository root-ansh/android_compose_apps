package io.github.curioustools.composeudemy1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.github.curioustools.composeudemy1.databinding.ActivityMainBinding
import io.github.curioustools.composeudemy1.proj1_resume.ResumeActivity

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            resume.setOnClickListener {
                startActivity(Intent(this@MainActivity,ResumeActivity::class.java))
            }
        }
    }
}