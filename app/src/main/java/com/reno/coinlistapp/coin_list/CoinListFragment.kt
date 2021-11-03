package com.reno.coinlistapp.coin_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.reno.coinlistapp.R
import com.reno.coinlistapp.coin_detail.CoinDetailFragment
import com.reno.coinlistapp.databinding.FragmentCoinListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment : Fragment() {
    private val coinListModel: CoinListViewModel by viewModels()
    private lateinit var binding: FragmentCoinListBinding
    private var coinId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCoinListBinding.inflate(inflater, container, false)
        binding.viewModel = coinListModel
        binding.lifecycleOwner = this
        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.containsKey(ARG_COIN_ID) == true) {
            coinId = requireArguments().getString(ARG_COIN_ID) ?: return
            moveToCoinDetailFragment(coinId!!)
        }
    }

    private fun initRecyclerView() {
        binding.rvCoinList.layoutManager = LinearLayoutManager(context)

        coinListModel.coinList.observe(viewLifecycleOwner) { coinList ->
            binding.rvCoinList.adapter = CoinListAdapter(coinList) { coinId ->
                moveToCoinDetailFragment(coinId)
            }
        }
    }

    private fun moveToCoinDetailFragment(coinId: String) {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.containerFragment, CoinDetailFragment.newInstance(coinId))
            addToBackStack(null)
        }
        arguments?.clear()
    }

    companion object {
        private const val ARG_COIN_ID = "ARG_COIN_ID_TO_COIN_LIST"

        fun newInstance(id: String?): CoinListFragment {
            return CoinListFragment().apply {
                arguments = bundleOf(Pair(ARG_COIN_ID, id))
            }
        }
    }
}