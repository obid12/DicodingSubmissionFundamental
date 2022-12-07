package com.example.submissionandroidfundamental.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.submissionandroidfundamental.ListUserAdapter
import com.example.submissionandroidfundamental.User

@BindingAdapter("listStatus")
fun bindRecyclerViewListHero(
    recycler: RecyclerView,
    data: MutableList<User>?
) {
    val adapter = recycler.adapter as ListUserAdapter
    if (data != null) {
        adapter.modifyList(data)
    }
}

@BindingAdapter("imageUser")
fun imageUser(
    iv: ImageView,
    image: Int
) {
    iv.setImageResource(image)
}