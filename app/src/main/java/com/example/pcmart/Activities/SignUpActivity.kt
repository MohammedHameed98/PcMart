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
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity() , TextWatcher {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        spannapleString(tv_SignUpLogo,this)
        et_SignUpEmail.addTextChangedListener(this)
        et_SignUpPassword.addTextChangedListener(this)

    }
    fun SignUpOnClick (view : View) {
        when (view)
        {
         bt_SignUp -> {
             if (et_SignUpPassword.text.length < 6){
                 et_SignUpPassword.error = "Password must be 6 characters or more"
                 et_SignUpPassword.requestFocus()
                 return
             }
             if (!Patterns.EMAIL_ADDRESS.matcher(et_SignUpEmail.text).matches()){
                 et_SignUpEmail.error = "Invalid Email"
                 et_SignUpEmail.requestFocus()
                 return
             }
             firebaseSignUp()
         }
            mg_ShowPasswordSignUp ->
            {
                if(mg_ShowPasswordSignUp.isChecked) {
                    et_SignUpPassword.transformationMethod = HideReturnsTransformationMethod()
                }
                else et_SignUpPassword.transformationMethod = PasswordTransformationMethod()
            }
            tv_SignIn -> {
                val intent = Intent (this, SignInActivity::class.java)
                startActivity(intent)

            }
            signUpConstraint -> {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken,0)
            }
        }
    }
    fun firebaseSignUp () {
        val intent = Intent (this, SignUpCompletion::class.java)
        startActivity(intent)

    }



    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    bt_SignUp.isEnabled = et_SignUpEmail.text.trim().isNotEmpty() && et_SignUpPassword.text.trim().isNotEmpty()
    }


}
