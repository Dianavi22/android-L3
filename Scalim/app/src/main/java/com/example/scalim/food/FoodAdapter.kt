package com.example.scalim.food

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scalim.Food
import com.example.scalim.databinding.MainItemListBinding

class FoodAdapter(private var foods: List<Food>) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    class ViewHolder(val binding: MainItemListBinding) : RecyclerView.ViewHolder(binding.root)

    var onItemClick : ((Food) -> Unit)? = null
    var onItemDelete : ((Food) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MainItemListBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foods[position]
        with(holder) {
            binding.nameTextView.text = food.genericName
            binding.dateTextView.text = food.date
            Glide.with(binding.foodImageView.context).load(Uri.parse(food.imageUrl)).into(binding.foodImageView)
            holder.itemView.setOnClickListener{
                onItemClick?.invoke(food)
            }
            binding.deleteProductButton.setOnClickListener {
                onItemDelete?.invoke(food)
            }

        }

    }
    override fun getItemCount(): Int = foods.size
    fun updateDataSet(foods: List<Food>) {
        this.foods = foods
        notifyDataSetChanged()
    }
}