package com.irsc.challenge.utils
import android.app.Application
import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}

@BindingAdapter("tintStr")
fun ImageView.setImageTint(color: String) {
    setColorFilter(Color.parseColor(color))

}

@BindingAdapter("colorStr")
fun TextView.setTextColorStr(color: String) {
    setTextColor(Color.parseColor(color))
}
