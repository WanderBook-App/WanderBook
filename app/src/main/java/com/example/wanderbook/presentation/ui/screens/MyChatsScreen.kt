package com.example.wanderbook.presentation.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wanderbook.R
import com.example.wanderbook.data.local.AppDatabase
import com.example.wanderbook.presentation.ui.theme.AnotherBlue
import com.example.wanderbook.presentation.ui.theme.Blue
import com.example.wanderbook.presentation.ui.theme.Geologica
import com.example.wanderbook.presentation.viewmodel.BooksViewModel
import com.example.wanderbook.presentation.viewmodel.BooksViewModelFactory

@OptIn(ExperimentalAnimationApi::class)
@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_7_pro")
@Composable
fun MyChatsScreen() {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val viewModel: BooksViewModel = viewModel(
        factory = BooksViewModelFactory(db.bookDao())
    )
    val chats = listOf("Дмитрий", "Лена", "Лалала")
    Column(modifier = Modifier.padding(15.dp)) {
        Text(
            text = "Чаты",
            color = Blue,
            fontSize = 40.sp,
            style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Medium)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            items(chats) { chat ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(bottom = 10.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(3.dp, AnotherBlue)
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(modifier = Modifier.padding(start = 10.dp)) {
                        Image(
                            painter = painterResource(R.drawable.avatar),
                            contentDescription = "avatar",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .border(1.dp, Color.Black, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(end = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text(
                                    text = chat,
                                    color = Blue,
                                    fontSize = 25.sp,
                                    style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Normal)
                                )
                                Text(
                                    text = "Война и мир",
                                    color = Blue,
                                    fontSize = 20.sp,
                                    style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Normal)
                                )
                            }
                            Spacer(modifier = Modifier.height(7.dp))
                            Text(
                                text = "Ты так как?",
                                color = Blue,
                                fontSize = 20.sp,
                                style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Thin)
                            )

                        }
                    }
                }
            }
        }
    }
}

