package com.example.kotlin_samples.DayDreamSoft.Utils

import com.example.kotlin_samples.DayDreamSoft.Model.ParentChildModel

class Utils {


    public companion object {
        var listParentChild: ArrayList<ParentChildModel>? = null;



    }

    public fun initData(): ArrayList<ParentChildModel>? {

        listParentChild = ArrayList<ParentChildModel>()
var listParent = arrayListOf<String>("A", "B", "C", "D", "E", "F", "G", "H")
 var listChild = arrayListOf<String>("A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1")

        (listParentChild as ArrayList<ParentChildModel>).add(ParentChildModel("No Parent Selected",
            null))

        for (item in listParent) {
            (listParentChild as ArrayList<ParentChildModel>).add(ParentChildModel(item,
                listChild))

        }

        return listParentChild
    }

}