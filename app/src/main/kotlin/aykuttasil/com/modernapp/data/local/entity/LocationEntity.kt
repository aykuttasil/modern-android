/**
 * Designed and developed by Aykut Asil (@aykuttasil)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package aykuttasil.com.modernapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Created by aykutasil on 27.12.2017.
 */
@Entity(tableName = "location")
data class LocationEntity(
  @PrimaryKey(autoGenerate = true) val locationId: Long? = null,
  @ColumnInfo val locLat: Double,
  @ColumnInfo val locLong: Double,
  @ColumnInfo val locTime: Date,
  @ColumnInfo val placesName: String? = null
)
