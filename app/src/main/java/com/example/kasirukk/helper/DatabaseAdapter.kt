package com.example.kasirukk.helper

import app.cash.sqldelight.ColumnAdapter
import korlibs.time.DateTime

val dateAdapter = object : ColumnAdapter<DateTime, Long> {
    override fun decode(databaseValue: Long): DateTime = DateTime(databaseValue)
    override fun encode(value: DateTime): Long = value.unixMillisLong
}