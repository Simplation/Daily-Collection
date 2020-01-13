package com.example.app

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.Utils
import java.util.ArrayList
import com.github.mikephil.charting.formatter.*
import com.example.app.R

/**
 * 线性图表
 */
class ChartLineUtils(private var chart: LineChart,
                     private var mContext: Context,
                     private var valueX: ArrayList<String>) {

    init {
        // background color
        chart.setBackgroundColor(Color.WHITE)

        // 设置四周间隔
        chart.setExtraOffsets(5f, 10f, 5f, 10f)

        // disable description text
        chart.description.isEnabled = false
        // force pinch zoom along both axis
        chart.setPinchZoom(false)
        // enable touch gestures
        chart.setTouchEnabled(false)

        // set listeners
//        chart.setOnChartValueSelectedListener(this)
        chart.setDrawGridBackground(false)

        chart.isDragEnabled = true
        chart.axisRight.isEnabled = false
        chart.setScaleEnabled(true)

        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        // X 轴颜色
//        xAxis.axisLineColor = R.color.color11CBC4
        // 是否显示竖直标尺线
        xAxis.setDrawGridLines(false)
        // 是否显示 X 轴数值
        xAxis.setDrawLabels(true)
        xAxis.textColor = ContextCompat.getColor(mContext, R.color.black)
        xAxis.granularity = 1f
        xAxis.setLabelCount(valueX.size, false)
        xAxis.valueFormatter = IndexAxisValueFormatter(valueX)

        xAxis.enableGridDashedLine(10f, 0f, 0f)

        val yAxis = chart.axisLeft
        // disable dual axis (only use LEFT axis)
        // Y 轴显示个数
        yAxis.setLabelCount(5, false)
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        // 左边的 Y 轴是否显示
        yAxis.setDrawAxisLine(true)
        yAxis.gridColor = ContextCompat.getColor(mContext, R.color.black)
        yAxis.gridLineWidth = 1f
        yAxis.textColor = ContextCompat.getColor(mContext, R.color.black)
        yAxis.spaceTop = 15f
//        yAxis.valueFormatter = LargeValueFormatter()//Y轴数据过大显示K\M
        //这个替换setStartAtZero(true)
//        yAxis.axisMinimum = 0f
        // horizontal grid lines
        yAxis.enableGridDashedLine(10f, 0f, 0f)


        // draw limit lines behind data instead of on top
        yAxis.setDrawLimitLinesBehindData(true)
        xAxis.setDrawLimitLinesBehindData(true)


        val l = chart.legend

        // draw legend entries as lines
        l.form = Legend.LegendForm.NONE
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.textColor = ContextCompat.getColor(mContext, R.color.black)
        l.yEntrySpace = 15f
        l.xOffset = -10f//左右移动
        l.yOffset = 10f

        chart.animateXY(1500, 1500)
    }

    fun setData(values: ArrayList<Entry>, title: String) {


        val set1: LineDataSet
        if (chart.data != null && chart.data.dataSetCount > 0) {
            set1 = chart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            set1.notifyDataSetChanged()
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, title)

            set1.setDrawIcons(false)

            // black lines and points
            set1.color = ContextCompat.getColor(mContext, R.color.report_text_color)
            set1.setCircleColor(ContextCompat.getColor(mContext, R.color.report_text_color))
            // line thickness and point size
            set1.lineWidth = 4f
            set1.circleRadius = 4.5f
            set1.circleHoleRadius = 2f

            // draw points as solid circles
            set1.setDrawCircleHole(true)

            // text size of values
            set1.valueTextSize = 10f
            set1.valueTextColor = ContextCompat.getColor(mContext, R.color.black)

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 0f, 0f)

            // set the filled area
            set1.setDrawFilled(true)
            set1.fillFormatter = IFillFormatter { _, _ -> chart.axisLeft.axisMinimum }

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                val drawables = ContextCompat.getDrawable(mContext, R.drawable.gif_tag)
                set1.fillDrawable = drawables
            } else {
                set1.fillColor = ContextCompat.getColor(mContext, R.color.report_text_color)
            }

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1) // add the data sets

            // create a data object with the data sets
            val data = LineData(dataSets)
//            data.setValueFormatter(LargeValueFormatter())//Y轴数据过大显示K\M
            //小数要重写这个方法，不然会取整
            data.setValueFormatter(object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return value.toString()
                }
            })
            // set data
            chart.data = data
        }
    }

}