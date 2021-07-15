package com.steve.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.steve.retrofit.data.api.ApiHelper
import com.steve.retrofit.data.api.RetrofitBuilder
import com.steve.retrofit.databinding.ActivityMainBinding
import com.steve.retrofit.model.User
import com.steve.retrofit.ui.adapter.MainAdapter
import com.steve.retrofit.ui.viewmodel.MainViewModel
import com.steve.retrofit.ui.viewmodel.ViewModelFactory
import com.steve.retrofit.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initRecyclerView()
        setupViewModel()
        setupObservers()

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun initRecyclerView() {
        binding?.recyclerview?.apply {
            adapter = MainAdapter(arrayListOf())
        }
    }

        private fun setupObservers() {
            viewModel.getUsers().observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            binding?.recyclerview?.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            resource.data?.let { users -> retrieveList(users) }
                        }
                        Status.ERROR -> {
                            binding?.recyclerview?.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                            binding?.recyclerview?.visibility = View.GONE
                        }
                    }
                }
            })
        }

        private fun retrieveList(users: List<User>) {
            adapter.apply {
                addUsers(users)
                notifyDataSetChanged()
            }
        }
    }
