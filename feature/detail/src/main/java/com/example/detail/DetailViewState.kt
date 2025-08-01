package com.example.detail

import com.necdetzr.common.base.IViewState
import com.necdetzr.home.component.domain.data.DetailCoin

data class DetailViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val coin: DetailCoin? = null
): IViewState
