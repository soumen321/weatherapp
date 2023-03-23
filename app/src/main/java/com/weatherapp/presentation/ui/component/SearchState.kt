package com.weatherapp.presentation.ui.component

sealed interface SearchState{
    val enabled: Boolean
    val query: String

    data class Changing(
        override val enabled: Boolean = true,
        override val query: String = ""
    ) : SearchState

    data class Closed(
        override val enabled: Boolean = false,
        override val query: String = ""
    ) : SearchState
}