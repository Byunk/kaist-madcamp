package com.example.cs496_pj1.habittracker.calendar

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_pj1.MainActivity
import com.example.cs496_pj1.databinding.FragmentCalendarBinding
import com.example.cs496_pj1.habittracker.HabitTrackerDetailActivity
import com.example.cs496_pj1.models.CustomCalendar
import com.example.cs496_pj1.models.Habit
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarFragment(position: Int, start: Long, end: Long, didArrayList: ArrayList<Date>) : Fragment() {
    lateinit var mContext: Context

    lateinit var currentDate: Date
    lateinit var calendar_year_month_text: TextView
    lateinit var calendarLayout: LinearLayout
    lateinit var calendarView: RecyclerView
    var start = start
    var end = end
    var didArrayList = didArrayList


    private lateinit var binding: FragmentCalendarBinding

    var pageIndex = position

    companion object {
        var instance: CalendarFragment? = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context as HabitTrackerDetailActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        pageIndex -= (Int.MAX_VALUE / 2)

        calendar_year_month_text = binding.calendarYearMonthText
        calendarLayout = binding.calendarLayout
        calendarView = binding.calendarView

        val date = Calendar.getInstance().run {
            add(Calendar.MONTH, pageIndex)
            time
        }
        currentDate = date

        var datetime: String = SimpleDateFormat(
            "yyyy년 MM월",
            Locale.KOREA
        ).format(date.time)
        calendar_year_month_text.text = datetime

        // Init Calendar VIew
        binding.calendarView.layoutManager = GridLayoutManager(mContext, CustomCalendar.DAYS_OF_WEEK)
        binding.calendarView.adapter = CalendarAdapter(mContext, calendarLayout, currentDate, start, end,
            didArrayList)
    }
}