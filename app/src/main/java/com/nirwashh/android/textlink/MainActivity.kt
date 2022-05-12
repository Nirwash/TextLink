package com.nirwashh.android.textlink

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import com.nirwashh.android.textlink.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        spannableText()

    }

    private fun spannableText() {
        val fullText = getString(R.string.full_text)
        val confidential = getString(R.string.confidential)
        val startIndexOfConfidential = fullText.indexOf(confidential)
        val endIndexOfConfidential = startIndexOfConfidential + confidential.length
        val policy = getString(R.string.policy)
        val startIndexOfPolicy = fullText.indexOf(policy)
        val endIndexOfPolicy = startIndexOfPolicy + policy.length
        val spannableString = SpannableString(fullText)
        val goToConfidential = getString(R.string.go_to_condifential)
        val goToPolicy = getString(R.string.go_to_policy)
        val confidentialClickable = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = true
                ds.color = Color.parseColor("#FF0000")
            }

            override fun onClick(p0: View) {
                Toast.makeText(
                    this@MainActivity, goToConfidential,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        val policyClickable = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = true
                ds.color = Color.parseColor("#FF0000")
            }

            override fun onClick(p0: View) {
                Toast.makeText(
                    this@MainActivity, goToPolicy,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        spannableString.setSpan(
            confidentialClickable, startIndexOfConfidential,
            endIndexOfConfidential,
            Spanned.SPAN_EXCLUSIVE_INCLUSIVE
        )
        spannableString.setSpan(
            policyClickable, startIndexOfPolicy,
            endIndexOfPolicy,
            Spanned.SPAN_EXCLUSIVE_INCLUSIVE
        )

        b.tvAgreement.text = spannableString
        b.tvAgreement.movementMethod = LinkMovementMethod.getInstance()
        b.tvAgreement.highlightColor = Color.TRANSPARENT
    }

}