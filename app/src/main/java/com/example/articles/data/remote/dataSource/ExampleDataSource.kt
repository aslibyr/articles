package com.example.articles.data.remote.dataSource

import com.example.articles.data.remote.webservice.WebService
import javax.inject.Inject

class ExampleDataSource @Inject constructor(
    private val webService: WebService
) {
}