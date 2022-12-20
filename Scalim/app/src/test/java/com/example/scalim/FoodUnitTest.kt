package com.example.scalim

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.scalim.food.FoodListViewModel
import com.example.scalim.food.MainActivity
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock


class FoodUnitTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `check if item already in list`() {
    }

    @Test
    fun `load add success state`() {
        val viewModel = FoodListViewModel()
        val mockPaper = mock<IPaperWrapper>()
        whenever(mockPaper.read(eq("foods"))).thenReturn(listOf())
        viewModel.paper = mockPaper

        viewModel.addFood( Food("1234", "DrPapier", "Demain", "https://i.ytimg.com/vi/vsaC5nCBDZc/maxresdefault.jpg"),  MainActivity())
        Assert.assertEquals(FoodListViewModel.AddState.Success,
            viewModel.stateLiveData.value)
    }



}