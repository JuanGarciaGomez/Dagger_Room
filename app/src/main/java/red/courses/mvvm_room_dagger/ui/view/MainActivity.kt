package red.courses.mvvm_room_dagger.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import red.courses.mvvm_room_dagger.R
import red.courses.mvvm_room_dagger.databinding.ActivityMainBinding
import red.courses.mvvm_room_dagger.ui.viewmodel.QuoteViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.onCreate()

        quoteViewModel.quoteModel.observe(this, Observer { currentQuote ->
            binding.txtQuote.text = currentQuote.quote
            binding.txtAuthor.text = currentQuote.author
        })

        quoteViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })


        binding.viewContainer.setOnClickListener {
            quoteViewModel.randomQuote()
        }


        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.add -> {
                    // Respond to navigation item 1 click
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                    true
                }
                R.id.clock -> {
                    // Respond to navigation item 2 click
                    Toast.makeText(this,"ITEM CLOCK", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }


    }

}