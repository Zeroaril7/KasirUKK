package com.example.kasirukk.feature.manager.report.model

import korlibs.time.DateTime

data class FilterModel(
    val created_at: DateTime,
    val buyer_name: String,
    val is_paid: Boolean,
    val id_meja: Long,
)
