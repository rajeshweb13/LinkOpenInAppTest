package com.linkopeninapptest

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.tabs.TabLayout
import com.linkopeninapptest.adapter.LinksAdapter
import com.linkopeninapptest.data.DashboardModel
import com.linkopeninapptest.databinding.ActivityMainBinding
import com.linkopeninapptest.utils.DaysValueFormatter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewmodel: MainViewModel by viewModels()
    private lateinit var binding:ActivityMainBinding
    var list:DashboardModel?=null
    lateinit var adapter: LinksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTabLayout()

        setGreetings()
        viewmodel.getDashboardData()
        viewmodel.data.observe(this) {data ->
            binding.apply {
                name.text = data?.top_location
                list = data
                setLineChart(data?.data?.overall_url_chart)
                setListData(data, isFirstTime = true, isRecentLink = false)
            }
        }
    }

    private fun setGreetings() {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR)
        binding.appCompatTextView.text = if(currentHour < 13 && currentHour < 4) "Good afternoon!" else "Good morning!"
        binding.appCompatTextView.text = if(currentHour < 18 && currentHour < 18 ) "Good evening!" else "Good evening!"
    }

    private fun setLineChart(overallUrlChart: HashMap<String, Int>?) {
        binding.apply {

            val lineEntries =  ArrayList<Entry>()
            val xvalue = ArrayList<String>()
            var incr =0

            if (overallUrlChart != null) {
                for((k,v) in overallUrlChart) {
                    lineEntries.add(Entry(v.toFloat(),incr++.toFloat()))
                    xvalue.add(k)
                }
            }

            lineChart.getXAxis().setDrawGridLines(false)
            lineChart.getAxisLeft().setDrawGridLines(false)
            lineChart.getAxisRight().setDrawGridLines(false)

            val rightYAxis: YAxis = lineChart.getAxisRight()
            rightYAxis.isEnabled = false
            val leftYAxis: YAxis = lineChart.getAxisLeft()
            leftYAxis.isEnabled = true
            val topXAxis: XAxis = lineChart.getXAxis()
            topXAxis.isEnabled = false

            val xAxis: XAxis = lineChart.getXAxis()
            xAxis.granularity = 1f
            xAxis.setCenterAxisLabels(true)
            xAxis.isEnabled = true
            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.textSize =7f
            xAxis.valueFormatter = IndexAxisValueFormatter(xvalue);

            lineChart.getDescription().setEnabled(true)
            val description = Description()

            description.setText("Days")
            description.setTextSize(15f)

            val lindataset= LineDataSet(lineEntries,"URLs")
            lindataset.color = resources.getColor(R.color.darkgreen)
            lindataset.mode = LineDataSet.Mode.CUBIC_BEZIER

            lindataset.circleRadius = 0f
            lindataset.setDrawFilled(true)
            lindataset.fillColor = resources.getColor(R.color.green)
            lindataset.fillAlpha = 30


            val data = LineData(lindataset)
            lineChart.data = data
            lineChart.setBackgroundColor(resources.getColor(R.color.white))
            lineChart.animateXY(3000,3000)

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setListData(data: DashboardModel?, isFirstTime:Boolean, isRecentLink: Boolean) {

        LinksAdapter.isRecentLinkFromMain = isRecentLink
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = data?.data?.let {
            LinksAdapter(MainActivity@this, it.top_links, it.recent_links, isRecentLink)
        }?.apply {
            itemClickRecentLinkListner = {
                Toast.makeText(applicationContext, it.title, Toast.LENGTH_LONG).show()
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_VIEW
                sendIntent.data =Uri.parse(it.web_link)
                sendIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(sendIntent)
            }
            itemClickTopLinkListner = {
                Toast.makeText(applicationContext, it.title, Toast.LENGTH_LONG).show()
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_VIEW
                sendIntent.data =Uri.parse(it.web_link)
                sendIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(sendIntent)
            }
        }!!

        if (isFirstTime) {
            binding.recyclerView.adapter = adapter
        } else {
            adapter?.notifyDataSetChanged()
        }


    }

    private fun setTabLayout() {
        binding.apply {
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Top Links"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Recent Links"))
            tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position) {
                        0 -> {
                            setListData(list, isFirstTime = false, isRecentLink = false)
                        }
                        1 -> {
                            setListData(list,isFirstTime = false, isRecentLink = true)
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })
        }
    }
}