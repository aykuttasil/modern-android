package aykuttasil.com.modernapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

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