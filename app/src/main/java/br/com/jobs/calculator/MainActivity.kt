package br.com.jobs.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    var pendingValue: Double = 0.0
    var pendingOperation: String = ""
    var isPendingOperation = false
    var isInitialState = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btEqual.setOnClickListener {

            try {
                var result = calc()
                var decimal = result.toString().substring(result.toString().indexOf(".") + 1).toInt()
                if(decimal == 0){
                    tvDisplay.text = result.toInt().toString()
                } else {
                    tvDisplay.text = result.toString()
                }
            } catch (e: ArithmeticException){
                tvDisplay.text = getString(R.string.app_error)
            }
        }

        btClear.setOnClickListener {
            //Toast.makeText(this, getString(R.string.app_name), Toast.LENGTH_LONG).show()
            tvDisplay.text = getString(R.string.zero_number_input_text)
            pendingValue = 0.0
            pendingOperation = ""
            isPendingOperation = false
            isInitialState = true
        }

        btAddition.setOnClickListener {
            setOperations(btAddition.text.toString())
        }

        btSubtraction.setOnClickListener {
            setOperations(btSubtraction.text.toString())
        }

        btMultiplication.setOnClickListener {
            setOperations(btMultiplication.text.toString())
        }

        btDivision.setOnClickListener {
            setOperations(btDivision.text.toString())
        }

        btZero.setOnClickListener {
            setValues(btZero.text.toString())
        }

        btOne.setOnClickListener {
            setValues(btOne.text.toString())
        }

        btTwo.setOnClickListener {
            setValues(btTwo.text.toString())
        }

        btThree.setOnClickListener {
            setValues(btThree.text.toString())
        }

        btFour.setOnClickListener {
            setValues(btFour.text.toString())
        }

        btFive.setOnClickListener {
            setValues(btFive.text.toString())
        }

        btSix.setOnClickListener {
            setValues(btSix.text.toString())
        }

        btSeven.setOnClickListener {
            setValues(btSeven.text.toString())
        }

        btEight.setOnClickListener {
            setValues(btEight.text.toString())
        }

        btNine.setOnClickListener {
            setValues(btNine.text.toString())
        }
    }

    private fun calc(): Double {
        var result: Double = 0.0
        //if(isPendingOperation) {
            if(pendingOperation.equals("+")){
                result = pendingValue + tvDisplay.text.toString().toDouble()
            } else if (pendingOperation.equals("-")) {
                result = pendingValue - tvDisplay.text.toString().toDouble()
            } else if (pendingOperation.equals("/")) {
                if(pendingValue == 0.0 && tvDisplay.text.toString().toDouble() == 0.0){
                    throw ArithmeticException()
                }
                result = pendingValue / tvDisplay.text.toString().toDouble()
            } else {
                result = pendingValue * tvDisplay.text.toString().toDouble()
            }
        //}
        if(isPendingOperation){
            pendingValue = tvDisplay.text.toString().toDouble()
        }
        isPendingOperation = false
        isInitialState = true
        return result
    }

    private fun setValues(componentValue: String){
        if(isInitialState){
            tvDisplay.text = componentValue
            if(!componentValue.equals("0")){
                isInitialState = false
            }
        } else {
            tvDisplay.text = tvDisplay.text.toString().plus(componentValue)
        }
    }

    private fun setOperations(operation: String){
        if(!isPendingOperation){
            pendingValue = tvDisplay.text.toString().toDouble()
        }
        pendingOperation = operation
        isPendingOperation = true
        isInitialState = true
    }
}