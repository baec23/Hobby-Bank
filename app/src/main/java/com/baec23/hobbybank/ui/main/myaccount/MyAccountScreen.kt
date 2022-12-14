package com.baec23.hobbybank.ui.main.myaccount

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.hobbybank.model.HobbyClass
import com.baec23.hobbybank.model.User

@Composable
fun MyAccountScreen(
    viewModel: MyAccountViewModel = hiltViewModel()
) {
    val currUser = viewModel.currUser
    val myHobbyClasses by viewModel.myHobbyClasses.collectAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        if(currUser != null)
            UserDetailsSection(user = currUser)
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
) {
    PreferencesSection(
        modifier = Modifier.fillMaxWidth(),
        headerTitle = "My Details",
        headerIcon = Icons.Rounded.AccountCircle
    ) {
        Text("username : ${user.username}")
        Text("displayName : ${user.displayName}")
    }
}

@Composable
fun MyClassesSection(
    myHobbyClasses: List<HobbyClass>,
    onClassClicked: (HobbyClass) -> Unit,
    onCreateNewClassClicked: () -> Unit,
) {
    val a = Result.success("a")
    a.getOrElse {  }

    PreferencesSection(
        modifier = Modifier.fillMaxWidth(),
        headerTitle = "My Classes",
        headerIcon = Icons.Rounded.List
    ) {
        TextButton(onClick = onCreateNewClassClicked) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
            Text("Create New Class")
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

@Composable
fun PreferencesSection(
    modifier: Modifier = Modifier,
    headerTitle: String,
    headerIcon: ImageVector? = null,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.Bottom) {
            if (headerIcon != null) {
                Icon(
                    imageVector = headerIcon,
                    contentDescription = headerTitle,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
            Text(headerTitle)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Divider()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun PreferencesSectionPreview() {
    PreferencesSection(
        headerTitle = "My Section Title",
        headerIcon = Icons.Rounded.Favorite
    ) {
        Text("Content")
    }
}