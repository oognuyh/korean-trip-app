package com.example.trip.ui.around

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trip.R
import com.example.trip.model.LocationBasedContent
import com.example.trip.ui.around.adapter.AroundContentRecyclerViewAdapter
import com.example.trip.utils.*
import kotlinx.android.synthetic.main.page_around_content.*

class AroundContentFragment(private val contentTypeId: String) : Fragment(R.layout.page_around_content){
    private lateinit var aroundFragment: AroundFragment
    private lateinit var contents: LiveData<List<LocationBasedContent>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aroundFragment = parentFragment as AroundFragment
        contents = selectItems()
        contents.observe(viewLifecycleOwner) {
            setupRecyclerView(it)
        }
    }

    override fun onResume() {
        super.onResume()

        aroundFragment.removeMapMarkers()
        contents.value?.let { aroundFragment.createMarkers(it) }
    }

    private fun selectItems(): LiveData<List<LocationBasedContent>> {
        return when(contentTypeId) {
            TOURIST_ATTRACTION -> aroundFragment.aroundViewModel.attractionItems
            CULTURAL_FACILITIES -> aroundFragment.aroundViewModel.cultureItems
            LEPORTS -> aroundFragment.aroundViewModel.leportsItems
            SHOPPING -> aroundFragment.aroundViewModel.shoppingItems
            DINING -> aroundFragment.aroundViewModel.diningItems
            else -> null
        }!!
    }

    private fun setupRecyclerView(contents: List<LocationBasedContent>) {
        val adapter = AroundContentRecyclerViewAdapter(requireContext(), contents)
        rv_page_around_content.setHasFixedSize(true)
        rv_page_around_content.adapter = adapter
        rv_page_around_content.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
    }
}