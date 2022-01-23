package com.android.nearlabs.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.nearlabs.R
import com.android.nearlabs.databinding.UserListLayoutBinding
import com.android.nearlabs.model.User
import com.android.nearlabs.textdrawable.TextDrawable
import java.lang.StringBuilder
import javax.inject.Inject


class UserGiftAdapter @Inject constructor() : RecyclerView.Adapter<UserGiftAdapter.ViewHolder>() {
    var userList:List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            UserListLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.itemView.setOnClickListener {
            userList[position].isSelected = true
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int  = userList.size


    inner class ViewHolder(private val binding: UserListLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user:User) {
            binding.apply {
                user.also { (name,email, isSelected) ->
                    val initials = StringBuilder()
                    for (s in name.split(" ")) {
                        initials.append(s[0])
                    }
                    userImage.setImageDrawable(TextDrawable.builder().beginConfig().textColor(Color.parseColor("#808080")).fontSize(30).endConfig().buildRound(initials.toString(), Color.parseColor("#EBEDF0")))
                    nameTextView.text = name
                    emailTextView.text = email
                    if (isSelected){
                        checkImageView.setImageResource(R.drawable.ic_check_circle)
                    }else{
                        checkImageView.setImageResource(R.drawable.ic_circle)
                    }
                }
            }
        }
    }

}