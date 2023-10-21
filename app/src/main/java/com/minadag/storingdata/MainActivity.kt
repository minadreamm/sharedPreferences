package com.minadag.storingdata

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.VerifiedInputEvent
import android.view.View
import com.minadag.storingdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var sharedPref : SharedPreferences
    var userAgePref : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //SheredPreferences / XML - Key-Value

        sharedPref = this.getSharedPreferences("com.minadag.storingdata", Context.MODE_PRIVATE)
        val userAgePref = sharedPref.getInt("age",-1)

        if (userAgePref == -1){
            binding.textView.text ="Your age:"
        } else {
            binding.textView.text = "Your age: ${userAgePref}"
        }

    }

    fun save (view: View){
        val myAge = binding.editText.text.toString().toIntOrNull()

        if (myAge != null) {
            binding.textView.text = "Your age: ${myAge}"
            // binding.textView.text = "Your age:" + myAge
            sharedPref.edit().putInt("age",myAge).apply()


        }
    }

    fun delete (view: View){

        userAgePref = sharedPref.getInt("age", -1)

        if (userAgePref != -1){
            sharedPref.edit().remove("age").apply()

            binding.textView.text = "Your age null"
        }

    }
}