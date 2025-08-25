package com.example.monitor.imp

import com.example.monitor.AppEvent
import com.example.monitor.AppStateMonitor
import com.necdetzr.common.model.DialogType
import com.necdetzr.common.model.FriendlyMessageDTO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultAppStateMonitor @Inject constructor() : AppStateMonitor {
    private val _events = Channel<AppEvent>(Channel.BUFFERED)

    override val events : Flow<AppEvent> = _events.receiveAsFlow()

    override suspend fun emitEvent(event: AppEvent) {
        _events.send(event)
    }

    override suspend fun showDialog(
        message: String,
        title: String?,
        cancelable: Boolean?,
        positiveButtonText: String?,
        negativeButtonText: String?,
        neutralButtonText: String?,
        onPositiveClick: (() -> Unit)?,
        onNegativeClick: (() -> Unit)?,
        onNeutralClick: (() -> Unit)?,
        dialogType: DialogType?,
        imageUrl: String?
    ) {
        emitEvent(
            AppEvent.ShowMessage(
                friendlyMessage =
                    FriendlyMessageDTO(
                        title = title,
                        message = message,
                        cancelable = cancelable,
                        buttonPositive = positiveButtonText,
                        buttonNegative = negativeButtonText,
                        buttonNeutral = neutralButtonText,
                        positiveButtonClick = onPositiveClick,
                        negativeButtonClick = onNegativeClick,
                        imageUrl = imageUrl,
                    ),
                appEventType = AppEvent.AppEventType.MODAL,
                dialogType = dialogType
            )
        )
    }


}