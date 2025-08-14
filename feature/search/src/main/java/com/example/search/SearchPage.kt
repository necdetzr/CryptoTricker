package com.example.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.search.components.CoinCard
import com.example.ui.component.CryptoAppBar
import com.example.ui.component.CustomTextField
import com.example.ui.component.ErrorCard
import com.example.ui.component.LinearProgressBar
import com.example.ui.component.Section
import com.necdetzr.designsystem.R
import com.necdetzr.home.component.domain.data.Coin

@Composable
fun SearchPage(
    onValueChange:(String)->Unit,
    searchQuery:String,
    isLoading:Boolean,
    isError:Boolean,
    onNavigateToCoin:(String)->Unit,
    coins:List<Coin>,
    filteredCoins:List<Coin>
){
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CryptoAppBar(
                isLeadingButton = false,
                title = stringResource(R.string.all_cryptocurrencies)
            )

        }
    ) { padding->

        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
        ) {
            Spacer(Modifier.height(72.dp))

            CustomTextField(
                icon = Icons.Default.Search,
                value = searchQuery,
                placeholder = stringResource(R.string.search_crypto_placeholder),
                onValueChange = onValueChange
            )
            Spacer(Modifier.height(20.dp))
            Section(stringResource(R.string.all_coins),stringResource(R.string.last_24h))
            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(1f)){
                if(isLoading){
                    LinearProgressBar()
                }else if (isError){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        ErrorCard()
                    }

                }else {
                    if (filteredCoins.isEmpty()) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text("There is no coin such as $searchQuery")
                        }
                    }
                    LazyColumn(
                        modifier = Modifier.padding(vertical = 12.dp)

                    ) {
                        items(filteredCoins,key = {it.id}) { coin ->

                                Spacer(Modifier.height(20.dp))
                                CoinCard(coin, onNavigateToCoin)

                        }

                    }
                }
            }
        }
    }
}