package com.android.nearlabs.Utility

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import com.google.android.material.textview.MaterialTextView
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import java.util.regex.Pattern


object Utility {
//by clicking continue you must agree to near labs Terms & Conditions and Privacu Policy
     fun getLoginTermAndConditionTextView(view: MaterialTextView, context:Context) {
        val spanTxt = SpannableStringBuilder(
            "by clicking continue you must agree to near labs "
        )
    spanTxt.setSpan(ForegroundColorSpan(Color.parseColor("#565656")), 0, spanTxt.length, 0)
        spanTxt.append("Terms & Conditions")
        spanTxt.setSpan(object : ClickableSpan() {
            override fun onClick(p0: View) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
                context.startActivity(browserIntent)
            }
        }, spanTxt.length - "Terms & Conditions".length, spanTxt.length, 0)
        spanTxt.setSpan(ForegroundColorSpan(Color.parseColor("#49AEFF")), 49, spanTxt.length, 0)
        spanTxt.append(" and")
        spanTxt.setSpan(ForegroundColorSpan(Color.parseColor("#565656")), 67, spanTxt.length, 0)

        spanTxt.append(" Privacy Policy")
        spanTxt.setSpan(object : ClickableSpan() {
            override fun onClick(p0: View) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
                context.startActivity(browserIntent)
            }
        }, spanTxt.length - " Privacy Policy".length, spanTxt.length, 0)
    spanTxt.setSpan(ForegroundColorSpan(Color.parseColor("#49AEFF")), spanTxt.length - " Privacy Policy".length, spanTxt.length, 0)
        view.movementMethod = LinkMovementMethod.getInstance()
        view.setText(spanTxt, TextView.BufferType.SPANNABLE)
    }

    fun isValidEmail(email:String) : Boolean {
        val emailAddressPattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+" )
            return emailAddressPattern.matcher(email).matches()
    }

    fun isValidPhone(phone:String) : Boolean {
        return  android.util.Patterns.PHONE.matcher(phone).matches()
    }
}