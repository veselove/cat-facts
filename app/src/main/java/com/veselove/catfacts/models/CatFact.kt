package com.veselove.catfacts.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "facts"
)

data class CatFact(
    @PrimaryKey(autoGenerate = false)
    val fact: String,
    val length: Int
) : Serializable