package com.dkp.rentaride.data.dao

import androidx.room.*
import com.dkp.rentaride.data.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>


    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}