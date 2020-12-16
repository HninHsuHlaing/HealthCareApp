package com.padcx.shared.persistent.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vo.DoctorVO
import com.padcx.shared.data.vo.SpecialitiesVO

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */
@Dao
interface DoctorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctorData(doctorVO: DoctorVO)

    @Query("select * from doctor")
    fun getAllDoctorData(): LiveData<List<DoctorVO>>

    @Query("select * from doctor WHERE email = :email")
    fun getAllDoctorDataByEmail(email: String): LiveData<DoctorVO>


    @Query("DELETE FROM doctor")
    fun deleteAllDoctorData()




}