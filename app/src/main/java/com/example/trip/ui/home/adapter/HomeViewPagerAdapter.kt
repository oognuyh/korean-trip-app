package com.example.trip.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.trip.ui.home.HomeCourseFragment
import com.example.trip.utils.*

class HomeViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val courses = listOf(FAMILY_COURSE, ALONE_COURSE, HEALING_COURSE, WALK_COURSE, CAMPING_COURSE, TASTE_COURSE)
    private val titles = listOf("#가족코스", "#나홀로코스", "#힐링코스", "#도보코스", "#캠핑코스", "#맛코스")

    override fun getCount() = courses.size

    override fun getItem(position: Int): Fragment {
         val fragment = when (position) {
             0 -> HomeCourseFragment(courses[0])
             1 -> HomeCourseFragment(courses[1])
             2 -> HomeCourseFragment(courses[2])
             3 -> HomeCourseFragment(courses[3])
             4 -> HomeCourseFragment(courses[4])
             5 -> HomeCourseFragment(courses[5])
             else -> null
         }
        return fragment!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}