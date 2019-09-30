package com.example.pcmart.Utilites

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.example.pcmart.R

open class BaseActivity : AppCompatActivity() {
    class Data (var image:String,var name : String,var price:String)
    protected var captials = arrayOf(
        "Select...", "Al Anbar", "Al Diwaniyah", "Babil", "Baghdad", "Basra", "Dhi Qar", "Diyala", "Dohuk", "Erbil"
        , "Halabja", "Karbala", "Kirkuk", "Maysan", "Mosul", "Muthanna", "Najaf", "Saladin", "Sulaymaniyah", "Wasit"
    )
    protected var sortingItems = arrayOf("Price : Higher to Lower",
        "Price : Lower to Higher",
        "Name : A to Z",
        "Name : Z to A"
    )

// price must be INT>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    var AllItemsData = arrayListOf<Data>(
    Data(
        "ic_iconfinder_youtube_317714",
        "youtube",
        "100$"
    ),
    Data(
        "ic_iconfinder_youtube_317714",
        "face",
        "1300$"
    ),
    Data(
        "ic_iconfinder_youtube_317714",
        "ic_iconfinder_youtube",
        "1040$"
    ),
    Data(
        "ic_iconfinder_youtube_317714",
        "Gtx 1070 Evga superclocked",
        "600$"
    ),
    Data(
        "repair_guy",
        "kkkkkkkkkkkkkkk",
        "300$"
    ),
    Data(
        "ic_iconfinder_youtube_317714",
        "ic_iconfinder_youtube",
        "10640$"
    )
)
    var cartItemsData = arrayListOf<Data>(
        Data(
            "ic_iconfinder_youtube_317714",
            "1",
            "100$"
        ),
        Data(
            "ic_iconfinder_youtube_317714",
            "2",
            "100$"
        ),
        Data(
            "ic_iconfinder_youtube_317714",
            "3",
            "100$"
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkSDK()

    }



    fun spannapleString(tv: TextView, context: Context) {

        var sp = SpannableString(tv.text)
        var fc = ForegroundColorSpan(Color.WHITE)
        var bcs = BackgroundColorSpan(context.getColor(R.color.colorLogoRed))
        sp.setSpan(fc, 13, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        sp.setSpan(bcs, 13, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv.text = sp

    }

    fun checkSDK() {
        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT;
        }

    }

    fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val win = activity.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

}
