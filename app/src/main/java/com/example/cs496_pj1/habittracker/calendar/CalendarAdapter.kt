package com.example.cs496_pj1.habittracker.calendar

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_pj1.R
import com.example.cs496_pj1.models.CustomCalendar
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarAdapter(
    val context: Context, val calendarLayout: LinearLayout, val date: Date, var start: Date? = null, var end: Date? = null
    ) : RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>() {

    var dataList: ArrayList<Int> = arrayListOf()
    var customCalendar: CustomCalendar = CustomCalendar(date)

    val calendarMonth = SimpleDateFormat("MM", Locale.KOREA).format(date).toInt()
    val calendarYear = SimpleDateFormat("yyyy", Locale.KOREA).format(date).toInt()
    init {
        customCalendar.initBaseCalendar()
        dataList = customCalendar.dateList
    }

    override fun onBindViewHolder(holder: CalendarItemHolder, position: Int) {
        // Configure Layout
        val h = calendarLayout.height / 6
        holder.itemView.layoutParams.height = h

        holder.bind(dataList[position], position, context)
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

            if ((dataList[position] == dateInt) && isMatchYearMonth() ){
                itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
                itemCalendarDateText.setTextSize(Dimension.SP, 25F)
            }

            // 현재 월의 1일 이전, 현재 월의 마지막일 이후 값의 텍스트를 회색처리
            if (position < firstDateIndex || position > lastDateIndex) {
                //itemCalendarDateText.setTextColor(Color.parseColor("#FFFFFF")) //("#676d6e"))
                itemCalendarDateText.setTextColor(getColor(context, R.color.whiteBlue))
                //itemCalendarDotView.background = null
            }

            // Testing
            start = Calendar.getInstance().run {
                set(2022, 6, 30)
                time
            }
            end = Calendar.getInstance().run {
                set(2022, 7, 18)
                time
            }

            // Specify if date is in between from and end Date
            if (start != null && end != null) {
                val todayDate = Calendar.getInstance().run {
                    set(calendarYear, calendarMonth, dataList[position])
                    time
                }

                if (!(position < firstDateIndex || position > lastDateIndex) && todayDate.compareTo(start!!) >= 0 && todayDate.compareTo(end!!) <= 0) {
                    itemCalendarDateText.setTextSize(Dimension.SP, 25F)
                    itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
                    itemCalendarDateText.setTextColor(Color.parseColor("#87CEEB"))
                }
            }
/*
            itemView.setOnClickListener {
                if (!(position < firstDateIndex || position > lastDateIndex)) {
                    val intent = Intent(context, CalendarDetailActivity::class.java).apply {
                        val year = SimpleDateFormat("yyyy", Locale.KOREA).format(date).toString()
                        val month = SimpleDateFormat("MM", Locale.KOREA).format(date).toString()
                        val day = dataList[position]
                        val dateString = year + "년 " + month + "월 " + day + "일"

                        putExtra("date", dateString)
                        //putExtra("number", item.number)
                        //addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }.run { context.startActivity(this) }
                }
            }*/
        }

        private fun isMatchYearMonth(): Boolean {
            //val monthInt = SimpleDateFormat("MM", Locale.KOREA).format(date).toInt()
            val actualMonth = SimpleDateFormat("MM", Locale.KOREA).format(Date()).toInt()
            val actualYear = SimpleDateFormat("yyyy", Locale.KOREA).format(Date()).toInt()

            return calendarMonth == actualMonth && calendarYear == actualYear

        }

    }
}
