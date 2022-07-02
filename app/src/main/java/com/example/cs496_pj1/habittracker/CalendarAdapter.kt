package com.example.cs496_pj1.habittracker

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_pj1.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarAdapter(
    val context: Context, val calendarLayout: LinearLayout, val date: Date
    ) : RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>() {

    var dataList: ArrayList<Int> = arrayListOf()
    var customCalendar: CustomCalendar = CustomCalendar(date)

    init {
        customCalendar.initBaseCalendar()
        dataList = customCalendar.dateList
    }

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onBindViewHolder(holder: CalendarItemHolder, position: Int) {
        // Configure Layout
        val h = calendarLayout.height / 6
        holder.itemView.layoutParams.height = h

        holder.bind(dataList[position], position, context)
        if (itemClick != null) {
            holder.itemView.setOnClickListener { v ->
                itemClick?.onClick(v, position)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.calendar_row, parent, false)

        return CalendarItemHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    inner class CalendarItemHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var itemCalendarDateText: TextView = itemView!!.findViewById(R.id.calendar_row_text)
        //var itemCalendarDotView: View = itemView!!.item_calendar_dot_view

        fun bind(data: Int, position: Int, context: Context) {
            val firstDateIndex = customCalendar.prevTail
            val lastDateIndex = dataList.size - customCalendar.nextHead - 1

            // 날짜 표시
            itemCalendarDateText.setText(data.toString())

            // 오늘 날짜 처리
            val dateString: String = SimpleDateFormat("dd", Locale.KOREA).format(date)
            val dateInt = dateString.toInt()


            if ((dataList[position] == dateInt)) {// && isMatchYearMonth() ){
                itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
                itemCalendarDateText.setTextSize(Dimension.SP, 25F)
            }

            // 현재 월의 1일 이전, 현재 월의 마지막일 이후 값의 텍스트를 회색처리
            if (position < firstDateIndex || position > lastDateIndex) {
                itemCalendarDateText.setTextColor(Color.parseColor("#FFFFFF")) //("#676d6e"))
                //itemCalendarDotView.background = null
            }
        }
/*
        private fun isMatchYearMonth(): Boolean {
            val monthInt = SimpleDateFormat("MM", Locale.KOREA).format(date).toInt()
            val yearInt = SimpleDateFormat("yyyy", Locale.KOREA).format(date).toInt()

            val calendarMonth = customCalendar.month
            val calendarYear = customCalendar.year

            return calendarMonth == monthInt && calendarYear == yearInt

        }*/

    }
}
