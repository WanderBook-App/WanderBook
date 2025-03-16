package com.example.wanderbook.presentation.ui.screens

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wanderbook.R
import com.example.wanderbook.ui.theme.AnotherBlue
import com.example.wanderbook.ui.theme.Blue
import com.example.wanderbook.ui.theme.Geologica
import kotlinx.coroutines.delay


@Composable
fun StartScreen(onLoginClick: () -> Unit, onRegisterClick: () -> Unit) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(2000)  // 1 секунда
        isVisible = true
    }
    val upAnimationOffset by animateIntOffsetAsState(targetValue = if (isVisible) IntOffset(0, 0) else IntOffset(0, 650) )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 45.sp,
            style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Medium),
            color = Blue
        )
        Image(
            painter = painterResource(id = R.drawable.logonoframes),
            contentDescription = "App Logo",
            modifier = Modifier.size(320.dp)
        )
        Spacer(modifier = Modifier.size(60.dp))
        Card(
            modifier = Modifier
                .offset { upAnimationOffset }
                .fillMaxWidth()
                .background(Color.White),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = AnotherBlue)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { onRegisterClick() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp),
                    elevation = ButtonDefaults.buttonElevation(4.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(3.dp),
                        text = "Регистрация",
                        fontSize = 28.sp,
                        style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Medium),
                        color = Blue
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { onLoginClick() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp),
                    elevation = ButtonDefaults.buttonElevation(4.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(3.dp),
                        text = "Вход",
                        fontSize = 28.sp,
                        style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Medium),
                        color = Blue
                    )
                }
            }
        }
    }
}
