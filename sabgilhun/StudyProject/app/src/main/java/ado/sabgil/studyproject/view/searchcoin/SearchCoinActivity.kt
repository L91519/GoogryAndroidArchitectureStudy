package ado.sabgil.studyproject.view.searchcoin

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.adapter.TickerAdapter
import ado.sabgil.studyproject.databinding.ActivitySearchCoinBinding
import ado.sabgil.studyproject.view.base.BaseActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.viewModel

class SearchCoinActivity :
    BaseActivity<ActivitySearchCoinBinding>(R.layout.activity_search_coin) {

    private val searchCoinViewModel: SearchCoinViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressBar = binding.pgCoinList

        bind {
            vm = searchCoinViewModel
            rvTickerList.adapter = TickerAdapter()
        }

        registerEvent()
    }

    override fun onResume() {
        super.onResume()
        searchCoinViewModel.subscribeCoinData()
    }

    override fun onPause() {
        searchCoinViewModel.unSubscribeCoinData()
        super.onPause()
    }

    private fun registerEvent() {
        searchCoinViewModel.run {
            showToastEvent.observe(this@SearchCoinActivity, Observer(::showToastMessage))
        }
    }
}