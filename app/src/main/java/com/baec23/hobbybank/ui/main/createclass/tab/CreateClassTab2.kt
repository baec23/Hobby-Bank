@file:OptIn(ExperimentalComposeUiApi::class)

package com.baec23.hobbybank.ui.main.createclass.tab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.ui.comp.button.HBButton
import com.baec23.hobbybank.ui.comp.inputfield.NumberInputField
import com.baec23.hobbybank.ui.comp.inputfield.TextInputField
import com.baec23.hobbybank.ui.comp.section.DisplaySection
import com.baec23.hobbybank.ui.main.createclass.CreateClass2UiEvent
import com.baec23.hobbybank.ui.main.createclass.CreateClassViewModel

@Composable
fun CreateClassTab2(
    viewModel: CreateClassViewModel = hiltViewModel()
) {
    val formState by viewModel.detailsFormState
    val minStudents = formState.minStudents
    val maxStudents = formState.maxStudents
    val location = formState.location
    val duration = formState.durationMinutes
    val details = formState.details

    val (minStudentsRef, maxStudentsRef, locationRef, durationRef, detailsRef) = remember { FocusRequester.createRefs() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        DisplaySection(
            headerText = "?????? ??????"
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                NumberInputField(
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(minStudentsRef)
                        .focusProperties { next = maxStudentsRef },
                    value = minStudents,
                    label = "?????? ?????????",
                    placeHolder = "?????? ????????? ?????? ?????????",
                    onValueChange = {
                        viewModel.onEvent(CreateClass2UiEvent.MinStudentsChanged(it))
                    },
                    imeAction = ImeAction.Next
                )
                NumberInputField(
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(maxStudentsRef)
                        .focusProperties {
                            next = locationRef
                            previous = minStudentsRef
                        },
                    value = maxStudents,
                    label = "?????? ?????????",
                    placeHolder = "?????? ????????? ?????? ?????????",
                    onValueChange = {
                        viewModel.onEvent(CreateClass2UiEvent.MaxStudentsChanged(it))
                    },
                    imeAction = ImeAction.Next
                )
            }

            TextInputField(
                modifier = Modifier
                    .focusRequester(locationRef)
                    .focusProperties {
                        next = durationRef
                        previous = maxStudentsRef
                    },
                value = location,
                onValueChanged = { viewModel.onEvent(CreateClass2UiEvent.LocationChanged(it)) },
                label = "?????? ??????",
                placeholder = "?????? ????????? ?????? ?????????",
                imeAction = ImeAction.Next
            )

            NumberInputField(
                modifier = Modifier
                    .focusRequester(durationRef)
                    .focusProperties {
                        next = detailsRef
                        previous = locationRef
                    },
                value = duration,
                label = "?????? ?????? ??????",
                onValueChange = { viewModel.onEvent(CreateClass2UiEvent.DurationChanged(it)) },
                placeHolder = "?????? ?????? ????????? ?????? ?????????",
                imeAction = ImeAction.Next
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        DisplaySection(headerText = "?????? ??????") {
            TextInputField(
                modifier = Modifier
                    .focusRequester(detailsRef)
                    .focusProperties { previous = durationRef },
                value = details,
                onValueChanged = { viewModel.onEvent(CreateClass2UiEvent.DetailsChanged(it)) },
                label = "?????? ??????",
                placeholder = "????????? ????????? ?????? ??? ?????????\n???) ?????? ??????",
                singleLine = false,
                minLines = 5,
                maxLines = 10
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HBButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.onEvent(CreateClass2UiEvent.PrevPressed) },
                text = "??????"
            )
            HBButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.onEvent(CreateClass2UiEvent.NextPressed) },
                text = "??????"
            )
        }
    }
}