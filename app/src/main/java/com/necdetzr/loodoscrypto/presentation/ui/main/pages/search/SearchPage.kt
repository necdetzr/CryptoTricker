package com.necdetzr.loodoscrypto.presentation.ui.main.pages.search

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.necdetzr.loodoscrypto.R
import com.necdetzr.loodoscrypto.presentation.ui.components.CustomTextField
import com.necdetzr.loodoscrypto.presentation.ui.main.components.Section
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.necdetzr.loodoscrypto.presentation.ui.components.LinearProgressBar
import com.necdetzr.loodoscrypto.presentation.ui.main.components.CoinCard
import com.necdetzr.loodoscrypto.presentation.ui.main.components.ErrorCard

@Composable
fun SearchPage(viewModel: SearchViewModel = hiltViewModel(),onNavigateToCoin:(String)->Unit){
    val coins by viewModel.coins.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredCoin by viewModel.filteredCoins.collectAsState()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { padding->
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
        ) {
            Text(stringResource(R.string.all_cryptocurrencies),style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(20.dp))

            CustomTextField(
                icon = Icons.Default.Search,
                value = searchQuery,
                placeholder = stringResource(R.string.search_crypto_placeholder),
                onValueChange = {viewModel.onSearchQueryChanged(it)}
            )
            Spacer(Modifier.height(20.dp))
            Section(stringResource(R.string.all_coins),stringResource(R.string.last_24h))
            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(1f)){
                if(uiState.isLoading){
                    LinearProgressBar()
                }else if (uiState.isError){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        ErrorCard()
                    }

                }else {
                    LazyColumn(
                        modifier = Modifier.padding(vertical = 12.dp)

                    ) {
                        items(filteredCoin,key = {it.id}) { coin ->
                            Spacer(Modifier.height(20.dp))
                            CoinCard(coin, onNavigateToCoin )
                        }

                    }
                }
            }
        }
    }


}