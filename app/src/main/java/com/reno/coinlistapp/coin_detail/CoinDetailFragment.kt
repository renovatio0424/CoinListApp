package com.reno.coinlistapp.coin_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.reno.coinlistapp.databinding.FragmentCoinDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {

    private val coinDetailModel: CoinDetailViewModel by viewModels()
    private lateinit var coinId: String
    private lateinit var binding: FragmentCoinDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            coinId = it[ARG_COIN_ID] as String
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        binding.viewModel = coinDetailModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coinDetailModel.loadCoinDetail(coinId)
    }

    companion object {
        const val ARG_COIN_ID = "ARG_COIN_ID"

        fun newInstance(id: String) = CoinDetailFragment().apply {
            arguments = bundleOf(Pair(ARG_COIN_ID, id))
        }
    }
}