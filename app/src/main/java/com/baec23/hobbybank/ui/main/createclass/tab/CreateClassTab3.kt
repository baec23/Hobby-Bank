package com.baec23.hobbybank.ui.main.createclass.tab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.model.HobbyClassSession
import com.baec23.hobbybank.ui.comp.button.HBButton
import com.baec23.hobbybank.ui.comp.section.DisplaySection
import com.baec23.hobbybank.ui.comp.inputfield.TextInputField
import com.baec23.hobbybank.ui.main.createclass.CreateClass3UiEvent
import com.baec23.hobbybank.ui.main.createclass.CreateClassViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun CreateClassTab3(
    viewModel: CreateClassViewModel = hiltViewModel()
) {
    val selectedDate by viewModel.selectedDate
    val selectedTime by viewModel.selectedTime
    val addedSessions by viewModel.addedSessions
    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        DisplaySection(headerText = "등록된 수업 시간 목록") {
            Column {
                addedSessions.forEach { session ->
                    AddedSessionCard(hobbyClassSession = session)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        DisplaySection(headerText = "수업 시간 설정") {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextInputField(
                    modifier = Modifier.weight(0.9f),
                    value = selectedDate,
                    onValueChanged = {},
                    label = "날자",
                    readOnly = true
                )
                IconButton(modifier = Modifier
                    .weight(0.1f),
                    onClick = { dateDialogState.show() }) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Set Date"
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextInputField(
                    modifier = Modifier.weight(0.9f),
                    value = selectedTime,
                    onValueChanged = {},
                    label = "시간",
                    readOnly = true
                )
                IconButton(modifier = Modifier.weight(0.1f),
                    onClick = { timeDialogState.show() }) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Set Time"
                    )
                }
            }

            TextButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { viewModel.onEvent(CreateClass3UiEvent.AddSessionPressed) },
                shape = RoundedCornerShape(5.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Text("수업 시간 추가")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HBButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.onEvent(CreateClass3UiEvent.PrevPressed) },
                text = "이전"
            )
            HBButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.onEvent(CreateClass3UiEvent.FinishPressed) },
                text = "완료"
            )
        }
    }

    MaterialDialog(
        dialogState = dateDialogState,
        shape = RoundedCornerShape(5.dp),
        autoDismiss = true,
        buttons = {
            positiveButton(
                text = "선택",
                textStyle = TextStyle.Default.copy(color = MaterialTheme.colorScheme.primary)
            ) {

            }
            negativeButton(
                text = "취소",
                textStyle = TextStyle.Default.copy(color = MaterialTheme.colorScheme.primary)
            )
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "날자를 선택 하세요",
            colors = DatePickerDefaults.colors(
                dateActiveBackgroundColor = MaterialTheme.colorScheme.primary,
                headerBackgroundColor = MaterialTheme.colorScheme.primary,
            )
        ) {
            viewModel.onEvent(CreateClass3UiEvent.SelectedDateChanged(it.toString()))
        }
    }

    MaterialDialog(
        dialogState = timeDialogState,
        shape = RoundedCornerShape(5.dp),
        autoDismiss = true,
        buttons = {
            positiveButton(
                text = "선택",
                textStyle = TextStyle.Default.copy(color = MaterialTheme.colorScheme.primary)
            ) {

            }
            negativeButton(
                text = "취소",
                textStyle = TextStyle.Default.copy(color = MaterialTheme.colorScheme.primary)
            )
        }
    ) {
        timepicker(
            title = "시간을 선택 하세요",
            colors = TimePickerDefaults.colors(
                inactiveBackgroundColor = Color.LightGray,
                activeBackgroundColor = MaterialTheme.colorScheme.primary,
                selectorColor = MaterialTheme.colorScheme.primary
            )
        ) {
            viewModel.onEvent(CreateClass3UiEvent.SelectedTimeChanged(it.toString()))
        }
    }
}

@Composable
fun AddedSessionCard(
    hobbyClassSession: HobbyClassSession,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(5.dp),
    ) {
        Text(text = hobbyClassSession.date)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = hobbyClassSession.startTime)
    }
}

@Preview
@Composable
fun AddedSessionCardPreview() {
    AddedSessionCard(
        hobbyClassSession = HobbyClassSession(
            date = LocalDate.now().toString(),
            startTime = LocalTime.now().toString()
        )
    )
}