package com.padcx.shared.persistent.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vo.ConsulationChatVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
@Dao
interface ConsultationChatDao {
//    @Query("SELECT * FROM consulation_chat")
//    fun getConsultationChat() : LiveData<List<ConsulationChatVO>>
//
//    @Query("SELECT * FROM consulation_chat WHERE id = :consultChatId")
//    fun getConsultationChatById(consultChatId :String) : LiveData<ConsulationChatVO>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insertConsultationChat(consultationChatVO: ConsulationChatVO)
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insertConsultationChatList(ConsultationChatList: List<ConsulationChatVO>)
@Insert(onConflict = OnConflictStrategy.REPLACE)
fun insertConsultationChat(consultationChatVO: ConsulationChatVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConsultationChatData(consultationChatList: List<ConsulationChatVO>)

    @Query("select * from consulation_chat")
    fun getAllConsultationChatData(): LiveData<List<ConsulationChatVO>>

    @Query("select * from consulation_chat WHERE id = :id")
    fun getAllConsultationChatDataBy(id: String): LiveData<ConsulationChatVO>

    @Query("select * from consulation_chat WHERE doctor_id = :id")
    fun getAllConsultationChatDataByDoctorId(id: String): LiveData<List<ConsulationChatVO>>

    @Query("select * from consulation_chat WHERE patient_id = :id")
    fun getAllConsultationChatDataByPatientId(id: String): LiveData<List<ConsulationChatVO>>

    @Query("DELETE FROM consulation_chat")
    fun deleteAllConsultationChatData()
}