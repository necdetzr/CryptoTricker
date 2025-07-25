package com.necdetzr.language

import com.necdetzr.common.base.IViewState

data class LanguageViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val selected:Boolean = false,
    val lang:String = "EN"

): IViewState