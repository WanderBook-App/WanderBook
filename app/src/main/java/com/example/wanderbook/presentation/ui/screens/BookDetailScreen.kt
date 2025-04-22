package com.example.wanderbook.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.wanderbook.R
import com.example.wanderbook.data.local.AppDatabase
import com.example.wanderbook.presentation.viewmodel.BooksViewModel
import com.example.wanderbook.presentation.ui.theme.AnotherBlue
import com.example.wanderbook.presentation.ui.theme.Blue
import com.example.wanderbook.presentation.ui.theme.Geologica
import com.example.wanderbook.presentation.ui.theme.Gray
import com.example.wanderbook.presentation.viewmodel.BooksViewModelFactory

@Composable
fun BookDetailsScreen(bookId: Int, navController: NavHostController) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val viewModel: BooksViewModel = viewModel(
        factory = BooksViewModelFactory(db.bookDao())
    )
    val book = viewModel.getBookById(bookId.toString())
    book?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.size(15.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 15.dp)
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Назад",
                    modifier = Modifier
                        .size(35.dp)
                        .clickable { navController.popBackStack() },
                    tint = Blue
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.elevatedCardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.wrapContentSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.book1),
                        contentDescription = "Обложка книги",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(width = 200.dp, height = 300.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = book.title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = Geologica,
                fontWeight = FontWeight.Medium,
                fontSize = 30.sp,
                color = Blue
            )
            Text(
                text = book.author,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = Geologica,
                fontWeight = FontWeight.Light,
                fontSize = 20.sp,
                color = Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Жанр",
                        fontWeight = FontWeight.Normal,
                        fontFamily = Geologica,
                        color = Gray,
                        fontSize = 22.sp
                    )
                    Text(
                        text = it.genre,
                        fontWeight = FontWeight.Normal,
                        fontFamily = Geologica,
                        color = Blue,
                        fontSize = 22.sp
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Состояние",
                        fontWeight = FontWeight.Normal,
                        fontFamily = Geologica,
                        color = Gray,
                        fontSize = 22.sp
                    )
                    Text(
                        text = it.condition,
                        fontWeight = FontWeight.Normal,
                        fontFamily = Geologica,
                        color = Blue,
                        fontSize = 22.sp
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Буккроссер",
                        fontWeight = FontWeight.Normal,
                        fontFamily = Geologica,
                        color = Gray,
                        fontSize = 22.sp
                    )
                    Text(
                        text = "Дарья",
                        fontWeight = FontWeight.Normal,
                        fontFamily = Geologica,
                        color = Blue,
                        fontSize = 22.sp
                    )
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "О книге",
                modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                fontWeight = FontWeight.Medium,
                fontFamily = Geologica,
                color = Blue,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = book.description,
                modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                fontWeight = FontWeight.Light,
                fontFamily = Geologica,
                color = Gray,
                fontSize = 20.sp
            )
            Box(modifier = Modifier.fillMaxSize().padding(bottom = 10.dp), contentAlignment = Alignment.BottomCenter) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(15.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { /* Действие */ },
                        modifier = Modifier.weight(1f).height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = AnotherBlue),
                        shape = RoundedCornerShape(8.dp),
                        elevation = ButtonDefaults.buttonElevation(4.dp)
                    ) {
                        Text(
                            text = "Связаться",
                            fontSize = 22.sp,
                            style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Medium),
                            color = Color.White
                        )
                    }
                    Button(
                        onClick = { /* Действие */ },
                        modifier = Modifier.size(50.dp).border(2.dp, AnotherBlue, RoundedCornerShape(8.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(8.dp),
                        elevation = ButtonDefaults.buttonElevation(4.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.heart),
                            contentDescription = "Избранное",
                            modifier = Modifier.size(24.dp),
                            tint = AnotherBlue,
                        )
                    }

                }
            }
        }
    } ?: Text(text = "Книга не найдена", fontSize = 18.sp, color = Color.Red)
}

