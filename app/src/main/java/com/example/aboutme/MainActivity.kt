package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //declare the binding object which is the glue between activity and layout views
    private lateinit var binding: ActivityMainBinding

    //instantiate the data for the views: create an instance of the data class
    // and set the name
    private val myName: MyName = MyName("Bruno Jullien")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        //Bindings: magic that connect the layout to activity!
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //set the value of the nyName variable that is declared and used in the layout file
        binding.myName = myName

        //findViewById<Button>(R.id.done_button).setOnClickListener {
        //    addNickName(it)
        //}
        //We can now access the DONE button through the Binding object
        binding.doneButton.setOnClickListener {
            addNickName(it)
        }
    }

    /**
     * @view: our done button, just clicked
     */
    private fun addNickName(view: View) {
//        val editText = findViewById<EditText>(R.id.nickname_edit)
//        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)
//        nicknameTextView.text = editText.text
//        editText.visibility = View.GONE //        view.visibility = View.GONE
//        nicknameTextView.visibility = View.VISIBLE
        binding.apply {
            //nicknameText.text = binding.nicknameEdit.text
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }


        //Hide the keyboard, after done is clicked
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}