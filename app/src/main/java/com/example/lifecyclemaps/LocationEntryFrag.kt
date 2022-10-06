package com.example.lifecyclemaps

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.net.URL

class LocationEntryFrag : Fragment(R.layout.locationentryfrag) {

    val viewModel: LocationViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnGo).setOnClickListener {
            val place = view.findViewById<EditText>(R.id.etPlace).text.toString()
            viewModel.moveMapToLocation(place)
        }
    }
}