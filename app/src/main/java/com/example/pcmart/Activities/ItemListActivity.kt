package com.example.pcmart.Activities

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.nav_body_layout.*
import android.view.MenuInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.pcmart.Adapters.ItemListAdapter
import com.example.pcmart.Utilites.BaseActivity
import com.example.pcmart.Utilites.ItemDataIntent
import com.example.pcmart.R

class ItemListActivity : BaseActivity() {
    lateinit var adapter: ItemListAdapter
    var item = ItemDataIntent ("","","")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
        val toggle = ActionBarDrawerToggle(
            this, darwerLayout,
            toolbar, R.string.drawer_open, R.string.drawer_close
        )
        toggle.syncState()
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_iconfinder_menu)
        setAdapter()
        setSpinner()

    }

    fun setSpinner() {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.sort_menu_button, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("Sort", "Clicked")
        return if (item.itemId == R.id.button_sort) {
            alertDialog()
            true
        } else super.onOptionsItemSelected(item)

    }
    fun alertDialog() {
        var alert = AlertDialog.Builder(this)
        var layinf = layoutInflater
        var view = layinf.inflate(R.layout.sort_alert_dialog, null)
        alert.setView(view)
        var alertD = alert.create()
        alertD.show()
    }
    fun setAdapter() {
        adapter = ItemListAdapter(this, AllItemsData) { data, position ->
            Log.d("Sort", "Clicked22")
            item.name = data.name
            item.price = data.price
            item.image = data.image
            var intent = Intent(this, SingleItemActivity::class.java)
            intent.putExtra("myKey",item)
            startActivity(intent)
        }
        rv_itemList.adapter = adapter
        rv_itemList.layoutManager = GridLayoutManager(this, 2)
        rv_itemList.hasFixedSize()
    }

    override fun onBackPressed() {
        if (darwerLayout.isDrawerOpen(GravityCompat.START))
            darwerLayout.closeDrawer(GravityCompat.START)
        else {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Are you sure!")
            alert.setMessage("Do you want to close the app?")
            alert.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                super.onBackPressed()
            }
            alert.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->

            }

            alert.show()
        }
    }


    fun navBodyOnClick(view: View) {
        when (view) {
            LL_orders -> {
                Toast.makeText(this, "Orders", Toast.LENGTH_SHORT).show()
            }
            LL_cart -> {
                startActivity(Intent(this@ItemListActivity, CartActivity::class.java))
                darwerLayout.closeDrawer(GravityCompat.START)
            }
            LL_build -> {
                Toast.makeText(this, "LL_build", Toast.LENGTH_SHORT).show()
            }
            LL_location -> {
                Toast.makeText(this, "LL_location", Toast.LENGTH_SHORT).show()

            }
            LL_repair -> {
                alertbuild()
            }
            LL_faq -> {
                Toast.makeText(this, "LL_faq", Toast.LENGTH_SHORT).show()

            }
            LL_signOut -> {
                Toast.makeText(this, "LL_signOut", Toast.LENGTH_SHORT).show()

            }
        }
    }
    fun alertbuild() {
        val alert = AlertDialog.Builder(this)
        val layoutinf = LayoutInflater.from(this).inflate(R.layout.repair_dialog_layout, null)
        var tv = layoutinf.findViewById<TextView>(R.id.tv_repairguy_Alert)
        alert.setView(layoutinf)
        darwerLayout.closeDrawer(GravityCompat.START)
        tv.text =
            "يمكنك طلب فريق مختص لتركيب الحاسبات وصيانة الاعطال متنقل في محافظة بغداد فقط من خلال الاتصال على الرقم : " + "07822910287"
        spannapleClick(tv)
        alert.setPositiveButton("Ok") { dialogInterface: DialogInterface, i: Int ->
        }
        var alertDialog = alert.create()
        alertDialog.show()
    }
    fun spannapleClick(text: TextView) {
        var ss = SpannableString(text.text)

        var sp1 = object : ClickableSpan() {
            override fun onClick(p0: View) {
                startActivity(Intent(Intent.ACTION_DIAL,Uri.parse("tel:07822910287")))
            }
        }
        ss.setSpan(sp1, 105, 116, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        text.text = ss
        text.movementMethod = LinkMovementMethod.getInstance()

    }

}
