package com.lebogang.superheros.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* Entity class which represents a table in the database.
* We just have a simple character list adapter_view_item with a tvName and a image url field.
* */
@Entity(tableName = "character_items")
 class SuperHeroEntity {

        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
        var name: String? = null
        var url: String? = null

        constructor()

        constructor(description: String, priority: String) {
                this.name = description
                this.url = priority
        }
}
