package com.example.monitor

import com.necdetzr.common.model.DialogType
import kotlinx.coroutines.flow.Flow

interface AppStateMonitor {
    val events: Flow<AppEvent>

    suspend fun emitEvent(event: AppEvent)

    suspend fun showDialog(
        message:String,
        title:String? = null,
        cancelable: Boolean? = null,
        positiveButtonText: String? = null,
        negativeButtonText: String? = null,
        neutralButtonText: String? = null,
        onPositiveClick: (() -> Unit)? = null,
        onNegativeClick: (() -> Unit)? = null,
        onNeutralClick: (() -> Unit)? = null,
        dialogType: DialogType? = null,
        imageUrl: String? = null,
    )
}