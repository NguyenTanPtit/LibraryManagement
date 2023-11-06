package com.example.librarymanagement.ultils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Calendar

class DateUtils {

    companion object {
        const val DATE_FORMAT = "dd/MM/yyyy"

        fun getCurrentDate(): String {
            val current = System.currentTimeMillis()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            return dateFormat.format(current)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("SimpleDateFormat")
        fun checkValidDateBorrow(dateBorrow: String, dateLast: String): Boolean {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val dateBorrowParse = dateFormat.parse(dateBorrow)
            val dateLastParse = dateFormat.parse(dateLast)
            val period = Duration.between(
                dateBorrowParse?.toInstant() ?: Calendar.getInstance().toInstant(),
                dateLastParse?.toInstant() ?: Calendar.getInstance().toInstant()
            )
            if (period.toDays() > 3 || dateBorrowParse?.before(dateLastParse) == true) {
                return false
            }
            return true
        }

        @SuppressLint("SimpleDateFormat")
        fun stringToDate(date: String): Calendar {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val dateParse = dateFormat.parse(date)
            val calendar = Calendar.getInstance()
            calendar.time = dateParse!!
            return calendar
        }
    }

}