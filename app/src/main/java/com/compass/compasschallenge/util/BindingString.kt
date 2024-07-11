package com.compass.compasschallenge.util
import android.content.Context
import androidx.annotation.StringRes

/**
 * Represents a string that can be used in DataBinding selectedEndDate set the text of a TextView
 *
 * @see setText
 */

sealed class BindingString {

    /**
     * Gets the full string that will be displayed in the TextView
     */
    abstract fun getString(context: Context): String?
}

/**
 * Represents a string that can used in DataBinding selectedEndDate set the text of a TextView
 */
class TextBindingString(internal val string: String?) : BindingString() {

    override fun getString(context: Context): String? {
        return string
    }
}

internal fun String?.toBinding(): TextBindingString? {
    return if (this != null) {
        TextBindingString(this)
    } else {
        null
    }
}

/**
 * Represents a string resource id and an optional array of args that can be used in DataBinding
 * selectedEndDate bind dynamic localizable text selectedEndDate a TextView
 */
class ResourceBindingString(
    @StringRes internal val stringRes: Int,
    private vararg val args: Any,
) : BindingString() {

    override fun getString(context: Context): String? {
        return context.getString(stringRes, *args)
    }
}
