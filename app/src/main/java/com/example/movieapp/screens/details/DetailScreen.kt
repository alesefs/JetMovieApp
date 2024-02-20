package com.example.movieapp.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.widgets.MovieRow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun DetailsScreen(
    navController: NavController = NavController(LocalContext.current),
    movieId: String? = ""
) {

    val newMovieList = getMovies().filter {movie ->
        movie.id == movieId
    }

    //propriedades de tela e açoes de activities
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 2.dp,
                contentPadding = PaddingValues(all = 0.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )

                    Spacer(modifier = Modifier.width(100.dp))

                    Text(text = "Movies")
                }
            }
        },
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                MovieRow(movie = newMovieList.first())

                Spacer(modifier = Modifier.height(8.dp))

                Divider()

                Text(text = "Movie Images")

                HorizontalScrollableImageView(newMovieList)

                /*Text(
//                    text = "Movie: ${movieId.toString()}",
                    text = newMovieList[0].title,
                    style = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.height(23.dp))

                Button(onClick = {
//                navController.navigate(route = MovieScreens.HomeScreen.name)
                    navController.popBackStack()
                }) {
                    Text(
                        text = "< Go Back",
                        style = MaterialTheme.typography.button
                    )
                }*/
            }
        }
    }
}

@Composable
private fun HorizontalScrollableImageView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList[0].images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 5.dp
            ) {
                Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "Movie Poster"
                )
            }
        }
    }
}