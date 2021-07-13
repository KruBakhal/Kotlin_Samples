package com.example.kotlin_samples.MoreScreen.View

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_samples.MoreScreen.Adapter.BottomAdapter
import com.example.kotlin_samples.MoreScreen.Adapter.MiddleAdapter
import com.example.kotlin_samples.MoreScreen.Adapter.SliderAdapterExample
import com.example.kotlin_samples.MoreScreen.Model.api.Datum
import com.example.kotlin_samples.MoreScreen.Model.api.MainResponse
import com.example.kotlin_samples.MoreScreen.Model.api.SubCategory
import com.example.kotlin_samples.MoreScreen.Model.api.SubCategory__1
import com.example.kotlin_samples.R
import com.example.kotlin_samples.MoreScreen.Service.FetchDataRepository
import kotlinx.android.synthetic.main.activity_more_app.*
import kotlinx.android.synthetic.main.lay_more_item.*
import kotlinx.android.synthetic.main.lay_progress_item.*
import kotlinx.android.synthetic.main.lay_retry_item.*

class MoreAppActivity : AppCompatActivity() {

    private lateinit var bottomAdapter: BottomAdapter
    private lateinit var middleAdapter: MiddleAdapter
    var context: Context = this
    var sliderAdapterExample: SliderAdapterExample? = null
    var fetchLiveData: FetchDataRepository? = null

    //    var activityBinding: ActivityMoreAppBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_app)
//        activityBinding = ActivityMoreAppBinding.inflate(layoutInflater)
//        setContentView(activityBinding!!.root)


        fetchLiveData = ViewModelProvider(this).get(FetchDataRepository::class.java)
        fetchLiveData!!.setInterNetConnectivity_Context(this)

        // Internet Connectivity
        fetchLiveData?.getInternetConnectivity()?.observe(this, Observer { t: Boolean ->
            if (t) {
                btnStatus.setImageResource(R.drawable.outline_wifi_white_24)

            } else {
                btnStatus.setImageResource(R.drawable.outline_wifi_off_white_24)
                if (fetchLiveData?.showProgressBar?.value == FetchDataRepository.ProgressUIType.CANCEL
                    && fetchLiveData?.responseMainMutableLiveData?.value?.appCenter == null
                ) {
                    lay_Retry.visibility = View.VISIBLE
                    lay_Pg.visibility = View.GONE
                    lay_More_item.visibility = View.GONE
                }
            }
        })
        // Progress Layout Display
        fetchLiveData?.getShowProgressStatus()?.observe(
            this,
            Observer<FetchDataRepository.ProgressUIType> { t: FetchDataRepository.ProgressUIType? ->
                if (t == FetchDataRepository.ProgressUIType.SHOW) {
                    progress_bar.visibility = View.VISIBLE
                    lay_More_item.visibility = View.GONE
                    lay_Retry.visibility = View.GONE
                    lay_Pg.visibility = View.VISIBLE
                } else if (t == FetchDataRepository.ProgressUIType.DIMISS) {
                    progress_bar.visibility = View.GONE
                    lay_Pg.visibility = View.GONE
                    lay_Retry.visibility = View.GONE
                    lay_More_item.visibility = View.VISIBLE
                } else {
                    progress_bar.visibility = View.GONE
                    lay_Pg.visibility = View.GONE
                    lay_Retry.visibility = View.VISIBLE
                    lay_More_item.visibility = View.GONE

                }
            })

        // UI Data
        fetchLiveData!!.getMainResponse()
            ?.observe(this, Observer<MainResponse> { t: MainResponse? ->
                sliderAdapterExample?.renewItems(t?.home?.get(0)?.subCategory as MutableList<SubCategory__1>)
                middleAdapter?.renewItems(t?.data as MutableList<Datum>)
                bottomAdapter?.renewItems(t?.appCenter?.get(0)?.subCategory as MutableList<SubCategory>)
            })


        // click event and init UI
        img12.setOnClickListener {

            fetchLiveData?.retryCall();

        }
        btnBack.setOnClickListener {

            onBackPressed()

        }

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