@file:OptIn(ExperimentalMaterial3Api::class)

package com.baec23.hobbybank.ui.main.createclass

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.ui.comp.DisplaySection
import com.baec23.hobbybank.ui.main.createclass.comp.AddImageButton
import com.baec23.hobbybank.ui.main.createclass.comp.JobImagesList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateClassTab1(
    viewModel: CreateClassViewModel = hiltViewModel()
) {
    val classNameFormState by viewModel.classNameInputState
    var className: String = ""
    var errorMessage: String? = null

    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val addedBitmaps by viewModel.addedImages

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    LaunchedEffect(imageUri) {
        imageUri?.let {
            val source = ImageDecoder
                .createSource(context.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
            bitmap.value?.let { bitmap ->
                viewModel.onEvent(CreateClass1UiEvent.ImageAdded(bitmap))
            }
        }
    }

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
    ) {
        DisplaySection(headerText = "수업 사진 추가") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                AddImageButton(
                    modifier = Modifier
                        .width(100.dp)
                        .aspectRatio(1f)
                ) {
                    launcher.launch("image/*")
                }
                JobImagesList(
                    bitmaps = addedBitmaps,
                    onRemove = { viewModel.onEvent(CreateClass1UiEvent.ImageRemoved(it)) }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        DisplaySection(
            modifier = Modifier
                .fillMaxWidth(),
            contentModifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            headerText = "수업 상세",
            headerSubtext = "상세 내용을 입력 해 주세요"
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyLarge,
                    value = className,
                    onValueChange = { viewModel.onEvent(CreateClass1UiEvent.NameChanged(it)) },
                    isError = errorMessage != null,
                    singleLine = true,
                    label = {
                        Text(
                            text = "수업 제목",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    placeholder = {
                        Text(
                            text = "수업 제목을 적어 주세요",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { viewModel.onEvent(CreateClass1UiEvent.NextPressed) },
            shape = RoundedCornerShape(5.dp)
        ) {
            Text("다음")
        }
    }
}