package com.baec23.hobbybank.ui.comp.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.baec23.hobbybank.model.User

@Composable
fun UserInfoCard(
    modifier: Modifier = Modifier,
    userName: String,
    userImageUrl: String,
    subText: String? = null,
    onProfileImageClick: (() -> Unit)? = null,
    onEditDetailsClick: (() -> Unit)? = null,
    CardHeight: Dp = 150.dp,
    CardElevation: Dp = 6.dp,
) {

    Card(
        modifier = Modifier
            .height(CardHeight)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = CardElevation),
    ) {
        Row(modifier = Modifier) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight()
                    .apply {
                        onProfileImageClick?.let { this.clickable { onProfileImageClick } }
                    }
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .clip(CircleShape)
                        .fillMaxWidth(0.6f)
                        .align(Alignment.Center),
                    model = userImageUrl,
                    contentScale = ContentScale.Inside,
                    loading = {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.height(100.dp)
                            )
                            Text("Loading...")
                        }
                    },
                    contentDescription = userName
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Box(modifier = Modifier) {
                    Column(modifier = Modifier) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "$userName 님",
                            fontSize = 24.sp,
                        )
                        subText?.let { Text(text = subText) }
                    }
                }
                Text(
                    modifier = Modifier
                        .apply { onEditDetailsClick?.let { this.clickable { onEditDetailsClick } } },
                    text = ">> 내 정보 수정하기"
                )
            }
        }
    }
}


@Preview
@Composable
fun UserInfoCardPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center,
    ) {
        UserInfoCard(
            modifier = Modifier,
            userName = "test User",
            userImageUrl = "https://picsum.photos/1200"
        )

    }
}