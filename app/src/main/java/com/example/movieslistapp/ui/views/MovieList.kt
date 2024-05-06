package com.example.movieslistapp.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieslistapp.R
import com.example.movieslistapp.models.Movie
import com.example.movieslistapp.ui.theme.MoviesListAppTheme
import com.example.movieslistapp.viewmodel.MovieListViewModel

@Composable
fun MovieList(
    viewModel: MovieListViewModel,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val uiState by viewModel.movieListUiState.collectAsState()
    LazyColumn {
        items(uiState.movieList) { movie ->
            MovieCard(
                movie = movie,
                onDelete = viewModel::deleteMovie,
                onEditMovie = {
                    viewModel.updateMovie(
                        movie = movie,
                        navController = navController
                    )
                })
        }
    }
}

@Composable
fun MovieCard(
    movie: Movie,
    onDelete: (Movie) -> Unit,
    onEditMovie: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier

            .fillMaxWidth()
            .padding(2.dp)
            .clickable {
                onEditMovie()
            }
    ) {

        Row (
            modifier = modifier
                .fillMaxWidth()
                .background(
                    if (movie.watched) Color.Green else Color.Transparent
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = modifier
                    .size(50.dp)
                    .padding(start = 4.dp, end = 4.dp),
                painter = painterResource(id = movie.pic),
                contentDescription = null,

                )
            Column {
                Text(
                    modifier = modifier.width(300.dp),
                        text = movie.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                Text(
                    modifier = modifier.width(300.dp),
                    text = movie.description,
                    fontStyle = FontStyle.Italic,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = modifier.weight(1F))
            Column{
                Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.Top
                    ) {
                    if(movie.rating > 0){
                        Image(
                            modifier = modifier
                                .size(20.dp)
                                .padding(top = 4.dp)
                                .align(Alignment.Top),
                            painter = painterResource(id = R.drawable.baseline_star_rate_24),
                            contentDescription = null
                        )
                        Text(
                            text = movie.rating.toString(),
                            fontWeight = FontWeight.Bold,
                            modifier = modifier
                                .size(20.dp)
                                .padding(top = 1.dp)
                                .align(Alignment.Top)
                        )
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "delete",
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(4.dp)
                        .clickable { onDelete(movie) }
                )
                }



        }

    }
}
@Preview(showBackground = true)
@Composable
fun CardPreview() {
    MoviesListAppTheme {
        MovieList(viewModel(), navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    MoviesListAppTheme {
        MovieCard(
            movie = Movie(
                R.drawable.action,
                "The Godfather",
                "An epic saga of the Corleone family, a Sicilian Mafia clan in New York City.",
                watched = true,
                rating = 1
            ),
            {}, {}
        )
    }
}