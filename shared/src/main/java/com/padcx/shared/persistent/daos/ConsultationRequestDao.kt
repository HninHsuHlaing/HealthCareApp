package com.padcx.shared.persistent.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vo.ConsulationRequestVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
@Dao
interface ConsultationRequestDao {
//    @Query("SELECT * FROM consulation_request")
//    fun getConsultationRequest() : LiveData<List<ConsulationRequestVO>>
//
//    @Query("SELECT * FROM consulation_request WHERE cr_id = :consulReqId")
//    fun getConsultationRequestById(consulReqId :String) : LiveData<ConsulationRequestVO>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insertConsultationRequest(consultationReqVO: ConsulationRequestVO)
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insertConsultationRequestList(consultationList: List<ConsulationRequestVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConsultationRequest(consultationRequestVO: ConsulationRequestVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConsultationRequestData(consultationRequestList: List<ConsulationRequestVO>)

    @Query("select * from consulation_request")
    fun getAllConsultationRequestData(): LiveData<List<ConsulationRequestVO>>

    @Query("select * from consulation_request WHERE speciality = :speciality")
    fun getAllConsultationRequestDataBySpeciality(speciality: String): LiveData<List<ConsulationRequestVO>>

//    @Query("select * from consulation_request WHERE  = :doctorId")
//    fun getAllConsultationAcceptData(doctorId: String): LiveData<List<ConsultationRequestVO>>

    @Query("DELETE FROM consulation_request")
    fun deleteAllConsultationRequestData()

    @Query("DELETE FROM consulation_request where cr_id =  :id")
    fun deleteAllConsultationRequestDataById(id : String)

    @Query("select * from consulation_request where status = :accept")
    fun getConsultationAcceptData(accept : String): LiveData<List<ConsulationRequestVO>>

    @Query("select * from consulation_request WHERE cr_id = :consultation_request_id")
    fun getConsultationRequestByConsultationRequestId(consultation_request_id: String): LiveData<ConsulationRequestVO>


}