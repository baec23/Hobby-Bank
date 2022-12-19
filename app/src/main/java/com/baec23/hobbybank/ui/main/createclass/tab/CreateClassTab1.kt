@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.ui.comp.section.DisplaySection
import com.baec23.hobbybank.ui.comp.ImageAdder
import com.baec23.hobbybank.ui.comp.button.HBButton
import com.baec23.hobbybank.ui.comp.inputfield.TextInputField
import com.baec23.hobbybank.ui.main.createclass.CreateClass1UiEvent
import com.baec23.hobbybank.ui.main.createclass.CreateClassViewModel
import com.baec23.hobbybank.ui.main.createclass.InputFormState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateClassTab1(
    viewModel: CreateClassViewModel = hiltViewModel()
) {
    val classNameFormState by viewModel.classNameInputState
    var className: String = ""
    var errorMessage: String? = null

    val addedBitmaps by viewModel.addedImages

    when (classNameFormState) {
        InputFormState.Empty -> {
            errorMessage = null
            className = ""
        }

        is InputFormState.Invalid -> {
            val formState = (classNameFormState as InputFormState.Invalid)
            errorMessage = formState.error
            className = formState.text
        }

        is InputFormState.Valid -> {
            val formState = (classNameFormState as InputFormState.Valid)
            errorMessage = null
            className = formState.text
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        DisplaySection(headerText = "수업 사진 추가") {
            ImageAdder(
                addedImages = addedBitmaps,
                onImageAdded = { viewModel.onEvent(CreateClass1UiEvent.ImageAdded(it)) },
                onImageRemoved = { viewModel.onEvent(CreateClass1UiEvent.ImageRemoved(it)) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        DisplaySection(
            modifier = Modifier
                .fillMaxWidth(),
            headerText = "수업 상세",
            headerSubtext = "상세 내용을 입력 해 주세요"
        ) {
            TextInputField(
                value = className,
                onValueChanged = { viewModel.onEvent(CreateClass1UiEvent.NameChanged(it)) },
                label = "수업 제목",
                placeholder = "수업 제목을 적어 주세요"
            )
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
                onClick = { viewModel.onEvent(CreateClass1UiEvent.NextPressed) },
                text = "다음"
            )
        }
    }
}