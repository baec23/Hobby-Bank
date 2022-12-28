package com.baec23.hobbybank.ui.main.tosscopy

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baec23.hobbybank.R

data class BenefitListEntry(
    val text1: String,
    val text2: String? = null,
    val text3: String,
    val iconImageVector: ImageVector,
    val backgroundColor: Color
)

private val backgroundColor = Color(0xFF18171d)
private val darkGray = Color(0xFF86858b)
private val lightGray = Color(0xFFc7c6cb)
private val blue = Color(0xFF3b85f2)
private val circleBackgroundColor = Color(0xFF201f27)

@Composable
fun BenefitsScreen() {
    var myPointsValue by remember { mutableStateOf(1000) }
    var benefitListEntries by remember {
        mutableStateOf(
            listOf(
                BenefitListEntry(
                    text1 = "숨겨진 보혐 내역 찾아보세요",
                    text2 = "내 명의 보험 한번에 모아보기",
                    text3 = "3초 기다려서 3원 받았어요",
                    iconImageVector = Icons.Default.Star,
                    backgroundColor = darkGray
                ),
                BenefitListEntry(
                    text1 = "만보기",
                    text3 = "140원 받기",
                    iconImageVector = Icons.Default.AddCircle,
                    backgroundColor = darkGray
                ),
                BenefitListEntry(
                    text1 = "이번 주 미션하면",
                    text3 = "얼마 받을지 보기",
                    iconImageVector = Icons.Default.Lock,
                    backgroundColor = darkGray
                ),
                BenefitListEntry(
                    text1 = "오늘의 행운복권",
                    text3 = "포인트 받기",
                    iconImageVector = Icons.Default.Call,
                    backgroundColor = darkGray
                ),
            )
        )
    }

    Column(
        modifier = Modifier.padding(20.dp),
    ) {
        Text(text = "혜택", fontSize = 25.sp, color = Color.White, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(40.dp))
        LabelledValueButton(label = "내 포인트", value = "${myPointsValue}원") {
        }
        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "혜택 더 받기", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(25.dp))
        Column(verticalArrangement = Arrangement.spacedBy(25.dp)) {
            benefitListEntries.forEach { listEntry ->
                BenefitCard(
                    text1 = listEntry.text1,
                    text2 = listEntry.text2,
                    text3 = listEntry.text3,
                    imageBackgroundColor = listEntry.backgroundColor
                ) {
                    Icon(
                        modifier = Modifier.scale(2f),
                        imageVector = listEntry.iconImageVector,
                        contentDescription = listEntry.text1,
                        tint = Color(0xFFee3b24)
                    )
                }
            }
        }

    }
}

@Preview("three lines")
@Composable
fun BenefitsScreenPreview() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = backgroundColor
    ) {
        BenefitsScreen()
    }
}

@Composable
fun LabelledValueButton(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onClick: () -> Unit,
) {

    Row(
        modifier = modifier.clickable {
            onClick()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.weight(2f), text = label, style = MaterialTheme.typography.labelMedium)
        Text(
            modifier = Modifier.weight(1f),
            text = value,
            textAlign = TextAlign.End,
            color = lightGray,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.width(5.dp))
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = R.drawable.chevron_right),
            contentDescription = label,
            tint = darkGray
        )
    }
}

@Preview("two lines")
@Composable
fun BenefitsScreenPreview2() {

    BenefitCard(
        modifier = Modifier.fillMaxWidth(),
        text1 = "만보기",
        text3 = "140원 받기"
    ) {
        Icon(imageVector = Icons.Filled.AddCircle, contentDescription = null)
    }
}

@Composable
fun BenefitCard(
    modifier: Modifier = Modifier,
    text1: String,
    text2: String? = null,
    text3: String,
    imageBackgroundColor: Color = darkGray,
    image: @Composable () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .aspectRatio(ratio = 1f, matchHeightConstraintsFirst = false),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(circleBackgroundColor),
                contentAlignment = Alignment.Center
            ){
                image()
            }
        }
        
        Spacer(modifier = Modifier.width(10.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(text = text1, color = darkGray, fontSize = 12.sp)
            text2?.let {
                Text(
                    text = text2,
                    color = lightGray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                text = text3,
                fontSize = if (text2 == null) 15.sp else 12.sp,
                fontWeight = if (text2 == null) FontWeight.Bold else null,
                color = blue
            )
        }
    }
}