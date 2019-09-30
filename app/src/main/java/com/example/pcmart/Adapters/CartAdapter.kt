package com.example.pcmart.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcmart.Utilites.BaseActivity
import com.example.pcmart.R

class CartAdapter (var context: Context,var cartData : ArrayList<BaseActivity.Data>) : RecyclerView.Adapter<CartAdapter.Holder> (){
     interface onCostumeItemClickListener {
        fun onItemClick(position: Int, data : BaseActivity.Data, view : View)
        fun onDeleteClick (position: Int, data : BaseActivity.Data, view : View)
    }
    lateinit var mListner : onCostumeItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var view = LayoutInflater.from(context).inflate(R.layout.cart_orders_itemst_layout,parent,false)
        return Holder(view)
    }
    override fun getItemCount(): Int {
        return cartData.count()
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
    holder.bindData(context,cartData[position])
    }

    inner class Holder(itemView : View): RecyclerView.ViewHolder (itemView) {
        var image = itemView.findViewById<ImageView>(R.id.mg_cart_image)
        var name = itemView.findViewById<TextView>(R.id.tv_cart_name)
        var price = itemView.findViewById<TextView>(R.id.tv_cart_price)
        var deleteImg = itemView.findViewById<ImageView>(R.id.mg_cart_trash)
        fun bindData (context : Context, cartData : BaseActivity.Data) {
            name.text = cartData.name
            price.text = cartData.price
            val resourceId = context.resources.getIdentifier(cartData.image,"drawable",context.packageName)
            image.setImageResource(resourceId)
            itemView.setOnClickListener {
                mListner.onItemClick(adapterPosition,cartData,it)
            }
            deleteImg.setOnClickListener {
                mListner.onDeleteClick(adapterPosition,cartData,it)
            }


        }
    }
}