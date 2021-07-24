package com.example.kotlin_samples.DayDreamSoft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_samples.DayDreamSoft.Adapter.MovieAdapter
import com.example.kotlin_samples.DayDreamSoft.Intermediate.BaseInterface
import com.example.kotlin_samples.DayDreamSoft.Model.ParentChildModel
import com.example.kotlin_samples.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_first.btnDropDown
import kotlinx.android.synthetic.main.activity_second.*
import java.lang.reflect.Type

class SecondActivity : AppCompatActivity() {
    private lateinit var adpater: MovieAdapter
    private var selectParent: Int = 0
    private var list: ArrayList<ParentChildModel>? = null
    private lateinit var popupWindow: PopupWindow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var type: Type = object : TypeToken<List<ParentChildModel>>() {}.type

        var msg = intent.getStringExtra("data")
        selectParent = intent.getIntExtra("pos", 0)

        list = Gson().fromJson(msg, type)


        var listChild = list?.get(selectParent)?.childList
        edtTExt1.setText(list?.get(selectParent)?.parentName)
        adpater = MovieAdapter(2, null, listChild, object : BaseInterface {
            override fun onCLickItems(position: Int) {

            }
        })
        recyclerViewMiddle.layoutManager = GridLayoutManager(this, 2)
        recyclerViewMiddle.adapter = adpater


        btnDropDown.setOnClickListener {
            showPopupWindow(it)
        }
        btnNext.setOnClickListener {
            finish()

        }
    }

    fun showPopupWindow(view: View) {

        //Create a View object yourself through inflater
        val inflater =
            view.context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView: View = inflater.inflate(R.layout.pop_up_layout, null)

        //Specify the length and width through constants
        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT


        //Make Inactive Items Outside Of PopupWindow
        val focusable = true

        //Create a window with our parameters
        popupWindow = PopupWindow(popupView, width, height, focusable)

        //Set the location of the window on the screen

        popupWindow?.width = windowManager.defaultDisplay.width * 300 / 1080
        popupWindow?.height = windowManager.defaultDisplay.height * 500 / 1080

        //Initialize the elements of our window, install the handler
        val recyclerView: RecyclerView = popupView.findViewById(R.id.recyclerView)
        var adpater = MovieAdapter(1, list, null, object : BaseInterface {
            override fun onCLickItems(position: Int) {

                selectParent = position
                adpater.notifyListChild(list?.get(selectParent)?.childList)
//                edtTExt.setText(list?.get(selectParent)?.parentName)
                edtTExt1.setText(list?.get(selectParent)?.parentName)
                if (popupWindow!!.isShowing) {
                    popupWindow?.dismiss()
                }
            }
        })
        recyclerView.adapter = adpater
        popupWindow?.showAsDropDown(view, 0, 0)

    }
/*
    fun showPopupWindow(view: View) {

        //Create a View object yourself through inflater
        val inflater =
            view.context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView: View = inflater.inflate(R.layout.pop_up_layout, null)

        //Specify the length and width through constants
        val width = LinearLayout.LayoutParams.MATCH_PARENT
        val height = LinearLayout.LayoutParams.MATCH_PARENT


        //Make Inactive Items Outside Of PopupWindow
        val focusable = true

        //Create a window with our parameters
        popupWindow = PopupWindow(popupView, width, height, focusable)

        //Set the location of the window on the screen

//        popupWindow?.width = windowManager.defaultDisplay.width * 300 / 1080
//        popupWindow?.height = windowManager.defaultDisplay.height * 500 / 1080

        //Initialize the elements of our window, install the handler
        val recyclerView: RecyclerView = popupView.findViewById(R.id.recyclerView)
        var adpater: MovieAdapter = MovieAdapter(1,
            list, null, object : BaseInterface {
                override fun onCLickItems(position: Int) {

                    selectParent = position
                    edtTExt.setText(list?.get(selectParent)?.parentName)
                    if (popupWindow!!.isShowing) {
                        popupWindow?.dismiss()
                    }
                }
            })
        recyclerView.adapter = adpater
        popupWindow?.showAsDropDown(view, Gravity.CENTER, 0, 0)

        //Handler for clicking on the inactive zone of the window

    }
*/
}