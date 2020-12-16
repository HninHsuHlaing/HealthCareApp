package com.padcx.shared.persistent.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vo.DoctorVO
import com.padcx.shared.data.vo.RecentlyDoctorVO
import com.padcx.shared.data.vo.SpecialitiesVO


/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
@Dao
interface RecentDoctorDao {
    @Query("SELECT * FROM recently_doctor")
    fun getRecentDoctor() : LiveData<List<RecentlyDoctorVO>>

    @Query("SELECT * FROM recently_doctor WHERE id = :doctorId")
    fun getRecentDoctorById(doctorId :String) : LiveData<RecentlyDoctorVO>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRecentDoctor(recentDoctor: RecentlyDoctorVO)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRecentDoctorList(recentDoctorList: List<RecentlyDoctorVO>)
}