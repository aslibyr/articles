package com.example.articles.data.remote.repository

import com.example.articles.data.remote.dataSource.ExampleDataSource
import javax.inject.Inject

class ExampleRepository @Inject constructor(
    private val exampleDataSource: ExampleDataSource
) {
}