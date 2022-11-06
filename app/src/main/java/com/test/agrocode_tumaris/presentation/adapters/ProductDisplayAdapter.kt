package com.test.agrocode_tumaris.presentation.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.agrocode_tumaris.databinding.ProductDisplayMainItemBinding
import com.test.agrocode_tumaris.models.ProductDisplayModel

class ProductDisplayAdapter(
    private val context: Context,
    private val list: List<ProductDisplayModel>,
    private val productClickInterface: ProductOnClickInterface,
    private val likeClickInterface: LikeOnClickInterface,

    ) : RecyclerView.Adapter<ProductDisplayAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ProductDisplayMainItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProductDisplayMainItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.binding.tvNameShoeDisplayItem.text = "${currentItem.farmer_name} ${currentItem.name}"
        holder.binding.tvPriceShoeDisplayItem.text = "₹${currentItem.price}"

        Glide
            .with(context)
            .load(currentItem.imageUrl)
            .into(holder.binding.ivShoeDisplayItem)


        holder.itemView.setOnClickListener {
            productClickInterface.onClickProduct(list[position])
        }

        holder.binding.btnLike.setOnClickListener {
            if(holder.binding.btnLike.isChecked){
                holder.binding.btnLike.backgroundTintList = ColorStateList.valueOf(Color.RED)
                likeClickInterface.onClickLike(currentItem)
            }
            else{
                holder.binding.btnLike.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface ProductOnClickInterface {
    fun onClickProduct(item: ProductDisplayModel)
}

interface LikeOnClickInterface{
    fun onClickLike(item : ProductDisplayModel)
}