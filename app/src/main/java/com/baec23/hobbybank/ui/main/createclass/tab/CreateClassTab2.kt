package com.baec23.hobbybank.ui.main.createclass.tab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.ui.comp.DisplaySection
import com.baec23.hobbybank.ui.comp.inputfield.NumberInputField
import com.baec23.hobbybank.ui.comp.inputfield.TextInputField
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        DisplaySection(
            headerText = "기본 설정"
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                NumberInputField(
                    modifier = Modifier.weight(1f),
                    value = minStudents,
                    label = "최소 인원수",
                    placeHolder = "최소 인원을 적어 주세요",
                    onValueChange = {
                        viewModel.onEvent(CreateClass2UiEvent.MinStudentsChanged(it))
                    })
                NumberInputField(
                    modifier = Modifier.weight(1f),
                    value = maxStudents,
                    label = "최대 인원수",
                    placeHolder = "최대 인원을 적어 주세요",
                    onValueChange = {
                        viewModel.onEvent(CreateClass2UiEvent.MaxStudentsChanged(it))
                    })
            }
            Spacer(modifier = Modifier.height(12.dp))

            TextInputField(
                value = location,
                onValueChanged = { viewModel.onEvent(CreateClass2UiEvent.LocationChanged(it)) },
                label = "수업 장소",
                placeholder = "수업 장소를 적어 주세요"
            )

            Spacer(modifier = Modifier.height(12.dp))

            NumberInputField(
                value = duration,
                label = "예상 소요 시간",
                onValueChange = { viewModel.onEvent(CreateClass2UiEvent.DurationChanged(it)) },
                placeHolder = "예상 소요 시간을 적어 주세요"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        DisplaySection(headerText = "상세 내용") {
            TextInputField(
                value = details,
                onValueChanged = { viewModel.onEvent(CreateClass2UiEvent.DetailsChanged(it)) },
                label = "수업 설명",
                placeholder = "수업을 자세히 설명 해 주세요\n예) 수업 소개",
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
            Button(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.onEvent(CreateClass2UiEvent.PrevPressed) },
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("이전")
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.onEvent(CreateClass2UiEvent.NextPressed) },
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("다음")
            }
        }
    }
}