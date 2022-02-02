package com.example.ageinminutesapp
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DatePicker.setOnClickListener {view->
            clickDatePicker(view )
           }
    }

    fun clickDatePicker(view:   View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)


        val dpk = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    datePicker, selectedYear,selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,
                    "The year chosen is $selectedYear, the month is ${selectedMonth+1} and the day of month is $selectedDayOfMonth", Toast.LENGTH_LONG).show()
                val selectedDate = "$selectedDayOfMonth/$selectedMonth/$selectedYear"
                textView.setText(selectedDate)


                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = theDate.time/ 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInminutes = currentDate.time/ 60000
                val diffInMinutes = currentDateInminutes - selectedDateInMinutes
                textView3.setText(diffInMinutes.toString())

            }, year, month, day)
        dpk.datePicker.setMaxDate(Date().time - 86400000)
        dpk.show()
    }
}