package com.example.trip.ui.home

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.trip.R
import com.example.trip.model.AreaBasedContent
import com.example.trip.utils.*
import kotlinx.android.synthetic.main.page_home_course.*

class HomeCourseFragment(private val cat2: String) : Fragment(R.layout.page_home_course){
    private lateinit var homeFragment: HomeFragment
    private lateinit var courses: LiveData<List<AreaBasedContent>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeFragment = parentFragment as HomeFragment

        courses = selectItems()
        courses.observe(viewLifecycleOwner) {
            setupCourseView(it)
        }

    }

    private fun selectItems(): LiveData<List<AreaBasedContent>> {
        return when (cat2) {
            FAMILY_COURSE -> homeFragment.viewModel.familyCourseItems
            ALONE_COURSE -> homeFragment.viewModel.aloneCourseItems
            HEALING_COURSE -> homeFragment.viewModel.healingCourseItems
            WALK_COURSE -> homeFragment.viewModel.walkCourseItems
            CAMPING_COURSE -> homeFragment.viewModel.campingCourseItems
            TASTE_COURSE -> homeFragment.viewModel.tasteCourseItems
            else -> null
        }!!
    }

    private fun setupCourseView(courses: List<AreaBasedContent>) {
        cv_home_user_based_content.apply {
            size = courses.size
            hideIndicator(true)
            setCarouselViewListener { view, position ->
                val title = courses[position].title
                val imageUrl = courses[position].firstimage
                if (imageUrl != null)
                    context.loadImage(imageUrl, view.findViewById(R.id.iv_home_user_based_content_image))
                view.findViewById<TextView>(R.id.tv_home_user_based_content_title).text = title
            }
            show()
        }
    }
}