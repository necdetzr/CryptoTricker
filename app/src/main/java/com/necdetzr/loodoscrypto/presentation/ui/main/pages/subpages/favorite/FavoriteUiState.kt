package com.necdetzr.loodoscrypto.presentation.ui.main.pages.subpages.favorite

import com.necdetzr.loodoscrypto.domain.model.DetailCoin

data class FavoriteUiState(
    val favorites:List<DetailCoin?> = emptyList(),
    val userId:String?=""
)