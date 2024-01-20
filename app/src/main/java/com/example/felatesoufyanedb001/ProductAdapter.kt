package com.example.felatesoufyanedb001

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(
    private var proList: MutableList<Product>,
    private val backButtonClickListener: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProViewHolder>() {

    fun submitList(newList: List<Product>) {
        proList = newList.toMutableList()
        notifyDataSetChanged()
    }

    inner class ProViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView? = itemView.findViewById(R.id.tvname)
        val priceTextView: TextView? = itemView.findViewById(R.id.tvprice)
        val imageImageView: ImageView? = itemView.findViewById(R.id.tvimage)
        val backButton: Button? = itemView.findViewById(R.id.back)

        init {
            backButton?.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    backButtonClickListener(proList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_view, parent, false)
        return ProViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return proList.size
    }

    override fun onBindViewHolder(holder: ProViewHolder, position: Int) {
        val currentItem = proList[position]
        holder.nameTextView?.text = currentItem.name
        holder.priceTextView?.text = currentItem.price.toString()

        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .into(holder.imageImageView!!)
    }
}
