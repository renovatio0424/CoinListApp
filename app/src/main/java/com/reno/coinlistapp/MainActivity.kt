package com.reno.coinlistapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.reno.coinlistapp.coin_list.CoinListFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 * deep link example
 * http://www.coin-list-app.com/coinList/{coin-id}
 * */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showCoinListFragment(intent)
    }

    private fun showCoinListFragment(intent: Intent?) {
        val id: String? = intent?.let {
            if (it.action == Intent.ACTION_VIEW) {
                return@let intent.data?.lastPathSegment
            }
            null
        }

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.containerFragment, CoinListFragment.newInstance(id))
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        showCoinListFragment(intent)
    }
}