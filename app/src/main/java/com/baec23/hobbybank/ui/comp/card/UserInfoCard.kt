package com.baec23.hobbybank.ui.comp.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage

@Composable
fun UserInfoCard(
    modifier: Modifier = Modifier,
    userName: String,
    userImageUrl: String,
    subText: String? = null,
    cardHeight: Dp = 150.dp,
    cardElevation: Dp = 6.dp,
    cardBackgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    cardBorderRadius: Dp = 6.dp,
    textColor: Color = Color.Unspecified,
    subTextColor: Color = Color.Unspecified,
    profileImageClipShape: Shape = CircleShape,
    onProfileImageClick: (() -> Unit)? = null,
    onEditDetailsClick: (() -> Unit)? = null,
) {
    Card(
        modifier = modifier
            .height(cardHeight),
        elevation = CardDefaults.cardElevation(defaultElevation = cardElevation),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
        shape = RoundedCornerShape(cardBorderRadius)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 0.dp, top = 8.dp, bottom = 8.dp, end = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight()
                    .run {
                        onProfileImageClick?.let { this.clickable { onProfileImageClick() } }
                            ?: this
                    }
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .clip(profileImageClipShape)
                        .fillMaxWidth(0.7f)
                        .align(Alignment.Center),
                    model = userImageUrl,
                    contentScale = ContentScale.Inside,
                    loading = {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }
                        }
                    },
                    contentDescription = userName
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxSize()
                    .padding(top = 24.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier) {
                    Column(modifier = Modifier) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "$userName 님",
                            color = textColor,
                            fontSize = 24.sp,
                        )
                        subText?.let {
                            Text(
                                text = subText,
                                color = subTextColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
                onEditDetailsClick?.let {
                    Text(
                        modifier = Modifier
                            .clickable { onEditDetailsClick() }
                            .align(Alignment.End),
                        text = ">> 내 정보 수정하기",
                        color = textColor
                    )
                }
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
            subText = "나는 테스트유저라고 해 만나서 반가워 가나다라마바사",
            userImageUrl = "https://picsum.photos/1200"
        )

    }
}