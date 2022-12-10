package com.example.submissionandroidfundamental.utils.bindingadapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ImageRequest
import com.example.submissionandroidfundamental.R
import com.example.submissionandroidfundamental.domain.entity.UserSearchEntity
import com.example.submissionandroidfundamental.presentation.listuser.ListSearchUserAdapter

@BindingAdapter("listSearchUser")
fun bindRecyclerViewListSearchUser(
    recycler: RecyclerView,
    data: MutableList<UserSearchEntity>?
) {
    val adapter = recycler.adapter as ListSearchUserAdapter
    adapter.submitData(data)
}

@BindingAdapter("imageUser")
fun imageUser(
    iv: ImageView,
    image: Int
) {
    iv.setImageResource(image)
}

@BindingAdapter("imageUserURL")
fun getImageUserUrl(iv: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imageUrl = ImageRequest.Builder(iv.context)
            .data("${it.toUri()}")
            .allowHardware(false)
            .build()
        iv.load("${imageUrl.data}") {
            placeholder(R.drawable.loading_animation)
            this.error(R.drawable.ic_broken_image)
        }
    }
}