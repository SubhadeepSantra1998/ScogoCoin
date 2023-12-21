package com.example.scogocoin.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scogocoin.common.Constants
import com.example.scogocoin.common.Resource
import com.example.scogocoin.data.remote.repository.CoinRepository
import com.example.scogocoin.view.coin_details.CoinDetailUiState
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
class CoinDetailViewModel @Inject constructor(
    private val repository: CoinRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = MutableStateFlow(CoinDetailUiState())
    val state: StateFlow<CoinDetailUiState> = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {coinId->
            getCoinDetail(coinId)
        }
    }


    private fun getCoinDetail(coinId: String) {
        viewModelScope.launch {
            repository.getCoinById(coinId).onStart {
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
                                coin = result.data
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