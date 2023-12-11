package com.example.factorialtestapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.factorialtestapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private val binding by lazy {
		ActivityMainBinding.inflate(layoutInflater)
	}

	private val viewModel by lazy {
		ViewModelProvider(this)[MainViewModel::class.java]
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		observeViewModel()
		binding.btnCalculate.setOnClickListener {
			viewModel.calculateFactorial(binding.etNumber.text.toString().trim())
		}
	}

	private fun observeViewModel() {
		viewModel.state.observe(this) {
			binding.pbLoading.visibility = View.GONE
			binding.btnCalculate.isEnabled = true
			when (it) {
				is Error -> {
					Toast.makeText(
						this,
						"You didn't enter value!",
						Toast.LENGTH_SHORT
					).show()
				}

				is Progress -> {
					binding.pbLoading.visibility = View.VISIBLE
					binding.btnCalculate.isEnabled = false
				}

				is Factorial -> {
					binding.tvResultOfOperation.text = it.factorial
				}
			}
		}
	}
}