package com.menadev.foodappapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_rest.view.*

class RestrauntsAdapter( val context: Context, val restaurants: List<YelpRestaurant>):RecyclerView.Adapter<RestrauntsAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant: YelpRestaurant,context: Context) {
            itemView.tv_Name.text = restaurant.name
            itemView.ratingBar.rating = restaurant.rating.toFloat()
            itemView.tvNumReview.text = "${restaurant.numReviews} Reviews"
            itemView.tvAddress.text = restaurant.location.address
            itemView.tvCategory.text = restaurant.categories[0].title
            itemView.tvDistance.text = restaurant.displayDistance()
            itemView.tvPrice.text = restaurant.price
            Glide.with(context).load(restaurant.imageUrl).apply(RequestOptions().transforms(
                CenterCrop(),RoundedCorners(20)
            )).into(itemView.imageView)


        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rest,parent,false))
    }


    override fun onBindViewHolder(holder: RestrauntsAdapter.MyViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant,context)
    }

    override fun getItemCount()= restaurants.size

}
