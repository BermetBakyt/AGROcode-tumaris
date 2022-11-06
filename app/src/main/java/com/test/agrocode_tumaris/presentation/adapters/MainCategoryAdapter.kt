package com.test.agrocode_tumaris.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.test.agrocode_tumaris.databinding.CategoryMainItemBinding

class MainCategoryAdapter(
    private val list: ArrayList<String>,
    val onClickCategory: CategoryOnClickInterface
): RecyclerView.Adapter<MainCategoryAdapter.ViewHolder>() {

    class ViewHolder(val binding : CategoryMainItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategoryMainItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.btnItemCategory.text = list[position]

        holder.binding.btnItemCategory.setOnClickListener {
            onClickCategory.onClickCategory(holder.binding.btnItemCategory)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface CategoryOnClickInterface{
    fun  onClickCategory(button: Button)
}