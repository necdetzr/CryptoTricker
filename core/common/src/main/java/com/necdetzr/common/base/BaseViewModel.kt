package com.necdetzr.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necdetzr.common.resource.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface IViewState{
    val loading:Boolean
    val showErrorModal: Boolean
}

abstract class BaseViewModel<State: IViewState> : ViewModel(){
    //Bir UI'in ilk state'ini 1 kez çalıştırır
    private val initialState:State by lazy {createInitialState()}
    //Event olaylarını alır, tek seferlik Toast gibi eventleri hızlıca çalıştırır.
    private val _event = Channel<BaseEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    //İlk State'i oluşturur. State türündedir.
    //Farklı durumlara göre her durumda override edilmelidir
    abstract fun createInitialState(): State

    //Loading state'ini duruma göre gösterir.
    //Farklı durumlara göre her durumda override edilmelidir.
    abstract fun showLoading(isLoading:Boolean)

    //Sadece buradan türeyen viewModellere anlık state'i aktarır.
    protected val currentState:State
        get() =  _uiState.value

    //bildiğimiz state.
    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState:StateFlow<State> = _uiState

    //State'i belirler
    protected fun setState(reduce: State.()->State){
        _uiState.update { currentState.reduce() }
    }

    //Event yollar
    protected suspend fun sendEvent(event: BaseEvent){
        _event.send(event)
    }

    //Event'i viewModelScope içinde yollar.
    protected fun sendEventInViewModelScope(event: BaseEvent){
        viewModelScope.launch { _event.send(event) }
    }

    //Belirlenen use case'i çalıştırır
    protected fun <P,T : Any> executeUseCase(
        useCase: UseCase<P, Flow<Resource<T>>>,
        parameter:P,
        onSuccess:(T)->Unit,
        onError:(Throwable?)->Unit,
        onLoading:()->Unit = {},
        isShowLoading:Boolean = true,
    ){
        viewModelScope.launch {
            useCase(parameter)
                .onEach {
                    when(it){
                        //Başarı anında loading false'a düşer
                        //Success ise o useCase'in verisiyle yazılır.
                        is Resource.Success ->{
                            showLoading(false)
                            onSuccess(it.data)

                        }
                        //Loading anında loading gösterilir.
                        is Resource.Loading->{
                            showLoading(isShowLoading)
                            onLoading()
                        }
                        //Error anında loading durur
                        //Hata Loga düşer
                        is Resource.Error->{
                            showLoading(false)
                            it.exception?.printStackTrace()
                            onError(it.exception)
                        }

                    }

                }
                //ViewModelScope içinde launch eder.
                .launchIn(viewModelScope)

        }
    }

}