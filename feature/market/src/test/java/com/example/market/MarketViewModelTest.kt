package com.example.market

import com.necdetzr.home.component.domain.repository.CoinRepository
import com.necdetzr.home.component.domain.usecase.GetTopCoinsUseCase
import com.necdetzr.testing.testing.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MarketViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val coinRepository: CoinRepository = mockk()

    private val getTopCoinsUseCase = GetTopCoinsUseCase(
        repository = coinRepository
    )

    private lateinit var viewModel: MarketViewModel

    @Before
    fun setup(){


        viewModel = MarketViewModel(
            getTopCoinsUseCase = getTopCoinsUseCase
        )
        coEvery { viewModel.getTopCoins() }
    }

    @Test
    fun `initial state is loading`() = runTest {
        coEvery { viewModel.getTopCoins() }

        val viewModel = MarketViewModel(getTopCoinsUseCase)

        assertEquals(true, viewModel.uiState.value.loading)

        advanceUntilIdle()
        assertEquals(false, viewModel.uiState.value.loading)
    }


}