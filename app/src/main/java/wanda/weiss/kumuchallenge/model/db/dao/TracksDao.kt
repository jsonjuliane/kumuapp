package wanda.weiss.kumuchallenge.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import wanda.weiss.kumuchallenge.model.pojo.Result

/*  Dao handling for our Result Entity
    Some of the functions are yet to be used,
    mainly included to display other use of DAO
 */
@Dao
interface TracksDao {

    @get:Query("SELECT * FROM tracks")
    val tracks: LiveData<List<Result>>

    @Insert
    fun insert(vararg tracksEntity: Result): LongArray

    @Update
    fun update(tracksEntity: Result): Int

    @Delete
    fun delete(tracksEntity: Result): Int

    @Query("DELETE FROM tracks")
    fun flush(): Int
}
