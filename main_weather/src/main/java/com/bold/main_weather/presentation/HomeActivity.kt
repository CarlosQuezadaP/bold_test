package com.bold.main_weather.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.bold.main_weather.R
import com.bold.main_weather.databinding.ActivityHomeBinding
import com.bold.main_weather.presentation.fragment.ForecastAdapter
import com.bold.main_weather.presentation.fragment.ItemAdapter
import com.bold.main_weather.presentation.fragment.TopBottomSheetFragment
import com.bold.main_weather.presentation.viewmodel.HomeViewModel
import com.bold.network.ApiResponse
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var homeBinding: ActivityHomeBinding

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        homeBinding =
            DataBindingUtil.setContentView<ActivityHomeBinding?>(this, R.layout.activity_home)
                .apply {
                    homeViewModel = viewModel
                    textViewName.setOnClickListener {
                        val bottomSheet = TopBottomSheetFragment()
                        bottomSheet.show(supportFragmentManager, bottomSheet.tag)
                    }
                }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        lifecycleScope.launch {
            viewModel.daysWeather.collect { state ->

                when (state) {
                    is ApiResponse.Success -> {
                        val forecast = state.data.forecast.forecastday
                        val itemAdapter = ForecastAdapter()
                        itemAdapter.submitList(forecast)
                        homeBinding.rvDays.adapter = itemAdapter
                    }

                    is ApiResponse.Error -> {

                    }

                    is ApiResponse.Loading -> {

                    }
                }
            }
        }


    }
}