package com.example.pcmart.Activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.pcmart.Utilites.BaseActivity
import com.example.pcmart.R
import kotlinx.android.synthetic.main.activity_sign_up_completion.*
import java.util.regex.Pattern

class SignUpCompletion : BaseActivity(), TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_completion)
        spannapleString(tv_SignUp2Logo, this)
        spinnerfun()
        et_FullName.addTextChangedListener(this)
        bt_floating.setOnClickListener {
            checkData()
        }
    }

    fun checkData() {
        if (et_FullName.text.isEmpty() && et_Number.text.isEmpty()) {
            Toast.makeText(this, "Please enter your name and number", Toast.LENGTH_SHORT).show()
            et_FullName.error = "Field is empty"
            et_Number.error = "Field is empty"
            return
        }
        if (et_FullName.text.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            et_FullName.error = "Field is empty"
            et_FullName.requestFocus()
            return
        }
        if (et_Number.text.isEmpty()) {
            Toast.makeText(this, "Please enter number", Toast.LENGTH_SHORT).show()
            et_Number.error = "Field is empty"
            et_Number.requestFocus()
            return
        }
        if (mySpinner.selectedItem == captials[0]) {
            Toast.makeText(this, "Please select an address", Toast.LENGTH_SHORT).show()
            return
        }
        startActivity(Intent(this, ItemListActivity::class.java))
    }

    fun constraintOnClick(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun spinnerfun() {
        val adapter = object : ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, captials) {
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                if (position == 0)
                    tv.setTextColor(Color.GRAY)
                else
                    tv.setTextColor(Color.BLACK)
                return view
            }

            override fun isEnabled(position: Int): Boolean {
                return (position != 0)

            }
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mySpinner.adapter = adapter
    }

    override fun afterTextChanged(p0: Editable?) {
        if (Pattern.compile("[0-9]").matcher(p0.toString()).find() || Pattern.compile("[٠-٩]").matcher(p0.toString()).find()) {
            p0?.replace(p0.toString().length - 1, p0.toString().length, "")
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }
}
