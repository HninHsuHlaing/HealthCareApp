package com.padcx.shared.persistent.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vo.PatientVO

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */

@Dao
interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewPatient(patientVO: PatientVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatientList(patientList: List<PatientVO>)

    @Query("select * from patient")
    fun getAllPatientData(): LiveData<List<PatientVO>>

    @Query("select * from patient WHERE email = :email")
    fun getAllPatientDataByEmail(email: String): LiveData<PatientVO>

    @Query("DELETE FROM patient")
    fun deleteAllPatientData()

    @Query("DELETE FROM patient WHERE id = :id")
    fun deletePatientById(id: String)

}