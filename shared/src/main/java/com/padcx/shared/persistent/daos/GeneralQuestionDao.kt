package com.padcx.shared.persistent.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vo.GeneralQuestionTemplateVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
@Dao
interface GeneralQuestionDao {
    @Query("SELECT * FROM general_question")
    fun getAllGeneralQuestion() : LiveData<List<GeneralQuestionTemplateVO>>

    @Query("SELECT * FROM general_question WHERE id = :generalQuestionId")
    fun getGeneralQuestionById(generalQuestionId :String) : LiveData<GeneralQuestionTemplateVO>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGeneralQuestion(consultationChatVO: GeneralQuestionTemplateVO)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGeneralQuestionList(ConsultationChatList: List<GeneralQuestionTemplateVO>)
}