package com.example.submissionandroidfundamental.presentation.listuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.submissionandroidfundamental.databinding.ItemSearchUserBinding
import com.example.submissionandroidfundamental.domain.entity.UserSearchEntity

class ListSearchUserAdapter(private val onClick: OnClick) :
    RecyclerView.Adapter<ListSearchUserAdapter.ListViewHolder>() {

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
        fun bind(data: UserSearchEntity) {
            binding.data = data
            binding.executePendingBindings()
        }
    }

    class OnClick(val click: (entity: UserSearchEntity?) -> Unit) {
        fun onClick(entity: UserSearchEntity?) = click(entity)
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
        holder.itemView.setOnClickListener {
            onClick.onClick(data)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}