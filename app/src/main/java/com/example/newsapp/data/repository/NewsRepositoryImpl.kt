package com.example.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.remote.NewsSearchPagingSource
import com.example.newsapp.data.remote.dto.NewsAPI
import com.example.newsapp.data.remote.dto.NewsPagingSource
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.presentation.onboarding.pages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl(
    private val newsAPI: NewsAPI,
    private val newsDao: NewsDao
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsAPI = newsAPI,
                    sources = sources.joinToString(separator = ",")
                )
            }

        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsSearchPagingSource(
                    searchQuery = searchQuery,
                    newsAPI = newsAPI,
                    sources = sources.joinToString(separator = ",")
                )
            }

        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }
}