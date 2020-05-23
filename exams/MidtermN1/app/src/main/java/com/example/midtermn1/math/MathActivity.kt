package com.example.midtermn1.math

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.midtermn1.R
import kotlinx.android.synthetic.main.activity_math.*


class MathActivity : AppCompatActivity(), View.OnClickListener {


    private var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math)
        init()
    }


    private fun init() {
        triviaRadioButton.setOnClickListener(this)
        mathRadioButton.setOnClickListener(this)
        dateRadioButton.setOnClickListener(this)
        yearRadioButton.setOnClickListener(this)

        predictButton.setOnClickListener {
            if (type.isNotEmpty()) {
                if (type == "date") {
                    val month = monthEditText.text.toString()
                    val day = dayEditText.text.toString()

                    if (month.isNotEmpty() && day.isNotEmpty()) {
                        swipeRefreshLayout.isRefreshing = true
                        makeButtonInactive()

                        MathHttpRequest.getRequest(month, day, type, object : CustomCallback {
                            override fun onSuccess(body: String) {

                                activateDateProperties()

                                dateRadioButton.isChecked = false

                                responseTextView.text = body

                                swipeRefreshLayout.isRefreshing = false
                                makeButtonActive()
                            }

                            override fun onFailed(error: String) {
                                toast(error)
                                swipeRefreshLayout.isRefreshing = false
                                makeButtonActive()
                            }
                        })
                    } else {
                        toast("Please fill in all the fields")
                    }
                } else {
                    val number = enterNumberEditText.text.toString()

                    if (number.isNotEmpty()) {
                        swipeRefreshLayout.isRefreshing = true
                        makeButtonInactive()

                        MathHttpRequest.getRequest(number, type, object : CustomCallback {
                            override fun onSuccess(body: String) {
                                responseTextView.text = body

                                swipeRefreshLayout.isRefreshing = false

                                makeButtonActive()
                            }

                            override fun onFailed(error: String) {
                                toast(error)

                                swipeRefreshLayout.isRefreshing = false

                                makeButtonActive()
                            }
                        })
                    } else {
                        toast("Please fill in all the fields")
                    }
                }
            } else {
                toast("Please select one of the radio buttons")
            }
        }
    }


    override fun onClick(v: View?) {

        if (v!!.id == R.id.dateRadioButton) {
            type = "date"

            deactivateDateProperties()
        } else {
            when (v.id) {
                R.id.triviaRadioButton -> type = "trivia"
                R.id.mathRadioButton -> type = "math"
                R.id.yearRadioButton -> type = "year"
            }
            activateDateProperties()
        }
    }


    private fun makeButtonActive() {
        predictButton.isClickable = true
        predictButton.text = "Check"
        predictButton.setBackgroundResource(R.drawable.main_button_background)
    }

    private fun makeButtonInactive() {
        predictButton.isClickable = false
        predictButton.text = "Waiting"
        predictButton.setBackgroundResource(R.drawable.inactive_button_background)
    }

    private fun activateDateProperties(){
        numberTextView.visibility = View.VISIBLE
        enterNumberEditText.visibility = View.VISIBLE
        monthTextView.visibility = View.GONE
        monthEditText.visibility = View.GONE
        dayTextView.visibility = View.GONE
        dayEditText.visibility = View.GONE
    }

    private fun deactivateDateProperties() {
        numberTextView.visibility = View.GONE
        enterNumberEditText.visibility = View.GONE
        monthTextView.visibility = View.VISIBLE
        monthEditText.visibility = View.VISIBLE
        dayTextView.visibility = View.VISIBLE
        dayEditText.visibility = View.VISIBLE
    }

    private fun toast(message: String) {
        Toast.makeText(
            this@MathActivity,
            message,
            Toast.LENGTH_LONG
        ).show()
    }


}