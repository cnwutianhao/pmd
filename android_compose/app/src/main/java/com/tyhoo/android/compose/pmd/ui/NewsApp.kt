package com.tyhoo.android.compose.pmd.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.tyhoo.android.compose.pmd.R
import com.tyhoo.android.compose.pmd.data.News
import com.tyhoo.android.compose.pmd.ui.theme.PlayMobileDevelopmentTheme
import com.tyhoo.android.compose.pmd.viewmodel.NewsViewModel

@Composable
fun NewsApp(newsViewModel: NewsViewModel) {
    PlayMobileDevelopmentTheme {
        RequestData(newsViewModel)
    }
}

@Composable
fun RequestData(newsViewModel: NewsViewModel) {
    val newsList = newsViewModel.newsList.collectAsState()
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(id = R.string.news_title)) })
    }) {
        LazyColumn {
            itemsIndexed(items = newsList.value.newsList) { _, news ->
                NewsItem(news = news)

                Divider(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                )
            }
        }
    }
}

@Composable
fun NewsItem(news: News) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = news.image),
            contentDescription = stringResource(id = R.string.news_item_image),
            Modifier
                .width(120.dp)
                .height(90.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = news.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = news.description,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun NewsItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = stringResource(id = R.string.news_item_image),
            Modifier
                .width(120.dp)
                .height(90.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = "Title",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Description",
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}