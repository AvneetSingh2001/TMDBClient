package com.anushka.tmdbclient.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.data.repository.movie.FakeMovieRepository
import com.anushka.tmdbclient.domain.usecase.GetMoviesUseCase
import com.anushka.tmdbclient.domain.usecase.UpdateMoviesUsecase
import com.anushka.tmdbclient.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        val repository = FakeMovieRepository()
        val getMoviesUseCase = GetMoviesUseCase(repository)
        val updateMoviesUsecase = UpdateMoviesUsecase(repository)

        viewModel = MovieViewModel(getMoviesUseCase, updateMoviesUsecase)
    }

    @Test
    fun getMovie_returnCurrentList() {
        val movies = mutableListOf<Movie>()
        movies.add(Movie(1, "str1", "path1","date 1", "titile1"))
        movies.add(Movie(2, "str2", "path2","date 2", "titile2"))
        movies.add(Movie(3, "str3", "path3","date 3", "titile3"))
        val curList = viewModel.getMovies().getOrAwaitValue()
        assertThat(curList).isEqualTo(movies)
    }

    @Test
    fun updateMovie_returnNewList() {
        val movies = mutableListOf<Movie>()
        movies.add(Movie(4, "str4", "path4","date 4", "titile4"))
        movies.add(Movie(5, "str5", "path5","date 5", "titile5"))
        movies.add(Movie(6, "str6", "path6","date 6", "titile6"))
        val curList = viewModel.updateMovies().getOrAwaitValue()
        assertThat(curList).isEqualTo(movies)
    }
}