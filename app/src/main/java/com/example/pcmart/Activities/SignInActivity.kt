package com.example.pcmart.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.pcmart.Utilites.BaseActivity
import com.example.pcmart.R
import kotlinx.android.synthetic.main.sign_in_activity.*

class SignInActivity : BaseActivity(),TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)
        spannapleString(tv_SignInLogo,this)
        et_SignInPassword.addTextChangedListener(this)
        et_SingInEmail.addTextChangedListener(this)
    }



    fun SignInOnClick (view: View) {
        when (view){
            bt_SignIn -> {
                if (et_SignInPassword.text.length < 6){
                    et_SignInPassword.error = "Password must be 6 characters or more"
                    et_SignInPassword.requestFocus()
                    return
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(et_SingInEmail.text).matches()){
                    et_SingInEmail.error = "Invalid Email"
                    et_SingInEmail.requestFocus()
                    return
                }
                firebaseSignIn()
            }
            mg_Showpassword -> {
            if (mg_Showpassword.isChecked){
                et_SignInPassword.transformationMethod = HideReturnsTransformationMethod()}
            else {
                et_SignInPassword.transformationMethod = PasswordTransformationMethod.getInstance()}
            }
            tv_SignUp ->
            {
                val intent = Intent (this, SignUpActivity::class.java)
                startActivity(intent)
            }
            signInConstrain -> {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken,0)
            }
            }
        }

fun firebaseSignIn () {
    var intent = Intent (this, ItemListActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)

}
    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        bt_SignIn.isEnabled = et_SingInEmail.text.trim().isNotEmpty() && et_SignInPassword.text.trim().isNotEmpty()
    }

}
