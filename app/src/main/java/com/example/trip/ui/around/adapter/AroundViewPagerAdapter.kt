package com.example.trip.ui.around.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.trip.ui.around.AroundContentFragment
import com.example.trip.utils.*

class AroundViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val contentTypeIds = listOf(TOURIST_ATTRACTION, CULTURAL_FACILITIES, LEPORTS, SHOPPING, DINING)
    private val titles = listOf("관광지", "문화시설", "레포츠", "쇼핑", "음식점")

    override fun getCount() = contentTypeIds.size

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AroundContentFragment(contentTypeIds[0])
            1 -> AroundContentFragment(contentTypeIds[1])
            2 -> AroundContentFragment(contentTypeIds[2])
            3 -> AroundContentFragment(contentTypeIds[3])
            4 -> AroundContentFragment(contentTypeIds[4])
            else -> null
        }!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}