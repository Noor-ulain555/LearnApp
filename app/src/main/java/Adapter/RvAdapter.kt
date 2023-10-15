package com.example.learnapp

import Data.DataEntity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.learnapp.databinding.CallSampleBinding

class RvAdapter(private val context: Context, private var callList: List<DataEntity>) :
    RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    class ViewHolder(val binding: CallSampleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = CallSampleBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return callList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = callList[position]

            holder.binding.name.text = item.name
            holder.binding.phone.text = item.phone
            holder.binding.email.text = item.email

            holder.binding.phone.setOnClickListener {
                val phoneNumber = item.phone
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                context.startActivity(intent)
            }
        }
//    class Holder(binding : CallSampleBinding):RecyclerView.ViewHolder(binding.root){
//
//    }

//
//    fun setData(newCallList: List<DataEntity>) {
//
//        this.callList.value = newCallList
//        notifyDataSetChanged()
//    }
}
