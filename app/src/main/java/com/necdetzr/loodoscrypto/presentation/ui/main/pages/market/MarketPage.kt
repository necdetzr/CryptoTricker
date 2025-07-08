package com.necdetzr.loodoscrypto.presentation.ui.main.pages.market

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.necdetzr.loodoscrypto.presentation.ui.main.components.Section
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.necdetzr.loodoscrypto.R
import com.necdetzr.loodoscrypto.presentation.ui.components.LinearProgressBar
import com.necdetzr.loodoscrypto.presentation.ui.main.components.CoinCard
import com.necdetzr.loodoscrypto.presentation.ui.main.components.ErrorCard

@Composable
fun MarketPage(
    viewModel: MarketViewModel = hiltViewModel(),
    onNavigateToCoin:(String)->Unit,

){

    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { padding->
        Column(
            modifier = Modifier.padding(12.dp).verticalScroll(state = rememberScrollState())
        ) {
            Text(stringResource(R.string.crypto_marketplace), style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(40.dp))
            Section(stringResource(R.string.top_gainers), stringResource(R.string.last_24h))
            Spacer(Modifier.padding(4.dp))
            Box(modifier = Modifier.fillMaxWidth().height(260.dp)) {
                if (uiState.isTopGainersLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        LinearProgressBar()
                    }

                } else if (uiState.isError) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        ErrorCard()
                    }
                } else {
                    LazyColumn {
                        items(items = uiState.topCoins) { coin ->
                            Spacer(Modifier.height(20.dp))
                            CoinCard(coin,onNavigateToCoin)


                        }
                    }

                }
            }
            Spacer(Modifier.height(20.dp))
            Section(stringResource(R.string.top_losers),stringResource(R.string.last_24h))
            Spacer(Modifier.padding(4.dp))

            Box(modifier = Modifier.fillMaxWidth().height(260.dp)) {
                if (uiState.isTopLosersLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        LinearProgressBar()
                    }

                } else if (uiState.isError) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        ErrorCard()
                    }
                } else {
                    LazyColumn {
                        items(items = uiState.topLosers) { coin ->
                            Spacer(Modifier.height(20.dp))
                            CoinCard(coin,onNavigateToCoin)


                        }
                    }

                }


            }
            Spacer(Modifier.height(10.dp))
            Section(stringResource(R.string.top_market_values),stringResource(R.string.last_24h))
            Box(modifier = Modifier.fillMaxWidth().height(260.dp)) {
                if (uiState.isTopCoinsLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        LinearProgressBar()
                    }

                } else if (uiState.isError) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        ErrorCard()
                    }
                } else {
                    LazyColumn {
                        items(items = uiState.topCoins) { coin ->
                            Spacer(Modifier.height(20.dp))
                            CoinCard(coin,onNavigateToCoin)


                        }
                    }

                }


            }


        }
    }

    }
