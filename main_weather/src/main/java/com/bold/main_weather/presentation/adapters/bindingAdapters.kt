package com.bold.main_weather.presentation.adapters

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bold.main_weather.R
import com.bumptech.glide.Glide

@BindingAdapter("android:textAttrChanged")
fun setListeners(
    editText: EditText, attrChange: InverseBindingListener
) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            attrChange.onChange()
        }
    })
}

@InverseBindingAdapter(attribute = "android:text")
fun getTextString(editText: EditText): String {
    return editText.text.toString()
}


@BindingAdapter("setLiveDataText")
fun TextView.setText(liveDataText: LiveData<String?>) {
    liveDataText.observe(this.findViewTreeLifecycleOwner()!!) { str ->
        text = str
    }
}

@BindingAdapter("imageUrlFromLivedata")
fun ImageView.bindImageUrlFromLivedata(nextDayData: LiveData<String?>) {
    val circularProgressDrawable = CircularProgressDrawable(this.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.backgroundColor = Color.WHITE
    circularProgressDrawable.start()

    nextDayData.observe(this.findViewTreeLifecycleOwner()!!) { nextDayData ->
        Glide.with(this.context).load("https:${nextDayData}")
            .placeholder(circularProgressDrawable)
            .error(R.drawable.no_weather).into(this)
    }
}


@BindingAdapter("imageUrl")
fun ImageView.bindImageUrl(nextDayData: String) {

    val circularProgressDrawable = CircularProgressDrawable(this.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Glide.with(this.context).load("https:${nextDayData}")
        .placeholder(circularProgressDrawable)
        .error(R.drawable.no_weather).into(this)
}

@BindingAdapter("cityName", "cityCountry")
fun TextView.setCityText(name: String?, country: String?) {
    text = if (name != null && country != null) {
        "$name, $country"
    } else {
        "Unknown"
    }
}

@BindingAdapter("isPressed")
fun setIsPressed(view: LinearLayout, isPressed: Boolean) {
    view.isPressed = isPressed
}