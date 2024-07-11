package com.compass.compasschallenge.util

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.compass.compasschallenge.R


fun Toast.showCustomToast(message: String, background: Drawable, activity: Activity)
{
    val layout = activity.layoutInflater.inflate (
        R.layout.message_error_custom_toast,
        activity.findViewById(R.id.wrapper)
    )

    val textView = layout.findViewById<TextView>(R.id.txv_message)
    textView.text = message
    textView.setBackgroundDrawable(background);

    this.apply {
        setGravity(Gravity.TOP, 0, 40)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}