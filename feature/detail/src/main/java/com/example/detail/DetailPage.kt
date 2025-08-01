package com.example.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.designsystem.component.LinearProgressBar
import com.example.detail.components.DetailText
import com.example.detail.components.DetailedCoinCard
import com.example.ui.component.ErrorCard
import com.example.ui.component.Section
import com.example.ui.util.FormatFunctions
import com.necdetzr.home.component.domain.data.DetailCoin


@Composable
fun DetailPage(
     coin: DetailCoin?,
     onBackButton:()->Unit,
     onRemoveFavorite:()->Unit,
     onAddFavorite:()->Unit,
     isLoading:Boolean,
     isError:Boolean

){
    val infoList = listOf(
        ("Market Cap" to coin?.marketCap),
        ("Total Volume" to coin?.totalVolume),
        ("Rank" to coin?.marketCapRank),
        ("Total Supply" to coin?.totalSupply),
        ("Max Supply" to coin?.maxSupply),
        ("High 24h" to coin?.high24h),
        ("Low 24h" to coin?.low24h),
        ("Hash Algorithm") to coin?.hashAlgorithm,
    )
    var favorited by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { padding->
        Column(
            modifier = Modifier.padding(12.dp)
                .verticalScroll(rememberScrollState()),

            ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    onBackButton()
                }) {
                    Icon(
                        Icons.AutoMirrored.Rounded.ArrowBack, contentDescription =
                            "back"
                    )
                }
                Text("${coin?.name} Information", style = MaterialTheme.typography.headlineSmall)
                Spacer(Modifier.weight(1f))
                if (favorited) {
                    IconButton(onClick = {
                       onRemoveFavorite()
                    }) {
                        Icon(Icons.Default.Star, contentDescription = "star", tint = Color(0xFFBDAD0D))
                    }
                } else {
                    IconButton(onClick = {
                        onAddFavorite()
                    }) {
                        Icon(Icons.Default.StarOutline, contentDescription = "star")
                    }
                }


            }
            Spacer(Modifier.height(20.dp))
            if (isLoading) {
                LinearProgressBar()
            } else if (isError) {
                ErrorCard()
            } else {
                DetailedCoinCard(coin = coin )


            }

            Spacer(Modifier.height(60.dp))
            Section("Description")
            Spacer(Modifier.height(10.dp))
            Card(
                modifier = Modifier.height(200.dp).fillMaxWidth()
                    .verticalScroll(state = rememberScrollState()),
                border = CardDefaults.outlinedCardBorder()

            ) {
                if (isLoading) {
                    Column(
                        modifier = Modifier.fillMaxHeight().padding(12.dp),
                        verticalArrangement = Arrangement.Center,

                        ) {
                        LinearProgressBar()

                    }
                } else if (isError) {
                    ErrorCard()
                } else {
                    Text(
                        text = coin?.description ?: "No Description.",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(12.dp)
                    )

                }


            }
            Spacer(Modifier.height(20.dp))


            Section("Information")


            Box(modifier = Modifier.height(260.dp)){
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(infoList.size) { item ->
                        val (label, size) = infoList[item]
                        val formattedText = FormatFunctions.formatAnyNumber(size)
                        val finalText = when (label) {
                            "Market Cap", "Total Volume", "High 24h", "Low 24h" -> "$formattedText$"
                            else -> formattedText
                        }
                        if (isLoading) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxHeight().padding(32.dp)
                            ) {
                                LinearProgressBar()

                            }
                        } else if (isError) {
                            ErrorCard()
                        } else {
                            DetailText(title = label, text = finalText)

                        }


                    }
                }
            }


        }
    }
}