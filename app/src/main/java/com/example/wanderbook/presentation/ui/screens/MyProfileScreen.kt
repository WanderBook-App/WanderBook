package com.example.wanderbook.presentation.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wanderbook.R
import com.example.wanderbook.data.local.AppDatabase
import com.example.wanderbook.presentation.ui.theme.AnotherBlue
import com.example.wanderbook.presentation.ui.theme.Blue
import com.example.wanderbook.presentation.ui.theme.Geologica
import com.example.wanderbook.presentation.ui.theme.Gray
import com.example.wanderbook.presentation.ui.theme.Red
import com.example.wanderbook.presentation.ui.theme.Yellow
import com.example.wanderbook.presentation.viewmodel.BooksViewModel
import com.example.wanderbook.presentation.viewmodel.BooksViewModelFactory

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MyProfileScreen() {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val viewModel: BooksViewModel = viewModel(
        factory = BooksViewModelFactory(db.bookDao())
    )
    val showFavorites by viewModel.showFavorites
    val myBooks = viewModel.myBooks
    val favBooks = viewModel.favBooks
    val offsetX by animateDpAsState(
        targetValue = if (showFavorites) 50.dp else 0.dp,
        label = "switchOffset"
    )
    val coverMap1 = mapOf(
        "book1.jpg" to R.drawable.book1,
        "book2.jpg" to R.drawable.book2,
        "book3.jpg" to R.drawable.book3,
        "book4.jpg" to R.drawable.book4,
        "book5.jpg" to R.drawable.book5,
        "book6.jpg" to R.drawable.book6,
        "book7.jpg" to R.drawable.book7,
    )
    val coverMap2 = mapOf(
        "book1.jpg" to R.drawable.book1,
        "book2.jpg" to R.drawable.book2,
        "book3.jpg" to R.drawable.book3,
        "book4.jpg" to R.drawable.book4,
        "book5.jpg" to R.drawable.book5,
        "book6.jpg" to R.drawable.book6,
        "book7.jpg" to R.drawable.book7,
    )
    val titleText = if (showFavorites) "Избранное" else "Мои книги"
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            painter = painterResource(R.drawable.avatar),
            contentDescription = "avatar",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Black, CircleShape)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Дарья",
            color = Blue,
            fontSize = 40.sp,
            style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Medium)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyCard("56 книг", R.drawable.book)
            MyCard("с 2025 года", R.drawable.calendar)
            MyCard("Роман", R.drawable.star)
        }

        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedContent(
                    targetState = titleText,
                    transitionSpec = {
                        fadeIn(tween(300)) with fadeOut(tween(200))
                    },
                    label = "titleChange"
                ) { text ->
                    Text(
                        text = text,
                        color = Blue,
                        fontSize = 32.sp,
                        style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Medium)
                    )
                }
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp)
                        .clickable { viewModel.toggleFavorites() }
                        .padding(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(2.dp)
                            .background(Color.Gray.copy(alpha = 0.4f))
                            .align(Alignment.Center)
                    )
                    Box(
                        modifier = Modifier
                            .offset(x = offsetX)
                            .size(40.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .shadow(4.dp, RoundedCornerShape(6.dp))
                            .align(Alignment.CenterStart)
                            .background(if (!showFavorites) AnotherBlue else Red),
                        contentAlignment = Alignment.Center
                    ) {
                        if (showFavorites) {
                            Icon(
                                imageVector = Icons.Outlined.Favorite,
                                contentDescription = null,
                                tint = Color.White
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.book),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
            AnimatedContent(
                targetState = showFavorites,
                transitionSpec = {
                    fadeIn(tween(300)) with fadeOut(tween(300))
                },
                label = "booksSwitch"
            ) { isFavorites ->
                if (isFavorites) {
                    LazyColumn(
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp)
                    ) {
                        items(favBooks) { book ->
                            if (book.isFavorite) {
                                val imageRes = coverMap1[book.coverUrl] ?: R.drawable.book2
                                Row() {
                                    Card(
                                        shape = RoundedCornerShape(5.dp),
                                        elevation = CardDefaults.elevatedCardElevation(8.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White),
                                        modifier = Modifier.padding(end = 8.dp).clickable {
                                            //navController.navigate(NavRoutes.BookDetails.route + "/${book.id}")
                                        }
                                    ) {
                                        Image(
                                            painter = painterResource(id = imageRes),
                                            contentDescription = "Book Cover",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(96.dp, 150.dp)
                                                .clip(RoundedCornerShape(5.dp))
                                        )
                                    }
                                    Column(
                                        modifier = Modifier.height(150.dp)
                                    ) {
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(
                                            text = book.title,
                                            color = Blue,
                                            fontSize = 22.sp,
                                            style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Normal)
                                        )
                                        Spacer(modifier = Modifier.height(5.dp))
                                        Text(
                                            text = book.author,
                                            color = Gray,
                                            fontSize = 17.sp,
                                            style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Light)
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                        Box(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .clip(RoundedCornerShape(12.dp))
                                                .border(4.dp, Red, RoundedCornerShape(12.dp))
                                                .clickable { /* TODO: onClick */ }
                                                .background(Color.White),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "delete",
                                                tint = Red,
                                                modifier = Modifier.size(28.dp)
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(25.dp))
                            }
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        FloatingActionButton(
                            onClick = { /* TODO: onClick */ },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(16.dp),
                            containerColor = AnotherBlue
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = Color.White,
                                modifier = Modifier.size(45.dp)
                            )
                        }
                    }
                    LazyColumn(
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp)
                    ) {
                        items(myBooks.reversed()) { book ->
                            val imageRes = coverMap2[book.coverUrl] ?: R.drawable.book2
                            Row() {
                                Card(
                                    shape = RoundedCornerShape(5.dp),
                                    elevation = CardDefaults.elevatedCardElevation(8.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White),
                                    modifier = Modifier.padding(end = 8.dp).clickable {
                                        //navController.navigate(NavRoutes.BookDetails.route + "/${book.id}")
                                    }
                                ) {
                                    Image(
                                        painter = painterResource(id = imageRes),
                                        contentDescription = "Book Cover",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(96.dp, 150.dp)
                                            .clip(RoundedCornerShape(5.dp))
                                    )
                                }
                                Column(
                                    modifier = Modifier.height(150.dp)
                                ) {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = book.title,
                                        color = Blue,
                                        fontSize = 22.sp,
                                        style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Normal)
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(
                                        text = book.author,
                                        color = Gray,
                                        fontSize = 17.sp,
                                        style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Light)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Row() {
                                        Box(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .clip(RoundedCornerShape(12.dp))
                                                .border(4.dp, Yellow, RoundedCornerShape(12.dp))
                                                .clickable { /* TODO: onClick */ }
                                                .background(Color.White),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Edit,
                                                contentDescription = "lalala",
                                                tint = Yellow,
                                                modifier = Modifier.size(28.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Box(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .clip(RoundedCornerShape(12.dp))
                                                .border(4.dp, Red, RoundedCornerShape(12.dp))
                                                .clickable { /* TODO: onClick */ }
                                                .background(Color.White),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "lalala",
                                                tint = Red,
                                                modifier = Modifier.size(28.dp)
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(25.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyCard(text: String, im: Int) {
    Card(
        modifier = Modifier
            .size(130.dp, 90.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(4.dp, AnotherBlue)

    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(im),
                contentDescription = "icon",
                tint = Blue,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = text,
                color = Blue,
                fontSize = 20.sp,
                style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Normal),
                textAlign = TextAlign.Center
            )
        }
    }
}

