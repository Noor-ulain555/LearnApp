package Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SignUp")
data class DataEntity(
    var name: String,
    var email: String,
    var phone: String,
    var password: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
)
