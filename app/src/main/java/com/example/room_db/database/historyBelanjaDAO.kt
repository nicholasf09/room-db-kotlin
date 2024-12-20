package com.example.room_db.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface historyBelanjaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(daftar: daftarBelanja)
    @Query("SELECT * FROM daftarBelanja ORDER BY id asc")
    fun selectAll(): MutableList<daftarBelanja>
}