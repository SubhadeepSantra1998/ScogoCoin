package com.example.scogocoin.viewModel

import app.cash.turbine.test
import com.example.scogocoin.common.Resource
import com.example.scogocoin.data.model.CoinModel
import com.example.scogocoin.data.remote.repository.CoinRepository
import com.example.scogocoin.view.coin.CoinUiState
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class CoinViewModelTest {

    private val repository : CoinRepository = mockk()

    private lateinit var viewModel: CoinViewModel

    @Before
    fun setUp() {
        viewModel = CoinViewModel(repository)
    }


    @Test
    fun `When fetching coins content state is shown`() = runBlocking{
        viewModel.state.test {
            val emission = awaitItem()
            //assert(viewModel.state.value is )
        }

    }

    @After
    fun tearDown() {
    }
}