package com.example.kotlin_samples.DayDreamSoft

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_samples.DayDreamSoft.Adapter.MovieAdapter
import com.example.kotlin_samples.DayDreamSoft.Intermediate.BaseInterface
import com.example.kotlin_samples.DayDreamSoft.Model.ParentChildModel
import com.example.kotlin_samples.DayDreamSoft.Utils.Utils
import com.example.kotlin_samples.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_first.*


class FirstActivity : AppCompatActivity() {
    private var popupWindow: PopupWindow? = null
    private var list: ArrayList<ParentChildModel>? = null
    private lateinit var pwindo: PopupWindow

    private var child = arrayListOf<String>("A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1")
    var selectParent = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)


        list = Utils().initData()

        textView.setText(list?.get(selectParent)?.parentName)
        edtTExt.addTextChangedListener {
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {

                 /*   if (s.toString().isNullOrBlank()) {
                        selectParent = 0
                        textView.setText(list?.get(selectParent)?.parentName)
                    } else {


                    }*/

                }
            }
        }

        btnDropDown.setOnClickListener {
            showPopupWindow(it)
        }

        btnSubmiit.setOnClickListener {

            if (selectParent == 0) {

                var msg = edtTExt.text
                if (msg.isNullOrBlank()) {
                    Toast.makeText(this, "enter text", Toast.LENGTH_SHORT).show();
                    return@setOnClickListener
                }

                list?.add(ParentChildModel(msg.toString(), child))


                Toast.makeText(this, "Parent Added ", Toast.LENGTH_SHORT).show();

                selectParent = list?.size?.minus(1) ?: 0
                textView.setText(list?.get(selectParent)?.parentName)

            } else {
                var msg = edtTExt.text
                if (msg.isNullOrBlank()) {
                    Toast.makeText(this, "enter text to add as child", Toast.LENGTH_SHORT).show();
                    return@setOnClickListener
                }

                var list1 = ArrayList<String>()
                list1.add(msg.toString())
                list1.addAll(list?.get(selectParent)?.childList!!)
                list1.reversed()
                list?.set(selectParent,
                    ParentChildModel(list?.get(selectParent)?.parentName, list1))



                Toast.makeText(this, "itemed added as child ", Toast.LENGTH_SHORT).show();

            }

        }

        btnNext.setOnClickListener {

            if (selectParent == 0) {
                Toast.makeText(this, "enter text or select item", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(Intent(this, SecondActivity::class.java).putExtra("data",
                    Gson().toJson(list?.toList())).putExtra("pos", selectParent))
            }


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

        var adpater: MovieAdapter = MovieAdapter(1, list, null, object : BaseInterface {
            override fun onCLickItems(position: Int) {

                selectParent = position
                edtTExt.setText(list?.get(selectParent)?.parentName)
                textView.setText(list?.get(selectParent)?.parentName)
                if (popupWindow!!.isShowing) {
                    popupWindow?.dismiss()
                    popupWindow = null
                }
            }
        })
        recyclerView.adapter = adpater

        popupWindow?.showAsDropDown(view, 0, 0)

    }


}