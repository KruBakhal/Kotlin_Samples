package com.example.kotlin_samples

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_samples.Adapter.BottomAdapter
import com.example.kotlin_samples.Adapter.MiddleAdapter
import com.example.kotlin_samples.Adapter.SliderAdapterExample
import com.example.kotlin_samples.Model.api.Datum
import com.example.kotlin_samples.Model.api.MainResponse
import com.example.kotlin_samples.Model.api.SubCategory
import com.example.kotlin_samples.Model.api.SubCategory__1
import com.example.kotlin_samples.Repository.FetchLiveData
import kotlinx.android.synthetic.main.activity_more_app.*

class MoreAppActivity : AppCompatActivity() {

    private lateinit var bottomAdapter: BottomAdapter
    private lateinit var middleAdapter: MiddleAdapter
    var context: Context = this
    var sliderAdapterExample: SliderAdapterExample? = null
    var fetchLiveData: FetchLiveData? = null
//    var activityBinding: ActivityMoreAppBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_app)
//        activityBinding = ActivityMoreAppBinding.inflate(layoutInflater)
//        setContentView(activityBinding!!.root)


        fetchLiveData = ViewModelProvider(this).get(FetchLiveData::class.java)
        fetchLiveData!!.getMainResponse()
            ?.observe(this, Observer<MainResponse> { t: MainResponse? ->
                sliderAdapterExample?.renewItems(t?.home?.get(0)?.subCategory as MutableList<SubCategory__1>)
                middleAdapter?.renewItems(t?.data as MutableList<Datum>)
                bottomAdapter?.renewItems(t?.appCenter?.get(0)?.subCategory as MutableList<SubCategory>)

            })

        initAutoSliderAdapter();
        initMiddleAdapter();
        initBottomAdapter();

    }

    private fun initBottomAdapter() {
        recyclerViewBottom.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        bottomAdapter = BottomAdapter(context)
        recyclerViewBottom.adapter = bottomAdapter

    }

    private fun initMiddleAdapter() {
        middleAdapter = MiddleAdapter(context)
        recyclerViewMiddle.adapter = middleAdapter

    }

    private fun initAutoSliderAdapter() {

        sliderAdapterExample = SliderAdapterExample(context)

        fetchLiveData?.getMainResponseData()?.value?.let {
            sliderAdapterExample?.renewItems(it.home.get(0).subCategory)
        }
        imageSlider.setSliderAdapter(sliderAdapterExample!!)

    }


    override fun onBackPressed() {
//        super.onBackPressed()
        finish()
    }
}