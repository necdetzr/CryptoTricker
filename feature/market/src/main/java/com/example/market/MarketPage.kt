package com.example.market

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.market.components.CoinCard
import com.example.ui.component.CryptoAppBar
import com.example.ui.component.ErrorCard
import com.example.ui.component.LinearProgressBar
import com.example.ui.component.Section
import com.necdetzr.designsystem.R
import com.necdetzr.home.component.domain.data.Coin

@Composable
fun MarketPage(
    onNavigateToCoin:(String)->Unit,
    isLoading:Boolean,
    isError:Boolean,
    topCoins: List<Coin>,
    topLosers:List<Coin>,
    topGainers:List<Coin>
){
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CryptoAppBar(
                isLeadingButton = false,
                title = stringResource(R.string.crypto_marketplace)
            )
        }
    ) { padding->


        Column(
            modifier = Modifier.padding(12.dp).verticalScroll(state = rememberScrollState())
        ) {

            Spacer(Modifier.height(80.dp))
            Section(stringResource(R.string.top_gainers), stringResource(R.string.last_24h))
            Spacer(Modifier.padding(4.dp))
            Box(modifier = Modifier.fillMaxWidth().height(260.dp)) {
                if (isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        LinearProgressBar()
                    }

                } else if (isError) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ErrorCard()
                    }
                } else {
                    LazyColumn {
                        items(items = topGainers) { coin ->
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
                if (isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LinearProgressBar()
                    }

                } else if (isError) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ErrorCard()
                    }
                } else {
                    LazyColumn {
                        items(items = topLosers) { coin ->
                            Spacer(Modifier.height(20.dp))
                            CoinCard(coin,onNavigateToCoin)


                        }
                    }

                }


            }
            Spacer(Modifier.height(10.dp))
            Section(stringResource(R.string.top_market_values),stringResource(R.string.last_24h))
            Box(modifier = Modifier.fillMaxWidth().height(260.dp)) {
                if (isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        LinearProgressBar()
                    }

                } else if (isError) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ErrorCard()
                    }
                } else {
                    LazyColumn {
                        items(items = topCoins) { coin ->
                            Spacer(Modifier.height(20.dp))
                            CoinCard(coin,onNavigateToCoin)


                        }
                    }

                }


            }


        }
    }

}
