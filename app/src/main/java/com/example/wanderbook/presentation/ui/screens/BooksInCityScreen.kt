package com.example.wanderbook.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.wanderbook.R
import com.example.wanderbook.navigation.NavRoutes
import com.example.wanderbook.presentation.viewmodel.BooksViewModel
import com.example.wanderbook.presentation.ui.theme.Blue
import com.example.wanderbook.presentation.ui.theme.Geologica
import com.example.wanderbook.presentation.viewmodel.BooksViewModelFactory
import com.example.wanderbook.data.local.AppDatabase


@Composable
fun BooksInCityScreen(navController: NavHostController) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val viewModel: BooksViewModel = viewModel(
        factory = BooksViewModelFactory(db.bookDao())
    )

    val books = viewModel.books
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(10.dp))
            Icon(
                Icons.Filled.Search,
                contentDescription = "icon",
                modifier = Modifier.size(45.dp).padding(top = 5.dp)
            )
            BasicTextField(
                value = viewModel.searchText,
                onValueChange = viewModel::onSearchTextChange,
                textStyle = TextStyle(
                    color = Color.Blue,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp),
                decorationBox = { innerTextField ->
                    Box {
                        if (viewModel.searchText.isEmpty()) {
                            Text(
                                text = "Поиск по названию или автору",
                                color = Blue,
                                fontSize = 16.sp,
                                style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Light),
                            )
                        }
                        innerTextField()
                    }
                }
            )
            Icon(
                Icons.Filled.Email,
                contentDescription = "icon",
                modifier = Modifier.size(45.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.size(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Новое",
                color = Blue,
                fontSize = 35.sp,
                style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Normal)
            )
            Icon(
                Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "icon",
                modifier = Modifier.size(35.dp)
            )
        }
        Text(text = "Новинки для обмена",
            color = Blue,
            fontSize = 20.sp,
            style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Light),
            modifier = Modifier.padding(start = 15.dp),

            )
        Spacer(modifier = Modifier.size(5.dp))
        LazyRow(modifier = Modifier.fillMaxWidth().padding(start = 15.dp)) {
            items(books) { book ->
                Card(
                    shape = RoundedCornerShape(5.dp),
                    elevation = CardDefaults.elevatedCardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.padding(end = 8.dp).clickable {
                        navController.navigate(NavRoutes.BookDetails.route + "/${book.id}")
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.book2),
                        contentDescription = "Book Cover",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(128.dp, 200.dp)
                            .clip(RoundedCornerShape(5.dp))
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Популярное",
                color = Blue,
                fontSize = 35.sp,
                style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Normal)
            )
            Icon(
                Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "icon",
                modifier = Modifier.size(35.dp),
                tint = Blue
            )
        }
        Text(text = "Самые просматриваемые книги",
            color = Blue,
            fontSize = 20.sp,
            style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Light),
            modifier = Modifier.padding(start = 15.dp)
        )
        Spacer(modifier = Modifier.size(5.dp))
        LazyRow(modifier = Modifier.fillMaxWidth().padding(start = 15.dp)) {
            items(books) { book ->
                Card(
                    shape = RoundedCornerShape(5.dp),
                    elevation = CardDefaults.elevatedCardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.padding(end = 8.dp).clickable {
                        navController.navigate(NavRoutes.BookDetails.route + "/${book.id}")
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.book3),
                        contentDescription = "Book Cover",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(128.dp, 200.dp)
                            .clip(RoundedCornerShape(5.dp))
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Книги в Саратове",
                color = Blue,
                fontSize = 35.sp,
                style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Normal)
            )
            Icon(
                Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "icon",
                modifier = Modifier.size(35.dp)
            )
        }
        Text(text = "Доступные для обмена книги",
            color = Blue,
            fontSize = 20.sp,
            style = TextStyle(fontFamily = Geologica, fontWeight = FontWeight.Light),
            modifier = Modifier.padding(start = 15.dp)
        )
        Spacer(modifier = Modifier.size(5.dp))
        LazyRow(modifier = Modifier.fillMaxWidth().padding(start = 15.dp)) {
            items(books) { book ->
                Card(
                    shape = RoundedCornerShape(5.dp),
                    elevation = CardDefaults.elevatedCardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.padding(end = 8.dp).clickable {
                        navController.navigate(NavRoutes.BookDetails.route + "/${book.id}")
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.book4),
                        contentDescription = "Book Cover",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(128.dp, 200.dp)
                            .clip(RoundedCornerShape(5.dp))
                    )
                }
            }
        }
    }
}




