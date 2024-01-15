package com.example.articles.ui.home

import com.example.articles.utils.TabItemModel
import com.example.articles.utils.getTabList

sealed class HomeScreenUIState {
    data class UpdateTabIndex(val tabIndex: Int) : HomeScreenUIState()
    data class SelectedCountry(val selectedCountry: String) : HomeScreenUIState()
    data class UpdateBottomSheetState(val value : Boolean) : HomeScreenUIState()

}
data class HomeScreenUIStateModel(
    val tabIndex : Int = 0,
    val tabItems : List<TabItemModel> = getTabList(),
    val selectedCountry : String = "",
    val showBottomSheet : Boolean = false
)