package com.baec23.hobbybank.ui.main.myclassschedule

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyClassScheduleViewModel @Inject constructor() : ViewModel() {

    fun onEvent(event: MyClassScheduleUiEvent) {
//        when (event) {
//        }
    }
}

sealed class MyClassScheduleUiEvent {
}