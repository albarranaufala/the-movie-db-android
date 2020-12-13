package com.example.screenplay.data.source.remote.retrofit

import com.example.screenplay.data.source.remote.retrofit.response.MovieDetailResponse
import com.example.screenplay.data.source.remote.retrofit.response.MovieListResponse
import com.example.screenplay.data.source.remote.retrofit.response.TvShowDetailResponse
import com.example.screenplay.data.source.remote.retrofit.response.TvShowListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface APIService {
    @GET("movie/top_rated")
    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMmIyMWM1MTczZTM0NmU1MjJiMWRiZWZlYjc0Yjk3OSIsInN1YiI6IjVmMmRmN2VjNTU0MWZhMDAzM2M5NzZhYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7ZJZEHb52TVjtN75W7-smdGuniIj0GzEDrMx4E661tc",
        "Content-Type: application/json;charset=utf-8"
    )
    fun getMovies(): Call<MovieListResponse>

    @GET("movie/{id}")
    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMmIyMWM1MTczZTM0NmU1MjJiMWRiZWZlYjc0Yjk3OSIsInN1YiI6IjVmMmRmN2VjNTU0MWZhMDAzM2M5NzZhYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7ZJZEHb52TVjtN75W7-smdGuniIj0GzEDrMx4E661tc",
        "Content-Type: application/json;charset=utf-8"
    )
    fun getMovieDetail(@Path("id") id: Int): Call<MovieDetailResponse>

    @GET("tv/top_rated")
    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMmIyMWM1MTczZTM0NmU1MjJiMWRiZWZlYjc0Yjk3OSIsInN1YiI6IjVmMmRmN2VjNTU0MWZhMDAzM2M5NzZhYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7ZJZEHb52TVjtN75W7-smdGuniIj0GzEDrMx4E661tc",
        "Content-Type: application/json;charset=utf-8"
    )
    fun getTvShows(): Call<TvShowListResponse>

    @GET("tv/{id}")
    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMmIyMWM1MTczZTM0NmU1MjJiMWRiZWZlYjc0Yjk3OSIsInN1YiI6IjVmMmRmN2VjNTU0MWZhMDAzM2M5NzZhYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7ZJZEHb52TVjtN75W7-smdGuniIj0GzEDrMx4E661tc",
        "Content-Type: application/json;charset=utf-8"
    )
    fun getTvShowDetail(@Path("id") id: Int): Call<TvShowDetailResponse>
}