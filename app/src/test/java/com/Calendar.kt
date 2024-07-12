package com

import java.util.Date

class Calendar(){
    val listAppointment = mutableListOf<Appointment>()

    fun addAppointment(appointment: Appointment):Boolean{
        var isOverlap:Boolean = false
        if(listAppointment.isNotEmpty()){
         //pregunto si mi nuevo appointment (startTime), está entre el rango de
         // fechas de alguno de los appointments ya agregados, entonces le mando un msg de feedback al user
            listAppointment.forEach {
                if(appointment.startTime >= it.startTime && appointment.startTime <= it.endTime){
                    isOverlap=true
                }
            }
            if(!isOverlap){
                listAppointment.add(appointment)
                return true
            }
        }
        return false
    }

    data class Appointment(val appointMentName:String, val startTime:Date, val endTime:Date, val client:String)
}
