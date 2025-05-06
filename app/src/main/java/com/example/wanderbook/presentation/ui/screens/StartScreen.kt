package com.example.wanderbook.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wanderbook.R
import com.example.wanderbook.presentation.ui.theme.AnotherBlue
import com.example.wanderbook.presentation.ui.theme.Blue
import com.example.wanderbook.presentation.ui.theme.Geologica
import com.example.wanderbook.presentation.viewmodel.StartViewModel
import kotlinx.coroutines.delay


@Composable
@Preview(device = "id:pixel_7_pro", showSystemUi = true, showBackground = true)
fun StartScreen(viewModel: StartViewModel = viewModel(), onLoginClick: () -> Unit = {}, onRegisterClick: () -> Unit = {}) {
    val isVisible by viewModel.isVisible
    val isRegistr by viewModel.isRegistr
    val isLogin by viewModel.isLogin

    val imageSize by animateDpAsState(targetValue = if (isRegistr || isLogin) 200.dp else 320.dp)
    val textSize by animateFloatAsState(targetValue = if (isRegistr || isLogin) 30f else 45f)
    LaunchedEffect(Unit) {
        delay(2000)  // 1 секунда
        viewModel.setVisible()
    }
    val upAnimationOffset by animateIntOffsetAsState(targetValue = if (isVisible) IntOffset(0, 0) else IntOffset(0, 650))
    val heightAnimation by animateDpAsState(targetValue = if (isRegistr) 1000.dp else if (isLogin) 350.dp else 185.dp)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = textSize.sp,
            style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Medium),
            color = Blue
        )
        Image(
            painter = painterResource(id = R.drawable.logonoframes),
            contentDescription = "App Logo",
            modifier = Modifier.size(imageSize)
        )
        Spacer(modifier = Modifier.size(60.dp))
        Card(
            modifier = Modifier
                .offset { upAnimationOffset }
                .fillMaxWidth()
                .background(Color.White)
                .height(heightAnimation),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = AnotherBlue)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            AnimatedVisibility(!isLogin && !isRegistr) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { viewModel.onRegisterButtonClick() },
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
                        onClick = { viewModel.onLoginButtonClick() },
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
            AnimatedVisibility(isRegistr) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp, start = 20.dp, bottom = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = viewModel.name,
                        onValueChange = { viewModel.onNameChange(it) },
                        label = { Text("Имя") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedTextColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedLabelColor = Color.Gray,
                            focusedLabelColor = Color.Gray,
                            unfocusedBorderColor = Color.White,
                            focusedBorderColor = Color.White,
                            disabledBorderColor = Color.White,
                            errorBorderColor = Color.Red
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = viewModel.email,
                        onValueChange = { viewModel.onEmailChange(it) },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedTextColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedLabelColor = Color.Gray,
                            focusedLabelColor = Color.Gray,
                            unfocusedBorderColor = Color.White,
                            focusedBorderColor = Color.White,
                            disabledBorderColor = Color.White,
                            errorBorderColor = Color.Red
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = viewModel.password,
                        onValueChange = { viewModel.onPasswordChange(it) },
                        label = { Text("Пароль") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedTextColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedLabelColor = Color.Gray,
                            focusedLabelColor = Color.Gray,
                            unfocusedBorderColor = Color.White,
                            focusedBorderColor = Color.White,
                            disabledBorderColor = Color.White,
                            errorBorderColor = Color.Red
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = viewModel.repeatPassword,
                        onValueChange = { viewModel.onRepeatPasswordChange(it) },
                        label = { Text("Повторите пароль") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedTextColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedLabelColor = Color.Gray,
                            focusedLabelColor = Color.Gray,
                            unfocusedBorderColor = Color.White,
                            focusedBorderColor = Color.White,
                            disabledBorderColor = Color.White,
                            errorBorderColor = Color.Red
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            viewModel.register(viewModel.name, viewModel.email, viewModel.password) {}
                            onRegisterClick()
                                  },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(8.dp),
                        elevation = ButtonDefaults.buttonElevation(4.dp)
                    ) {
                        Text(
                            text = "Зарегистрироваться",
                            fontSize = 22.sp,
                            color = Blue
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            viewModel.onRegisterButtonClick()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(8.dp),
                        elevation = ButtonDefaults.buttonElevation(4.dp)
                    ) {
                        Text(
                            text = "Назад",
                            fontSize = 22.sp,
                            color = Blue
                        )
                    }
                }
            }
            AnimatedVisibility(isLogin) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp, start = 20.dp, bottom = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = viewModel.email,
                        onValueChange = { viewModel.onEmailChange(it) },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedTextColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedLabelColor = Color.Gray,
                            focusedLabelColor = Color.Gray,
                            unfocusedBorderColor = Color.White,
                            focusedBorderColor = Color.White,
                            disabledBorderColor = Color.White,
                            errorBorderColor = Color.Red
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = viewModel.password,
                        onValueChange = { viewModel.onPasswordChange(it) },
                        label = { Text(text = "Пароль") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedTextColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedLabelColor = Color.Gray,
                            focusedLabelColor = Color.Gray,
                            unfocusedBorderColor = Color.White,
                            focusedBorderColor = Color.White,
                            disabledBorderColor = Color.White,
                            errorBorderColor = Color.Red,

                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
//                        onClick = {
//                            viewModel.login(viewModel.email, viewModel.password) {}
//                            onLoginClick()
//                                  },
                        onClick = {
                            viewModel.login(viewModel.email, viewModel.password) { success ->
                                if (success) {
                                    onLoginClick()
                                }
//                                else {
//
//                                    Toast.makeText(context, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
//                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(8.dp),
                        elevation = ButtonDefaults.buttonElevation(4.dp)
                    ) {
                        Text(
                            text = "Войти",
                            fontSize = 22.sp,
                            color = Blue
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            viewModel.onLoginButtonClick()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(8.dp),
                        elevation = ButtonDefaults.buttonElevation(4.dp)
                    ) {
                        Text(
                            text = "Назад",
                            fontSize = 22.sp,
                            color = Blue
                        )
                    }
                }
            }
        }
    }
}