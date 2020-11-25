package com.example.trip.ui.home

import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.trip.R
import com.example.trip.model.AreaBasedContent
import com.example.trip.model.SearchFestivalContent
import com.example.trip.network.TourAPIService
import com.example.trip.repository.TourAPIRepository
import com.example.trip.ui.home.adapter.HomeViewPagerAdapter
import com.example.trip.utils.loadImage
import com.example.trip.viewmodel.HomeViewModel
import com.example.trip.viewmodel.factory.HomeViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.jama.carouselview.enums.IndicatorAnimationType
import com.jama.carouselview.enums.OffsetType
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.page_home_course.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/* todo
 * 1. display festival banner - Done
 * 2. display contents with user based recommendation
 * 3. display the recommendation contents by TOUR API 3.0 - Done
 */

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        viewModel.fetchCourseItems()
        setupCourseViewPager()

        observeFestivalItems()
    }

    private fun setupViewModel() {
        val repository = TourAPIRepository(TourAPIService)
        val factory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    private fun setupCourseViewPager() {
        val homeViewpagerAdapter = HomeViewPagerAdapter(childFragmentManager)
        vp_home_course.adapter = homeViewpagerAdapter
        tl_home_course.setupWithViewPager(vp_home_course)
    }

    private fun setupFestivalBanner(contents: List<SearchFestivalContent>) {
        cv_home_festival_banner.apply {
            size = contents.size
            setCarouselViewListener { view, position ->
                val title = contents[position].title
                val imageUrl = contents[position].firstimage
                if (imageUrl != null) {
                    context.loadImage(imageUrl, view.findViewById(R.id.iv_home_banner_image))
                    indicatorAnimationType = IndicatorAnimationType.THIN_WORM
                    carouselOffset = OffsetType.CENTER
                    view.findViewById<TextView>(R.id.tv_home_banner_title).text = title
                }
            }
            show()
        }
    }

    private fun observeFestivalItems() {
        viewModel.fetchFestivalItems()
        viewModel.festivalItems.observe(viewLifecycleOwner, { festivals ->
            CoroutineScope(Dispatchers.Main).launch {
                setupFestivalBanner(festivals)
            }
        })
    }
}