package com.example.movieslistapp.ui.views

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieslistapp.R
import com.example.movieslistapp.viewmodel.MovieListViewModel

@Composable
fun InsertMovie(
    viewModel: MovieListViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.insertMovieUiState.collectAsState()
    BackHandler {
        viewModel.navigateBack(navController = navController)
    }
    InsertForm(
        pic = uiState.pic,
        name = uiState.name,
        description = uiState.description,
        watched = uiState.watched,
        rating = uiState.rating,
        onUpdatePic = viewModel::updatePic,
        onUpdateName = viewModel::updateName,
        onUpdateDescription = viewModel::updateDescription,
        onUpdateWatched = viewModel::updateWatched,
        onUpdateRating = viewModel::updateRating,
        modifier = Modifier,
    )
}

@Composable
fun InsertForm(
    @DrawableRes pic: Int,
    name: String,
    description: String,
    watched: Boolean = false,
    rating: Int = 0,
    onUpdatePic: (Int) -> Unit,
    onUpdateDescription: (String) -> Unit,
    onUpdateName: (String) -> Unit,
    onUpdateWatched: (Boolean) -> Unit,
    onUpdateRating: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val imageList = listOf(
        R.drawable.action,
        R.drawable.science_fiction,
        R.drawable.romance,
        R.drawable.thriller,
        R.drawable.horror,
        R.drawable.other
    )
    Column(modifier = modifier
        .padding(16.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier.fillMaxWidth()
        ) {
            items(imageList) { image ->
                Box(modifier = modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .background(if (image == pic) Color.LightGray else Color.Transparent)
                ){
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = modifier
                            .size(100.dp)
                            .clickable {
                                onUpdatePic(image)
                            }
                    )
                }
            }
        }

        Spacer(modifier = modifier.height(8.dp))

        TextField(
            value = name,
            onValueChange = onUpdateName,
            label = { Text("Name") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = modifier.height(8.dp))
        TextField(
            value = description,
            onValueChange = onUpdateDescription,
            label = { Text("Description") },
            singleLine = false,
            minLines = 1,
            maxLines = 3,
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.height(8.dp))

        Row(modifier = modifier.fillMaxWidth()) {
            Checkbox(
                checked = watched,
                onCheckedChange = onUpdateWatched,
                modifier = modifier.padding(end = 8.dp)
            )
            Text("Watched", modifier = modifier.padding(top = 12.dp))
        }

        Spacer(modifier = modifier.height(8.dp))

        Row(modifier = modifier.fillMaxWidth()) {
            Rating(
                ratingInicial = rating,
                onRatingChange = onUpdateRating,
                modifier = modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun Rating(
    ratingInicial: Int,
    onRatingChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val starCount = 5
    var rating by remember { mutableStateOf(ratingInicial) }

    Row(modifier = modifier) {
        repeat(starCount) { index ->
            val modifier = Modifier
                .padding(4.dp)
                .clickable {
                    val newRating = (index + 1).coerceAtMost(starCount)
                    rating = newRating
                    onRatingChange(newRating)
                }
            Icon(
                painter = painterResource(id = if (rating > index) R.drawable.baseline_star_rate_24 else R.drawable.baseline_star_border_24),
                contentDescription = null,
                modifier = modifier
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun InsertFormPreview() {
    InsertForm(
        pic = R.drawable.other,
        name = "",
        description = "",
        onUpdatePic = {},
        onUpdateDescription = {},
        onUpdateName ={},
        onUpdateWatched = {},
        onUpdateRating = {}
    )
}