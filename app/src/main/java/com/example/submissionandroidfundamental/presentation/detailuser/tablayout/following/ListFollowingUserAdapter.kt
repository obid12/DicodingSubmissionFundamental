package com.example.submissionandroidfundamental.presentation.detailuser.tablayout.following

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.submissionandroidfundamental.R
import com.example.submissionandroidfundamental.databinding.ItemSearchUserBinding
import com.example.submissionandroidfundamental.domain.entity.UserSearchEntity

class ListFollowingUserAdapter() :
    RecyclerView.Adapter<ListFollowingUserAdapter.ListViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<UserSearchEntity>() {
        override fun areItemsTheSame(
            oldItem: UserSearchEntity,
            newItem: UserSearchEntity
        ): Boolean =
            oldItem.hashCode() == newItem.hashCode()


        override fun areContentsTheSame(
            oldItem: UserSearchEntity,
            newItem: UserSearchEntity
        ): Boolean =
            oldItem.hashCode() == newItem.hashCode()


    }

    private val differ = AsyncListDiffer(this, diffCallback)
    fun submitData(value: MutableList<UserSearchEntity>?) = differ.submitList(value)

    class ListViewHolder(private var binding: ItemSearchUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun context(): Context = itemView.context
        fun bind(data: UserSearchEntity, adapterListener: ClickListener) {
            var click = false
            binding.data = data
            binding.executePendingBindings()
            binding.container.run {
                background = ContextCompat.getDrawable(
                    context(),
                    if (click == true) R.drawable.bg_shimmer
                    else R.drawable.bg_white
                )

                setOnClickListener {
                    click = true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemSearchUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = differ.currentList[position] as UserSearchEntity
        holder.bind(data)
    }

    override fun getItemCount(): Int = differ.currentList.size

    interface ClickListener {
        fun onClickItemSelectedListener(position: Int, data: UserSearchEntity)
    }
}