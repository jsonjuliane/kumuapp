package wanda.weiss.kumuchallenge.model.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Pojo data class for Returns and Entity
@Entity(tableName = "tracks")
data class Result(
    @PrimaryKey
    val trackId: String,
    @ColumnInfo(name = "artwork_url")
    val artworkUrl100: String?,
    @ColumnInfo(name = "description")
    val longDescription: String,
    @ColumnInfo(name = "preview")
    val previewUrl: String,
    @ColumnInfo(name = "genre")
    val primaryGenreName: String,
    @ColumnInfo(name = "track")
    val trackName: String,
    @ColumnInfo(name = "price")
    val trackPrice: Double
)