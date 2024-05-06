package com.example.movieslistapp.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.movieslistapp.R
import com.example.movieslistapp.data.createMoviesList
import com.example.movieslistapp.models.Movie
import com.example.movieslistapp.ui.views.AppScreens
import com.example.movieslistapp.ui.views.MovieListUiState
import com.example.movieslistapp.ui.views.InsertMovieUiState
import com.example.movieslistapp.ui.views.MainScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MovieListViewModel : ViewModel() {

    private val _movieListUiState: MutableStateFlow<MovieListUiState> =
        MutableStateFlow(MovieListUiState(createMoviesList()))

    val movieListUiState: StateFlow<MovieListUiState> =
        _movieListUiState.asStateFlow()

    private val _insertMovieUiState: MutableStateFlow<InsertMovieUiState> =
        MutableStateFlow(InsertMovieUiState())

    val insertMovieUiState: StateFlow<InsertMovieUiState> =
        _insertMovieUiState.asStateFlow()

    private val _mainScreenUiState: MutableStateFlow<MainScreenUiState> =
        MutableStateFlow(
            MainScreenUiState(
                "Movies List",
                R.drawable.add,
                "Insert Movie"
            )
        )

    val mainScreenUiState: StateFlow<MainScreenUiState> =
        _mainScreenUiState.asStateFlow()

    private var editMovie: Boolean = false
    private var movieToEdit: Movie = Movie(
        R.drawable.other,
        "",
        "",
        false,
        0
    )

    fun fabAction(navController: NavController) {
        if (_mainScreenUiState.value.screenName == "Movies List") {
            _mainScreenUiState.update { currentState ->
                currentState.copy(
                    screenName = "Insert Movie",
                    icon = R.drawable.save,
                    iconContentDescription = "Confirm"
                )
            }
            navController.navigate(AppScreens.InsertMovie.name)
        } else {
            if (editMovie) {
                val allMovies = _movieListUiState.value.movieList.toMutableList()
                val pos = allMovies.indexOf(movieToEdit)
                allMovies.remove(movieToEdit)
                allMovies.add(
                    pos, movieToEdit.copy(
                        pic = _insertMovieUiState.value.pic,
                        name = _insertMovieUiState.value.name,
                        description = _insertMovieUiState.value.description,
                        watched = _insertMovieUiState.value.watched,
                        rating = _insertMovieUiState.value.rating
                    )
                )
                _movieListUiState.update { currentState ->
                    currentState.copy(
                        movieList = allMovies.toList()
                    )
                }
                editMovie = false
                movieToEdit = Movie(R.drawable.other, "", "", false, 0)
            } else {
                _movieListUiState.update { currentState ->
                    currentState.copy(
                        movieList = currentState.movieList + Movie(
                            _insertMovieUiState.value.pic,
                            _insertMovieUiState.value.name,
                            _insertMovieUiState.value.description,
                            _insertMovieUiState.value.watched,
                            _insertMovieUiState.value.rating
                        )
                    )
                }
            }
            _insertMovieUiState.update {
                InsertMovieUiState()
            }
            _mainScreenUiState.update { currentState ->
                currentState.copy(
                    screenName = "Movies List",
                    icon = R.drawable.add,
                    iconContentDescription = "Insert Movie"
                )
            }
            navController.navigate(AppScreens.MovieList.name) {
                popUpTo(AppScreens.MovieList.name) {
                    inclusive = true
                }
            }
        }
    }

    fun navigateBack(navController: NavController) {
        editMovie = false
        movieToEdit = Movie(R.drawable.other, "", "", false, 0)
        _insertMovieUiState.update { InsertMovieUiState() }
        _mainScreenUiState.update {
            MainScreenUiState(
                screenName = "Movies List",
                icon = R.drawable.add,
                iconContentDescription = "Insert Movie"
            )
        }
        navController.popBackStack()
    }

    fun updateMovie(movie: Movie, navController: NavController) {
        editMovie = true
        movieToEdit = movie
        _insertMovieUiState.update { currentState->
            currentState.copy(
                pic = movie.pic,
                name = movie.name,
                description = movie.description,
                watched = movie.watched,
                rating = movie.rating
            )
        }
        _mainScreenUiState.update {
            MainScreenUiState(
                screenName = "Edit Movie",
                icon = R.drawable.save,
                iconContentDescription = "Confirm"
            )
        }
        navController.navigate(AppScreens.InsertMovie.name)
    }

    fun updatePic(@DrawableRes newPic: Int) {
        _insertMovieUiState.value = _insertMovieUiState.value.copy(
            pic = newPic
        )
    }

    fun updateName(newName: String) {
        _insertMovieUiState.value = _insertMovieUiState.value.copy(
            name = newName
        )
    }

    fun updateDescription(newDescription: String) {
        _insertMovieUiState.value = _insertMovieUiState.value.copy(
            description = newDescription
        )
    }

    fun updateWatched(newWatched: Boolean) {
        _insertMovieUiState.value = _insertMovieUiState.value.copy(
            watched = newWatched
        )
    }

    fun updateRating(newRating: Int) {
        _insertMovieUiState.value = _insertMovieUiState.value.copy(
            rating = newRating
        )
    }

    fun deleteMovie(movie: Movie) {
        val movies = _movieListUiState.value.movieList.toMutableList()
        movies.remove(movie)
        _movieListUiState.value = _movieListUiState.value.copy(
            movieList = movies.toList()
        )
    }
}