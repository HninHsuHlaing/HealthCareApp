package com.padcx.shared.persistent.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vo.SpecialquestionVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
@Dao
interface SpecialQuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialQuestions(special_questions: SpecialquestionVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialQuestions(special_questions: List<SpecialquestionVO>)

    @Query("select * from special_question")
    fun getAllSpecialQuestionsData(): LiveData<List<SpecialquestionVO>>

    @Query("select * from special_question WHERE id = :id")
    fun getAllSpecialQuestionsBy(id: String): LiveData<SpecialquestionVO>

    @Query("DELETE FROM special_question")
    fun deleteSpecialQuestions()
}