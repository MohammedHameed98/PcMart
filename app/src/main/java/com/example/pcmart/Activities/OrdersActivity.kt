package com.example.pcmart.Activities

import android.os.Bundle
import com.example.pcmart.R
import com.example.pcmart.Utilites.BaseActivity
import kotlinx.android.synthetic.main.activity_orders.*

class OrdersActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
        spannapleString(tv_ordersLogo,this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}
