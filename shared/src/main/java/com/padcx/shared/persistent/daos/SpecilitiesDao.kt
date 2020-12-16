package com.padcx.shared.persistent.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vo.SpecialitiesVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
@Dao
interface SpecilitiesDao {
    @Query("SELECT * FROM specialites")
    fun getSpecialities() : LiveData<List<SpecialitiesVO>>

    @Query("SELECT * FROM specialites WHERE id = :specialtiesId")
    fun getSpecialitiesById(specialtiesId :String) : LiveData<SpecialitiesVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialities(detail: SpecialitiesVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialitiesList(detailList: List<SpecialitiesVO>)

    @Query("DELETE FROM specialites")
    fun deleteSpecialities()

}
