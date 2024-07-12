package com

import com.Calendar.Appointment
import java.util.Date

class CalendarTest {

    fun testAddMethodSuccessful(){
        val calendar: Calendar
        val dateStart = Date(9)
        val dateEnd = Date(10)

        val dateStart2 = Date(11)
        val dateEnd2 = Date(12)

        calendar.addAppointment(Appointment("Meeting", dateStart, dateEnd, "Gian"))

        assert(calendar.addAppointment(Appointment("Meeting2", dateStart2, dateEnd2, "Gian")))


    }
}