package com.anushka.tmdbclient.data.repository.movie

import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.domain.repository.MovieRepository

class FakeMovieRepository : MovieRepository{
    val movies = mutableListOf<Movie>()

    init {
        movies.add(Movie(1, "str1", "path1","date 1", "titile1"))
        movies.add(Movie(2, "str2", "path2","date 2", "titile2"))
        movies.add(Movie(3, "str3", "path3","date 3", "titile3"))
    }


    override suspend fun getMovies(): List<Movie>? {
        return movies
    }

    override suspend fun updateMovies(): List<Movie>? {
        movies.clear()
        movies.add(Movie(4, "str4", "path4","date 4", "titile4"))
        movies.add(Movie(5, "str5", "path5","date 5", "titile5"))
        movies.add(Movie(6, "str6", "path6","date 6", "titile6"))
        return movies
    }
}