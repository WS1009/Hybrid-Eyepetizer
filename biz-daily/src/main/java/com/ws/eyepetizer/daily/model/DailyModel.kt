package com.ws.eyepetizer.daily.model

import com.ws.eyepetizer.provider.model.Issue

data class DailyModel(
    val dialog: Any,
    val issueList: List<Issue>,
    val newestIssueType: String,
    val nextPageUrl: String,
    val nextPublishTime: Long
)

