package com.wahidabd.library.utils.rx.paging

import androidx.paging.PagingSource


/**
 * Created by Wahid on 4/6/2023.
 * Github github.com/wahidabd.
 */


fun<T : Any> apiToLoadResult(data: List<T>, position: Int): PagingSource.LoadResult<Int, T> =
    PagingSource.LoadResult.Page(
        data = data,
        prevKey = if (position == 1) null else position - 1,
        nextKey = if (data.isEmpty()) null else position + 1
    )