package com.gulzari.bmical

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private lateinit var maleButton: RadioButton
    private lateinit var femaleButton: RadioButton
    private lateinit var ageEditText: EditText
    private lateinit var feetEditText: EditText
    private lateinit var inchesEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setTheme(R.style.Theme_BMI_Calculator_Kotlin)
        findViews()
        setupButtonClickListener()
    }
    private fun findViews() {
        resultText = findViewById<TextView>(R.id.text_view_result)
        maleButton = findViewById<RadioButton>(R.id.radio_button_male)
        femaleButton = findViewById<RadioButton>(R.id.radio_button_female)
        ageEditText = findViewById<EditText>(R.id.edit_text_age)
        feetEditText = findViewById<EditText>(R.id.edit_text_feet)
        inchesEditText = findViewById<EditText>(R.id.edit_text_inches)
        weightEditText = findViewById<EditText>(R.id.edit_text_weigth)
        calculateButton = findViewById<Button>(R.id.button_calculate)
    }
    private fun setupButtonClickListener() {
        calculateButton.setOnClickListener {
            val bmiResult = calculateBMI()
            val ageText = ageEditText.text.toString()
            val age = Integer.parseInt(ageText)

            if(age >= 18) {
                displayResult(bmiResult)
            } else {
                displayGuidance(bmiResult)
            }
        }
    }

    private fun calculateBMI(): Double {
        val feetText = feetEditText.text.toString()
        val inchesText = inchesEditText.text.toString()
        val weightText = weightEditText.text.toString()

        val feet = Integer.parseInt(feetText)
        val inches = Integer.parseInt(inchesText)
        val weight = Integer.parseInt(weightText)

        val totalInches = (feet * 12) + inches
        val heightInMeters = totalInches * 0.0254
        return weight / (heightInMeters * heightInMeters)
    }
    private fun displayResult (bmi: Double) {
        val myDecimalFormatter = DecimalFormat("0.00")
        val bmiTextResult = myDecimalFormatter.format(bmi)

        var fullResultString: String = if(bmi < 18.5) {
            "$bmiTextResult - You are underweight"
        } else if(bmi > 25) {
            "$bmiTextResult - You are overweight"
        }else {
            "$bmiTextResult - You are a healthy weight"
        }
        resultText.text = fullResultString
    }
    private fun displayGuidance(bmi: Double) {
        val myDecimalFormatter = DecimalFormat("0.00")
        val bmiTextResult = myDecimalFormatter.format(bmi)


        var fullResultString: String = if(maleButton.isChecked){
            "$bmiTextResult - As you are under 18, please with your doctor for the healthy range for boys"
        } else if(femaleButton.isChecked){
            "$bmiTextResult - As you are under 18, please with your doctor for the healthy range for girls"
        } else {
            "$bmiTextResult - As you are under18, please with your doctor for the healthy range"
        }
        resultText.text = fullResultString
    }
}
