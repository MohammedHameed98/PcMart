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


class ItemListAdapter(var context: Context, var itemsData: List<BaseActivity.Data>, var itemClick : (BaseActivity.Data, position : Int) -> Unit) :
    RecyclerView.Adapter<ItemListAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_layout_list, parent, false)
        return Holder(view,itemClick)
    }
    override fun getItemCount(): Int {
        return itemsData.count()
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(itemsData[position], context)
    }
    inner class Holder(itemView: View,var itemClick : (BaseActivity.Data, position : Int) -> Unit):RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.mg_itemRecyclerView)
        val name: TextView = itemView.findViewById(R.id.tvName_itemRecyclerView)
        val price: TextView = itemView.findViewById(R.id.tvPrice_itemRecyclerView)
        fun bindData(data: BaseActivity.Data, context: Context) {
            var resourceId = context.resources.getIdentifier(data.image, "drawable", context.packageName)
            image.setImageResource(resourceId)
            name.text = data.name
            price.text = data.price
            itemView.setOnClickListener{
                itemClick(data,adapterPosition)
            }
        }


    }

}