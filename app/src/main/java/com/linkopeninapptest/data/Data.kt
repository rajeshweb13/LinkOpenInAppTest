package com.linkopeninapptest.data

data class Data(
    val overall_url_chart: HashMap<String,Int>,
    val recent_links: List<RecentLink>,
    val top_links: List<TopLink>
)