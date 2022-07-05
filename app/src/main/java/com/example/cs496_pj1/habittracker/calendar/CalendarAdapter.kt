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
import com.example.cs496_pj1.models.Habit
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarAdapter(
    val context: Context, val calendarLayout: LinearLayout, val date: Date, var start: Long, var end: Long,
    var didArrayList: ArrayList<Date>
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
                itemCalendarDateText.setTextColor(getColor(context, R.color.whiteBlue))
            }

            // 목표 기간 내의 데이터를 볼드처리
            val dateStart = Date(start)
            val dateEnd = Date(end)
            // Specify if date is in between from and end Date
            if (dateStart != null && dateEnd != null) {
                val todayDate = Calendar.getInstance().run {
                    set(calendarYear, calendarMonth-1, dataList[position])
                    time
                }

                if (!(position < firstDateIndex || position > lastDateIndex) && todayDate.compareTo(dateStart) >= 0 && todayDate.compareTo(dateEnd) <= 0) {
                    itemCalendarDateText.setTextSize(Dimension.SP, 25F)
                    itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
                    itemCalendarDateText.setTextColor(Color.parseColor("#87CEEB"))
                }
            }

            // 할일을 한 날짜에 표시
            for (date in didArrayList) {
                val year = SimpleDateFormat("yyyy", Locale.KOREA).format(date).toInt()
                val month = SimpleDateFormat("MM", Locale.KOREA).format(date).toInt()-1
                val day = SimpleDateFormat("dd", Locale.KOREA).format(date).toInt()

                if (calendarYear == year && calendarMonth == month && dataList[position] == day) {
                    itemCalendarDateText.setTextSize(Dimension.SP, 25F)
                    itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
                    itemCalendarDateText.setTextColor(Color.parseColor("#636161"))
                }
            }

        }

        private fun isMatchYearMonth(): Boolean {
            //val monthInt = SimpleDateFormat("MM", Locale.KOREA).format(date).toInt()
            val actualMonth = SimpleDateFormat("MM", Locale.KOREA).format(Date()).toInt()
            val actualYear = SimpleDateFormat("yyyy", Locale.KOREA).format(Date()).toInt()

            return calendarMonth == actualMonth && calendarYear == actualYear

        }

    }
}
