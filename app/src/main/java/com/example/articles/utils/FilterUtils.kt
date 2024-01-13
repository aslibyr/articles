package com.example.articles.utils


fun getCountryList() : List<TabItemModel> {
    return arrayListOf(
        TabItemModel(
            "Türkiye",
            "tr"
        ),
        TabItemModel(
            "Amerika",
            "us"
        ),
        TabItemModel(
            "İngiltere",
            "gb"
        ),
        TabItemModel(
            "Almanya",
            "de"
        ))
}

fun getTabList() : List<TabItemModel> {
    return arrayListOf(
        TabItemModel(
            "Genel",
            "general"
        ),
        TabItemModel(
            "Spor",
            "sports"
        ),
        TabItemModel(
            "Teknoloji",
            "technology"
        ),
        TabItemModel(
            "Sağlık",
            "health"
        ),
        TabItemModel(
            "Eğlence",
            "entertainment"
        ),
        TabItemModel(
            "Bilim",
            "science"
        ),
        TabItemModel(
            "Ticari",
            "business"
        ),

        )
}

data class TabItemModel(
    val title : String,
    val id : String
)