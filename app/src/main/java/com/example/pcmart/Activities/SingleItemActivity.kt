package com.example.pcmart.Activities

import android.os.Bundle
import android.widget.Toast
import com.example.pcmart.Utilites.BaseActivity
import com.example.pcmart.R
import com.example.pcmart.Utilites.ItemDataIntent
import kotlinx.android.synthetic.main.activity_single_item.*

class SingleItemActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_item)
        setSupportActionBar(toolbar5)
        spannapleString(tv_LogoSingleActivity,this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
        var itemData = intent.getParcelableExtra<ItemDataIntent>("myKey")
        mg_ItemImage.setImageResource(this.resources.getIdentifier(itemData?.image,"drawable",this.packageName))
        tv_ItemName.text = itemData?.name
        tv_ItemPrice.text = itemData?.price
        bt_SingleItem.setOnClickListener {
            Toast.makeText(this,"me : ${itemData?.image}",Toast.LENGTH_SHORT).show()
            cartItemsData.add(Data(bt_SingleItem.toString(),tv_ItemName.text.toString(),tv_ItemPrice.text.toString()))
        }

    }
}
