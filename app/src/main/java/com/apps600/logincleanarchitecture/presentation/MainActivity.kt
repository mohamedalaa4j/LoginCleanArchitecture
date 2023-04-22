package com.apps600.logincleanarchitecture.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.apps600.logincleanarchitecture.databinding.ActivityMainBinding
import com.apps600.logincleanarchitecture.domain.entity.User
import com.apps600.logincleanarchitecture.utilites.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleToast()
        handleProgressbar()
        binding.tvLogin.setOnClickListener {
            val user = User("kminchelle", "0lelplR")
            viewModel.login(user)
        }
    }

    private fun handleToast() {
        lifecycleScope.launchWhenStarted {
            viewModel.showToast.collect {
                when (it) {
                    is Resource.Success -> {
                        Snackbar.make(binding.root, it.message!!, Snackbar.LENGTH_LONG).show()
                    }
                    is Resource.Error -> {
                        Snackbar.make(binding.root, it.message!!, Snackbar.LENGTH_LONG).show()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun handleProgressbar() {
        lifecycleScope.launchWhenStarted {
            viewModel.showProgressbar.collect {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressbar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressbar.visibility = View.GONE
                    }
                    else -> {}
                }
            }
        }
    }
}