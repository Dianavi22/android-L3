@file:Suppress("DEPRECATION")

package com.example.scalim.details

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.scalim.Food
import com.example.scalim.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val food = intent.getParcelableExtra<Food>("food")
        if (food !=null) {
            Glide.with(binding.imageProductView.context).load(Uri.parse(food.imageUrl)).into(binding.imageProductView)
            binding.nameProductTextView.text = food.genericName
            binding.codeProductTextView.text = food.code
            binding.dateScanProductTextView.text = food.date
        }else {
            Toast.makeText(this@DetailsActivity, "Error", Toast.LENGTH_LONG).show()
        }
    }
}