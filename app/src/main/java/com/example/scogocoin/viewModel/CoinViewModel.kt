package com.example.scogocoin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scogocoin.common.Resource
import com.example.scogocoin.data.remote.repository.CoinRepository
import com.example.scogocoin.view.coin.CoinUiEvent
import com.example.scogocoin.view.coin.CoinUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinViewModel @Inject constructor(
    private val repository: CoinRepository
): ViewModel() {

    private val _state = MutableStateFlow(CoinUiState())
    val state: StateFlow<CoinUiState> = _state.asStateFlow()

    fun onEvent(event: CoinUiEvent) {
        when (event) {
            is CoinUiEvent.SearchQueryChanged -> {
                _state.update {
                    it.copy(
                        searchQuery = event.query
                    )
                }
            }
            is CoinUiEvent.Search -> {
                searchCoin()
            }
        }
    }

    private fun searchCoin() {

        if(_state.value.searchQuery.isNotBlank()){
            val searchedCoins = _state.value.coins?.filter {
                it.name.lowercase().contains(_state.value.searchQuery.lowercase())
            }
            _state.update {
                it.copy(
                    coins = searchedCoins
                )
            }
        }else{
            _state.update {
                it.copy(
                    coins = _state.value.tempCoinList
                )
            }
        }
    }

    init {
        getAllCoins()
    }

    fun getAllCoins() {
        viewModelScope.launch {
            repository.getCoins().onStart {
                _state.update {
                    it.copy(
                        isLoading = true
                    )
                }
            }
                .onEach { result ->

                    _state.value = when (result){
                        is Resource.Loading -> {
                            _state.value.copy(
                                isLoading = true
                            )
                        }
                        is Resource.Success -> {
                            _state.value.copy(
                                isLoading = false,
                                isRefreshing = false,
                                coins = result.data,
                                tempCoinList = result.data
                            )

                        }
                        is Resource.Error -> {
                            _state.value.copy(
                                isLoading = false,
                                errorMessage = result.message
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }
}