package com.baec23.hobbybank.ui.main.viewclassdetails

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewClassDetailsViewModel @Inject constructor() : ViewModel() {

}

sealed class ViewClassDetailsUiEvent {
}