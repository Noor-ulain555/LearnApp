package Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "properties")
data class propertyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val room: String,
    val bedrooms: String,
    val bathrooms: String,
    val floor: String,
    val city: String,
    val location: String,
    val type: String,
    val interior: String,
    val size: String,

)


