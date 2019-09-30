package com.example.pcmart.Activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pcmart.Adapters.CartAdapter
import com.example.pcmart.Utilites.BaseActivity
import com.example.pcmart.R
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        spannapleString(tv_cartLogo, this)
        setAdapter()

    }
    fun setAdapter() {
        var adapter = CartAdapter(this, cartItemsData)
        rv_cartItemList.adapter = adapter
        adapter.mListner = object : CartAdapter.onCostumeItemClickListener {
            override fun onDeleteClick(position: Int, data: Data, view: View) {
                var popUp = PopupMenu(this@CartActivity, view)
                popUp.setOnMenuItemClickListener { item ->
                    if (item?.itemId == R.id.menu_delete) {
                        cartItemsData.remove(data)
                        adapter.notifyItemRemoved(position)
                        true
                    } else false
                }
                popUp.inflate(R.menu.popup_deletecart_menu)
                popUp.show()
            }
            override fun onItemClick(position: Int, data: Data, view: View) {
                alertbuild(data)
            }
        }
        rv_cartItemList.layoutManager = LinearLayoutManager(this)
        rv_cartItemList.hasFixedSize()

    }
    fun alertbuild(data: Data) {
        var alertDialog = AlertDialog.Builder(this)
        val layinf = LayoutInflater.from(this@CartActivity).inflate(R.layout.cart_item_dialog,null)
        var mg = layinf.findViewById<ImageView>(R.id.mg_cart_image_dialog)
        var tv_name = layinf.findViewById<TextView>(R.id.tvName_cart_dialog)
        var tv_price = layinf.findViewById<TextView>(R.id.tvPrice_cart_dialog)
        mg.setImageResource(resources.getIdentifier(data.image,"drawable",this.packageName))
        tv_name.text = data.name
        tv_price.text = data.price
        alertDialog.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int -> })
        alertDialog.setView(layinf)
        alertDialog.show()
    }
}

