package com.example.kotlincalculator

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private val textViewExpression: TextView by lazy {
        findViewById<TextView>(R.id.textViewExpression)
    }

    private val textViewResult: TextView by lazy {
        findViewById<TextView>(R.id.textViewResult)
    }

    private var isOperator = false
    private var hasOperator = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun buttonClicked(view: View) {
        when (view.id) {
            R.id.button0 -> numberButtonClicked("0")
            R.id.button1 -> numberButtonClicked("1")
            R.id.button2 -> numberButtonClicked("2")
            R.id.button3 -> numberButtonClicked("3")
            R.id.button4 -> numberButtonClicked("4")
            R.id.button5 -> numberButtonClicked("5")
            R.id.button6 -> numberButtonClicked("6")
            R.id.button7 -> numberButtonClicked("7")
            R.id.button8 -> numberButtonClicked("8")
            R.id.button9 -> numberButtonClicked("9")
            R.id.buttonPlus -> numberButtonClicked("+")
            R.id.buttonMinus -> numberButtonClicked("-")
            R.id.buttonMulti -> numberButtonClicked("*")
            R.id.buttonDivider -> numberButtonClicked("/")
            R.id.buttonModulo -> numberButtonClicked("%")
        }
    }

    private fun numberButtonClicked(number: String) {

        if (isOperator) {
            textViewExpression.append(" ")
        }
        isOperator = true

        val expression = textViewExpression.text.split(" ")

        if (expression.isNotEmpty() && expression.last().length > 15) {
            Toast.makeText(this, "Please enter 15 digits or less", Toast.LENGTH_SHORT).show()
            return
        } else if (expression.isEmpty() && number == "0") {
            return
        }

        textViewExpression.append(number)
        textViewResult.text = calculateExpression()
    }

    private fun operatorButtonClicked(operator: String) {

        if (textViewExpression.text.isEmpty()) {
            return
        }

        when {
            isOperator -> {
                val text = textViewExpression.text.toString()
                textViewExpression.text = text.dropLast(1) + operator
            }
            hasOperator -> {
                Toast.makeText(this, "You can use only one operator", Toast.LENGTH_SHORT).show()
                return
            }
            else -> {
                textViewExpression.append(" $operator")
            }
        }
        val ssb = SpannableStringBuilder(textViewExpression.text)
        ssb.setSpan(
                ForegroundColorSpan(getColor(R.color.green)),
                textViewExpression.text.length - 1,
                textViewExpression.text.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        textViewExpression.text = ssb

        isOperator = true
        hasOperator = true
    }

    private fun calculateExpression(): String {
        val expressions = textViewExpression.text.split(" ")
        if (!hasOperator.not() || expressions.size != 3) {
            return ""
        } else if (expressions[0].isNumber().not() || expressions[2].isNumber().not()) {
            return ""
        }

        val exp1 = expressions[0].toBigInteger()
        val exp2 = expressions[2].toBigInteger()
        val op = expressions[1]

        return when (op) {
            "+" -> (exp1 + exp2).toString()
            "-" -> (exp1 - exp2).toString()
            "*" -> (exp1 * exp2).toString()
            "/" -> (exp1 / exp2).toString()
            "%" -> (exp1 % exp2).toString()
            else -> ""
        }
    }

    private fun buttonClearClicked(view: View) {}
    private fun buttonHistoryClicked(view: View) {}
    private fun buttonResultClicked(view: View) {}
}

fun String.isNumber(): Boolean {
    return try {
        this.toBigInteger()
        true
    } catch (e: NumberFormatException) {
        false
    }
}