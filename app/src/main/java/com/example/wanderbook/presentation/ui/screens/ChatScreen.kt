package com.example.wanderbook.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wanderbook.presentation.ui.theme.Blue
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.wanderbook.presentation.ui.theme.AnotherBlue
import androidx.compose.material3.Divider
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.wanderbook.data.local.ChatDao
import com.example.wanderbook.data.local.MessageDao
import com.example.wanderbook.presentation.viewmodel.ChatsViewModel
import com.example.wanderbook.presentation.viewmodel.MessageViewModelFactory
import androidx.compose.runtime.LaunchedEffect



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavHostController,
    chatId: String,
    senderId: String, // ID текущего пользователя
    chatDao: ChatDao,
    messageDao: MessageDao
) {
    val viewModel: ChatsViewModel = viewModel(
        factory = MessageViewModelFactory(chatDao, messageDao)
    )
    // Загружаем сообщения для этого чата
    LaunchedEffect(chatId) {
        viewModel.loadMessagesForChat(chatId)
    }
    val messages by viewModel.messages.collectAsState()
    var text by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAF6FF)) // светло-голубой фон
    ) {
        // Верхняя панель
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Назад",
                    tint = Blue
                )
            }
            Text(
                text = "Чат с пользователем",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Blue
            )
        }

        Divider(color = AnotherBlue, thickness = 2.dp)

        // Список сообщений
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp),
            reverseLayout = true
        ) {
            items(messages.reversed()) { message ->
                val alignment = if (message.senderId == senderId) Alignment.CenterEnd else Alignment.CenterStart
                val background = if (message.senderId == senderId) Blue else Color.LightGray
                val textColor = if (message.senderId == senderId) Color.White else Color.Black


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    contentAlignment = alignment
                ) {
                    Text(
                        text = message.text,
                        color = textColor,
                        modifier = Modifier
                            .background(background, shape = RoundedCornerShape(12.dp))
                            .padding(10.dp)
                    )
                }
            }
        }

        // Поле ввода
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Введите сообщение") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                )
            )
            IconButton(onClick = {
                viewModel.sendMessage(chatId, senderId, text)
                text = ""
            }) {
                Icon(Icons.Default.Send, contentDescription = "Send", tint = Blue)
            }
        }
    }
}




