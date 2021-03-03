@file:Suppress("DEPRECATION")

package com.whitezone.whitezone.ui.tips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.whitezone.whitezone.R

@Suppress("DEPRECATION")
class TipsFragment : Fragment() {

    private lateinit var tipsViewModel: TipsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        tipsViewModel =
                ViewModelProviders.of(this).get(TipsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tips, container, false)
        val isiTextView: TextView = root.findViewById(R.id.isi)
        tipsViewModel.text.observe(viewLifecycleOwner, Observer {
            isiTextView.text = it
        })
        return root
    }
}