/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
