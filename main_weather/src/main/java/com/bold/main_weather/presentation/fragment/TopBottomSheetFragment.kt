package com.bold.main_weather.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.bold.main_weather.R
import com.bold.main_weather.databinding.BottomSheetLayoutBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bold.main_weather.data.repo.models.CitySearchModel
import com.bold.main_weather.presentation.CityClickListener
import com.bold.main_weather.presentation.viewmodel.HomeViewModel
import com.bold.main_weather.presentation.viewmodel.WeatherViewModel
import com.bold.network.ApiResponse
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("ResourceType")
class TopBottomSheetFragment : DialogFragment(R.style.TransparentDialog), CityClickListener {

    private var _binding: BottomSheetLayoutBinding? = null
    private val binding get() = _binding!!

    private val weatherViewModel: WeatherViewModel by viewModel()

    private val homeViewModel: HomeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_layout, container, false)
        binding.viewModel = weatherViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            weatherViewModel.weatherState.collect { state ->

                when (state) {
                    is ApiResponse.Success -> {
                        val cities = state.data
                        val itemAdapter = ItemAdapter(cities, this@TopBottomSheetFragment)
                        binding.rvSearchs.adapter = itemAdapter
                    }

                    is ApiResponse.Error -> {

                    }

                    is ApiResponse.Loading -> {

                    }
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            (resources.displayMetrics.heightPixels * 0.5).toInt()
        )
        dialog?.window?.setBackgroundDrawableResource(R.drawable.rounded_bottom_sheet)
        dialog?.window?.setGravity(Gravity.TOP)
        val slideIn = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in)
        binding.root.startAnimation(slideIn)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun dismiss() {
        val slideOut = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out)
        binding.root.startAnimation(slideOut)
        slideOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) = Unit
            override fun onAnimationEnd(animation: Animation?) {
                dismissAllowingStateLoss()
            }
            override fun onAnimationRepeat(animation: Animation?) = Unit
        })
    }

    override fun onCityClick(city: CitySearchModel) {
        homeViewModel.updateSelectedCity(city)
        dismiss()
    }
}
