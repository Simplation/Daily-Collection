package com.example.app

import android.content.Context
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.example.app.R

/** 
 * 分组柱状图工具类
 */
class BarChartUtils(private var chart: BarChart, private var mContext: Context) {

    init {
        // 设置四周的间隔距离
        chart.setExtraOffsets(5f, 10f, 5f, 10f)
        // 在条形图的上方绘制值
        chart.setDrawValueAboveBar(true)
        // 统计图表的操作
        chart.setDrawBarShadow(false)
//        chart.setDrawValueAboveBar(true)
        chart.description.isEnabled = false
        // 如果60多个条目显示在图表,drawn没有值
//        chart.setMaxVisibleValueCount(60)
        // 扩展现在只能分别在x轴和y轴
        chart.setPinchZoom(false)
        //是否显示表格颜色
        chart.setDrawGridBackground(false)
        // 设置比例
        chart.setScaleEnabled(false)
        // 柱状图上面的数值是否在柱子上面
        chart.setDrawValueAboveBar(false)
        // 是否在折线图上添加边框
//        chart.setDrawBorders(false)

        // 图例设置
        val l = chart.legend
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.orientation = Legend.LegendOrientation.HORIZONTAL

        l.setDrawInside(true)
        l.yOffset = 0f
        // y 轴距离
        l.xOffset = 10f
        l.yEntrySpace = 0f
        // 水平间距
        l.xEntrySpace = 30f
        // 图例的字体大小
        l.textSize = 9f
        // 图的大小
        l.formSize = 6f
        // 图例的形状
        l.form = Legend.LegendForm.SQUARE

        val xAxis = chart.xAxis
        // 设置 X 轴的位置 默认在上方
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        // 是否显示竖直标尺线
        xAxis.setDrawGridLines(false)
        // 标签设置居中
        xAxis.setCenterAxisLabels(true)
        // 是否显示 X 轴数值
        xAxis.setDrawLabels(true)
        xAxis.axisLineWidth = 1f
        // X 轴显示个数
        xAxis.labelCount = list().size
        // x 轴 显示字符
        xAxis.valueFormatter = IndexAxisValueFormatter(list())
        // 间隔
        xAxis.granularity = 1f


        val leftAxis = chart.axisLeft
        // Y 轴显示个数
        leftAxis.setLabelCount(4, false)
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        // 左边的 Y 轴是否显示
        leftAxis.setDrawAxisLine(true)
        // Y 轴数据过大显示 K\M
        leftAxis.valueFormatter = LargeValueFormatter()
        leftAxis.gridColor = ContextCompat.getColor(mContext, R.color.background_color)
        leftAxis.gridLineWidth = 1f
        leftAxis.spaceTop = 15f
        // 这个替换 setStartAtZero(true)
        leftAxis.axisMinimum = 0f
//        leftAxis.axisMaximum = 50f

        val rightAxis = chart.axisRight
        // 右侧是否显示 Y 轴数值
//        rightAxis.setDrawLabels(false)
        // 是否显示最右侧竖线
        rightAxis.isEnabled = false
//        rightAxis.setDrawGridLines(false)
//        rightAxis.setLabelCount(8, false)
//        rightAxis.spaceTop = 15f
//        rightAxis.axisMinimum = 0f
//        rightAxis.axisMaximum = 50f


//        setData()
        // X、Y 轴动画
        chart.animateXY(1500, 1500)
    }



    //设置数据
    fun setData(values1: ArrayList<BarEntry>, values2: ArrayList<BarEntry>, values3: ArrayList<BarEntry>) {

        val list = list()
        val groupSpace = 0.55f
        val barSpace = 0.00f // x3 DataSet 组之前的距离
        val barWidth = 0.15f // x3 DataSet
        // (0.00 + 0.1) * 3 + 0.7 = 1，即一个间隔为一组，包含三个柱图 -> interval per "group"

        val set1: BarDataSet
        val set2: BarDataSet
        val set3: BarDataSet
        if (chart.data != null && chart.data.dataSetCount > 0) {
            set1 = chart.data.getDataSetByIndex(0) as BarDataSet
            set2 = chart.data.getDataSetByIndex(1) as BarDataSet
            set3 = chart.data.getDataSetByIndex(2) as BarDataSet
            set1.values = values1
            set2.values = values2
            set3.values = values3
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(values1, "AAA")
            set1.setColors(ColorTemplate.rgb("#73B8F7"))
            set2 = BarDataSet(values2, "BBB")
            set2.setColors(ColorTemplate.rgb("#EA7B20"))
            set3 = BarDataSet(values3, "CCC")
            set3.setColors(ColorTemplate.rgb("#E32210"))

            // 设置渐变色
            /*val startColor1 = ContextCompat.getColor(mContext, R.color.color7bc3ff)
            val endColor1 = ContextCompat.getColor(mContext, R.color.color3fa7ff)

            val startColor2 = ContextCompat.getColor(mContext, R.color.colorFFe99c)
            val endColor2 = ContextCompat.getColor(mContext, R.color.colorFFD43F)

            val startColor3 = ContextCompat.getColor(mContext, R.color.colorFFAADC)
            val endColor3 = ContextCompat.getColor(mContext, R.color.colorFF76c7)

            set1.setGradientColor(startColor1, endColor1)//设置数据渐变色
            set2.setGradientColor(startColor2, endColor2)//设置数据渐变色
            set3.setGradientColor(startColor3, endColor3)//设置数据渐变色*/

            set1.setDrawValues(false)  // 显示/不显示数值
            set1.isHighlightEnabled = false

            set2.setDrawValues(false) // 显示/不显示数值
            set2.isHighlightEnabled = false

            set3.setDrawValues(false)  // 显示/不显示数值
            set3.isHighlightEnabled = false

            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)
            dataSets.add(set2)
            dataSets.add(set3)

            val data = BarData(dataSets)
            data.setValueTextSize(10f)
            // Y轴数据过大显示K\M
            data.setValueFormatter(LargeValueFormatter())
            // 设置数据
            chart.data = data
        }
        chart.barData.barWidth = barWidth
        chart.xAxis.axisMinimum = 0f

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        chart.xAxis.axisMaximum = chart.barData.getGroupWidth(groupSpace, barSpace) * list.size + 0
        chart.groupBars(0f, groupSpace, barSpace)
        chart.invalidate()
    }


    fun list(): List<String> {
        val values = ArrayList<String>()
        values.add("A")
        values.add("B")
        values.add("C")
        values.add("D")
        return values
    }
}
