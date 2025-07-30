package com.example.search

import com.necdetzr.common.base.IViewState
import com.necdetzr.home.component.domain.data.Coin

data class SearchViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val coins: List<Coin>  = emptyList(),
    val searchQuery:String = ""

): IViewState