package com.example.trip.ui.around

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trip.R
import com.example.trip.model.LocationBasedContent
import com.example.trip.network.TourAPIService
import com.example.trip.repository.TourAPIRepository
import com.example.trip.ui.around.adapter.AroundViewPagerAdapter
import com.example.trip.utils.*
import com.example.trip.viewmodel.AroundViewModel
import com.example.trip.viewmodel.LocationViewModel
import com.example.trip.viewmodel.factory.AroundViewModelFactory
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPoint
import kotlinx.android.synthetic.main.fragment_around.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AroundFragment : Fragment(R.layout.fragment_around) {
    private lateinit var viewModel: LocationViewModel
    lateinit var aroundViewModel: AroundViewModel
    private var isGPSEnabled = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(LocationViewModel::class.java)

        setupAroundViewModel()
        setupViewPager()

        GpsUtils(requireContext()).turnGPSOn(object : GpsUtils.OnGpsListener {
            override fun gpsStatus(isGPSEnable: Boolean) {
                isGPSEnabled = isGPSEnable
            }
        })

        setupMapView()
        aroundViewModel.attractionItems.observeOnce(viewLifecycleOwner) {
            createMarkers(it)
        }
    }

    private fun setupAroundViewModel() {
        val repository = TourAPIRepository(TourAPIService)
        val factory = AroundViewModelFactory(repository)
        aroundViewModel = ViewModelProvider(this, factory)[AroundViewModel::class.java]
    }

    private fun setupViewPager() {
        val aroundViewPagerAdapter = AroundViewPagerAdapter(childFragmentManager)
        vp_around.adapter = aroundViewPagerAdapter
        tl_around.setupWithViewPager(vp_around)
    }

    private fun setupMapView() {
        CoroutineScope(Dispatchers.Main).launch {
            map_around.setSKTMapApiKey(getString(R.string.TMAP_API_KEY))
            map_around.setTrackingMode(true)
            map_around.setSightVisible(true)
            map_around.zoomLevel = 13
        }
    }

    fun createMarkers(items: List<LocationBasedContent>) {
        items.map {
            val markerItem = TMapMarkerItem()
            markerItem.setPosition(0.5f, 1.0f)
            markerItem.tMapPoint = TMapPoint(it.mapy!!, it.mapx!!)
            markerItem.name = it.title

            map_around.addMarkerItem(it.contentid.toString(), markerItem)
        }
    }

    fun removeMapMarkers() {
        map_around.removeAllMarkerItem()
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

            shouldShowRequestPermissionRationale() -> requireView().snackbar("permission request")

            else -> ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_REQUEST
            )
        }
    }

    private fun startLocationUpdate() {
        viewModel.getLocation().observeOnce(viewLifecycleOwner) {
            map_around.setLocationPoint(it.longitude, it.latitude)
            map_around.setCenterPoint(it.longitude, it.latitude)
            aroundViewModel.fetchLocationBasedItems(it.longitude, it.latitude)
        }
    }

    private fun isPermissionsGranted() =
        ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    requireContext(),
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
}