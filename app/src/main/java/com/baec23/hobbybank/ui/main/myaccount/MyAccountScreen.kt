package com.baec23.hobbybank.ui.main.myaccount

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.model.HobbyClass
import com.baec23.hobbybank.model.User
import com.baec23.hobbybank.ui.comp.PreferencesSection
import com.baec23.hobbybank.ui.comp.card.UserInfoCard

private const val TAG = "MyAccountScreen"

@Composable
fun MyAccountScreen(
    viewModel: MyAccountViewModel = hiltViewModel()
) {
    val currUser = viewModel.currUser
    val myHobbyClasses by viewModel.myHobbyClasses.collectAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        if (currUser != null)
            UserDetailsSection(
                user = currUser,
                onEditDetailsClicked = {
                    Log.d(TAG, "MyAccountScreen: Edit My Profile Pressed")
                    viewModel.onEvent(MyAccountUiEvent.EditDetailsPressed)
                },
                onProfileImageClicked ={
                    viewModel.onEvent(MyAccountUiEvent.EditDetailsPressed)
                }
            )
        MyClassesSection(
            myHobbyClasses = myHobbyClasses,
            onClassClicked = {},
            onCreateNewClassClicked = { viewModel.onEvent(MyAccountUiEvent.CreateNewClassPressed) }
        )
    }
}

@Composable
fun UserDetailsSection(
    user: User,
    onEditDetailsClicked: () -> Unit,
    onProfileImageClicked: () -> Unit,
) {
    PreferencesSection(
        modifier = Modifier.fillMaxWidth(),
        headerTitle = "내 회원 정보",
        headerIcon = Icons.Rounded.AccountCircle
    ) {
        UserInfoCard(
            userName = user.displayName,
            userImageUrl = "https://picsum.photos/1200",
            onEditDetailsClick = onEditDetailsClicked,
            onProfileImageClick = onProfileImageClicked,
            subText = "나는 테스트유저라고 해 만나서 반가워 가나다라마바사",
            subTextColor = Color.DarkGray,
        )
    }
}

@Composable
fun MyClassesSection(
    myHobbyClasses: List<HobbyClass>,
    onClassClicked: (HobbyClass) -> Unit,
    onCreateNewClassClicked: () -> Unit,
) {
    val a = Result.success("a")
    a.getOrElse { }

    PreferencesSection(
        modifier = Modifier.fillMaxWidth(),
        headerTitle = "내가 만든 수업",
        headerIcon = Icons.Rounded.List
    ) {
        TextButton(onClick = onCreateNewClassClicked) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
            Text("나만의 수업 제작 하기")
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            items(myHobbyClasses.size) { index ->
                HobbyClassCard(
                    modifier = Modifier.fillMaxWidth(),
                    hobbyClass = myHobbyClasses[index]
                )
            }
        }
    }
}

@Composable
fun HobbyClassCard(
    modifier: Modifier = Modifier,
    hobbyClass: HobbyClass
) {
    ElevatedCard(modifier = modifier.padding(8.dp)) {
        Text(text = hobbyClass.name)
        Text(text = hobbyClass.details)
    }
}