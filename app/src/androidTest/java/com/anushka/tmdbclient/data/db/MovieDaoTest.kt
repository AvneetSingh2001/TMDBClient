package com.anushka.tmdbclient.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Database
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anushka.tmdbclient.data.model.movie.Movie
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    private lateinit var database: TMDBDatabase
    private lateinit var movieDao: MovieDao


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), TMDBDatabase::class.java).build()
        movieDao = database.movieDao()
    }

    fun saveMoviesTest() = runBlocking {
        val movies : List<Movie> = listOf(
            Movie(1, "str1", "path1","date 1", "titile1"),
            Movie(2, "str2", "path2","date 2", "titile2"),
            Movie(3, "str3", "path3","date 3", "titile3")
        )
        movieDao.saveMovies(movies)
        val allMovies = movieDao.getMovies()
        Truth.assertThat(movies).isEqualTo(allMovies)
    }

    @Test
    fun deleteMovieTest() = runBlocking{
        val movies : List<Movie> = listOf(
            Movie(1, "str1", "path1","date 1", "titile1"),
            Movie(2, "str2", "path2","date 2", "titile2"),
            Movie(3, "str3", "path3","date 3", "titile3")
        )
        movieDao.saveMovies(movies)
        val allMovies = movieDao.getMovies()
        Truth.assertThat(allMovies).isEmpty()

    }

    @After
    fun tearDown() {
        database.close()
    }
}