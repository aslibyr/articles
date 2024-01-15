package com.example.articles.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiStates = MutableStateFlow(HomeScreenUIStateModel())
    val uiStates = _uiStates.asStateFlow()

    // Updating UI States.
    fun updateUIEvents(event:HomeScreenUIState) {
        when (event) {
            is HomeScreenUIState.SelectedCountry -> {
                _uiStates.update {
                    it.copy(
                        selectedCountry = event.selectedCountry
                    )
                }
            }
            is HomeScreenUIState.UpdateBottomSheetState -> {
                _uiStates.update {
                    it.copy(
                        showBottomSheet = event.value
                    )
                }
            }
            is HomeScreenUIState.UpdateTabIndex -> {
                _uiStates.update {
                    it.copy(
                        tabIndex = event.tabIndex
                    )
                }
            }
        }
    }
}