package com.wahidabd.onelibrary.data.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wahidabd.onelibrary.data.movie.model.MovieResultResponse
import com.wahidabd.onelibrary.data.movie.remote.MovieApi
import retrofit2.HttpException
import java.io.IOException


/**
 * Created by wahid on 5/24/2024.
 * Github github.com/wahidabd.
 */


class MoviePagingDataStore(
    private val api: MovieApi,
) : PagingSource<Int, MovieResultResponse>() {
    override fun getRefreshKey(state: PagingState<Int, MovieResultResponse>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResultResponse> {
        return try {
            val nextPage = params.key ?: 1
            val response = api.getMovies(nextPage)

            LoadResult.Page(
                data = response.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (response.results.isEmpty()) null else nextPage + 1
            )

        }catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}