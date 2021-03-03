@file:Suppress("DEPRECATION")

package com.whitezone.whitezone.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.whitezone.whitezone.API.RetrofitClient
import com.whitezone.whitezone.Model.ModelDataIndonesia
import com.whitezone.whitezone.Profile
import com.whitezone.whitezone.R
import kotlinx.android.synthetic.main.custom_actionbar.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        showIndonesia()

        return root
    }

    private fun showIndonesia() {
        RetrofitClient.instance.getIndonesia().enqueue(object :
            Callback<ArrayList<ModelDataIndonesia>> {
            override fun onFailure(call: Call<ArrayList<ModelDataIndonesia>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<ModelDataIndonesia>>,
                response: Response<ArrayList<ModelDataIndonesia>>
            ) {
                val indonesia = response.body()?.get(0)
                positif.text = indonesia?.positif
                dirawat.text = indonesia?.dirawat
                sembuh.text = indonesia?.sembuh
                meninggal.text = indonesia?.meninggal

            }
        })
    }
}