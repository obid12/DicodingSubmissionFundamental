package com.example.submissionandroidfundamental

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.submissionandroidfundamental.databinding.ItemRowUserBinding
import java.util.*

class ListUserAdapter(val onClick: OnClick) :
    RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    private var unfilteredlist = mutableListOf<User>()
    fun modifyList(list: MutableList<User>) {
        unfilteredlist = list
        submitData(list)
    }

    fun filter(query: CharSequence?) {
        val list = mutableListOf<User>()

        if (!query.isNullOrEmpty()) {
            list.addAll(unfilteredlist.filter {

                it.name.lowercase(Locale.getDefault())
                    .contains(query.toString().lowercase(Locale.getDefault())) ||
                        it.name.uppercase(Locale.getDefault())
                            .contains(query.toString().uppercase(Locale.getDefault()))
            })


        } else {
            list.addAll(unfilteredlist)
        }

        submitData(list)
    }

    private val diffCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.hashCode() == newItem.hashCode()


        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.hashCode() == newItem.hashCode()


    }

    private val differ = AsyncListDiffer(this, diffCallback)
    fun submitData(value: MutableList<User>?) = differ.submitList(value)

    class ListViewHolder(var binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: User) {
            binding.entity = data
            binding.executePendingBindings()
        }
    }

    class OnClick(val click: (entity: User?) -> Unit) {
        fun onClick(entity: User?) = click(entity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemRowUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = differ.currentList[position] as User
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onClick.onClick(data)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}