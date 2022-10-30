package com.udacity.asteroidradar

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.contentDescription = "this asteroid is hazardous"
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.contentDescription = "this asteroid is safe"
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.contentDescription = "this asteroid is hazardous"
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.contentDescription = "this asteroid is safe"
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("imgOfDay")
fun uri(img: ImageView, picture: PictureOfDay?) {
    picture?.let {
        if (it.mediaType == "image") {
            Picasso.get().load(it.url).into(img)
            img.contentDescription=img.context.getString(R.string.nasa_picture_of_day_content_description_format,picture.title)
        }
    }

}

@BindingAdapter("imgTitle")
fun title(txt: TextView, picture: PictureOfDay?) {
    picture?.let {
        if (it.mediaType == "image") {
            txt.text = it.title
        }
    }
}