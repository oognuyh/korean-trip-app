package com.example.trip.ui.around

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.trip.R
import com.example.trip.databinding.FragmentAroundBinding
import com.example.trip.network.TourApi
import com.example.trip.repository.TourRepository
import com.example.trip.ui.around.adapter.ContentAdapter
import com.example.trip.ui.base.BaseFragment
import com.example.trip.util.*
import com.example.trip.viewmodel.AroundViewModel
import com.example.trip.viewmodel.factory.AndroidViewModelFactory
import com.skt.Tmap.TMapView

class AroundFragment : BaseFragment<FragmentAroundBinding>() {
    lateinit var viewModel: AroundViewModel
    private var isGPSEnabled = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()

        val tMapView = view.findViewById<TMapView>(R.id.tMapView)
        tMapView.setSKTMapApiKey(getString(R.string.TMAP_API_KEY))

        binding.contents.adapter = ContentAdapter()

        GpsUtils(requireContext()).turnGPSOn(object : GpsUtils.OnGpsListener {
            override fun gpsStatus(isGPSEnable: Boolean) {
                this@AroundFragment.isGPSEnabled = isGPSEnable
            }
        })
    }

    private fun setupViewModel() {
        val repository = TourRepository(TourApi.invoke())
        val factory = AndroidViewModelFactory(repository, requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(AroundViewModel::class.java)

        binding.viewModel = viewModel
    }

    override fun onStart() {
        super.onStart()
        invokeLocationAction()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GPS_REQUEST) {
                isGPSEnabled = true
                invokeLocationAction()
            }
        }
    }

    private fun invokeLocationAction() {
        when {
            !isGPSEnabled -> requireView().snackbar("enable gps")

            isPermissionsGranted() -> startLocationUpdate()

            shouldShowRequestPermissionRationale() -> requireView().snackbar("request permission")

            else -> ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                    LOCATION_REQUEST
            )
        }
    }

    private fun startLocationUpdate() {
        viewModel.getLocation().observeOnce(viewLifecycleOwner) { location ->
            viewModel.getNearbyContents(location.longitude.toString(), location.latitude.toString())
        }
    }

    private fun isPermissionsGranted() =
            ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(
                            requireActivity(),
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED

    private fun shouldShowRequestPermissionRationale() =
            ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
            ) && ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
            )

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_REQUEST -> {
                invokeLocationAction()
            }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_around
}