package com.padcx.shared.persistent.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vo.converters.ConsultatedPatientVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/15/2020
 */

@Dao
interface ConsultatedPatientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConsultedPatient(patient: ConsultatedPatientVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConsultedPatient(patient: List<ConsultatedPatientVO>)

    @Query("select * from consulted_patient")
    fun getConsultedPatient(): LiveData<List<ConsultatedPatientVO>>

    @Query("select * from consulted_patient WHERE id = :id")
    fun getConsultedPatientBy(id: String): LiveData<ConsultatedPatientVO>

    @Query("DELETE FROM consulted_patient")
    fun deleteConsultedPatient()
}