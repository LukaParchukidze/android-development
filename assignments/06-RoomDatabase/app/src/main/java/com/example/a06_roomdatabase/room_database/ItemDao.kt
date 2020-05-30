package com.example.a06_roomdatabase.room_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE uid LIKE :uid LIMIT 1")
    fun findById(uid: Int): User

    @Query("SELECT * FROM user WHERE first_name LIKE :firstName LIMIT 1")
    fun findByFirstName(firstName: String): User

    @Query("SELECT * FROM user ORDER BY uid DESC LIMIT 1")
    fun getLastUser(): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Query("UPDATE user SET first_name = :firstName, last_name = :lastName WHERE uid = :uid")
    fun update(uid: Int, firstName: String, lastName: String)
}
