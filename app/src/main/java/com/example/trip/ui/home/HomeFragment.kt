package com.example.trip.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.trip.R
import com.example.trip.model.AreaBasedContent
import com.example.trip.model.AreaBasedVO
import com.example.trip.model.SearchFestivalContent
import com.example.trip.network.TourAPIService
import com.example.trip.utils.loadImage
import com.jama.carouselview.enums.IndicatorAnimationType
import com.jama.carouselview.enums.OffsetType
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_home_banner.view.*
import kotlinx.android.synthetic.main.item_home_user_based_content.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/* todo
 * 1. display festival banner
 * 2. display contents with user based recommendation
 * 3. display the recommendation contents by TOUR API 3.0
 */

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val areaBasedItems = MutableLiveData<List<AreaBasedContent>>()
    private val searchFestivalItems = MutableLiveData<List<SearchFestivalContent>>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchCourseItems()
        observeFestivalItems()
        observeCourse()
    }

    private fun fetchSearchFestival() {
        CoroutineScope(Dispatchers.IO).launch {
            TourAPIService.getInstace().getSearchFestival(pageNo = "1", eventStartDate = getDate()).let {
                searchFestivalItems.postValue(it.body()?.response?.body?.items?.contents)
            }
        }
    }

    private fun fetchCourseItems() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = TourAPIService.getInstace().getAreaBasedList(pageNo = "1", arrange = "P", contentTypeId = "25")
            if (response.isSuccessful) {
                areaBasedItems.postValue(response.body()?.response?.body?.items?.contents)
            }
        }
    }

    private fun observeCourse() {
        areaBasedItems.observe(viewLifecycleOwner) { contents ->
            cv_home_user_based_content.apply {
                size = contents.size
                scaleOnScroll = true
                spacing = 50
                hideIndicator(true)
                setCarouselViewListener { view, position ->
                    val title = contents[position].title
                    val imageUrl = contents[position].firstimage
                    Log.d("tag", imageUrl)
                    if (imageUrl != null)
                        context.loadImage(imageUrl, view.findViewById(R.id.iv_home_user_based_content_image))
                    view.findViewById<TextView>(R.id.tv_home_user_based_content_title).text = title
                }
                show()
            }
        }
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
        fetchSearchFestival()
        searchFestivalItems.observe(viewLifecycleOwner) { contents ->
            setupFestivalBanner(contents)
        }
    }

    private fun getDate(): String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
}