package com.linkopeninapptest.utils

import android.util.Log
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class DaysValueFormatter(val dataList:List<String>) : ValueFormatter() {

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        Log.e("@@@", value.toString())
        var position = Math.round(value)

        if (position< dataList.size) {
            return dataList[value.toInt()]
        }
        return ""
    }
}