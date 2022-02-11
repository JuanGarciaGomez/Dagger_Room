package red.courses.mvvm_room_dagger.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import red.courses.mvvm_room_dagger.databinding.ActivityMain2Binding
import red.courses.mvvm_room_dagger.ui.viewmodel.QuoteRegisterViewModel

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    private val quoteRegisterViewModel: QuoteRegisterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            quoteRegisterViewModel.addQuote(binding.editTextAuthor.text.toString(),
                binding.editTextQuote.text.toString())
            binding.editTextAuthor.setText("")
            binding.editTextQuote.setText("")
        }


    }

}