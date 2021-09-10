package com.irsc.challenge.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.irsc.challenge.R
import com.irsc.challenge.databinding.BlobViewBinding


class BlobView @JvmOverloads
constructor(private val ctx: Context, private val attrs: AttributeSet? = null, private val defStyleAttr: Int = 0)
    : RelativeLayout(ctx, attrs, defStyleAttr) {

    private var binding: BlobViewBinding = BlobViewBinding.inflate(LayoutInflater.from(context), this, true)

    var color:String? = null
    var text:String? = null

    init {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.BlobView,
            0, 0)
        color = typedArray.getString(R.styleable.BlobView_blob_color)
        text = typedArray.getString(R.styleable.BlobView_blob_text)
        typedArray.recycle()


        updateView()
    }



    fun setBlobColor(color:String?){
        this.color = color
        updateView()
    }

    fun setBlobText(text:String?){
        this.text = text
        updateView()
    }


    private fun updateView(){
        color?.let {
            binding.imgCircle.setColorFilter(Color.parseColor(it))
            binding.imgBorder.setColorFilter(Color.parseColor(it))
        }
        text?.let{
            binding.tvMain.text = it
        }

    }

    override fun onDraw(canvas: Canvas?) {
        updateView()
        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        updateView()
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

}

@BindingAdapter("blob_color")
fun BlobView.setBlobColor(color: String) {
    this.setBlobColor(color)
}

@BindingAdapter("blob_text")
fun BlobView.setBlobText(text: String) {
    this.setBlobText(text)
}
