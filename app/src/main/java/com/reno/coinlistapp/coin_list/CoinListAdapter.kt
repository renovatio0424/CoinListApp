package com.reno.coinlistapp.coin_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reno.coinlistapp.data.model.Coin
import com.reno.coinlistapp.databinding.ItemCoinBinding

class CoinListAdapter(
    private val coinList: List<Coin>?,
    private val onClickItem: (id: String) -> Unit
) : RecyclerView.Adapter<CoinListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        coinList ?: return

        val item = coinList[position]
        holder.onBind(item, onClickItem)
    }

    override fun getItemCount(): Int = coinList?.size ?: 0

    inner class ViewHolder(binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root) {
        private val tvName: TextView = binding.tvName
        private val tvRank: TextView = binding.tvRank
        private val view = binding.root

        fun onBind(coin: Coin, onClickItem: (id: String) -> Unit) {
            tvName.text = coin.name
            tvRank.text = coin.rank.toString()
            view.setOnClickListener {
                onClickItem(coin.id)
            }
        }
    }
}